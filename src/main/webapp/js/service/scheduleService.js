var app = angular.module('myApp');
app.service('scheduleService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    var name;
    self.setScope = function(scope){
        self.scope = scope;

        self.scope.today = new Date();
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        var treatmentIdInit;
        self.scope.setScope = function (treatmentId) {
            /*console.log("setScopeMethod~!");
            console.log(treatmentId);
            treatmentIdInit = treatmentId;*/
            $location.path("/visitAppointment")
        };
        self.scope.eventsSchedule = [];
        self.scope.eventSources = [self.scope.eventsSchedule];
        //pobranie eventow
        $http.get("/event/allEventsForAllSpecializationByUser")
            .then(function (response) {
                console.log("response");
                console.log(response);
                //self.scope.eventsSchedule.slice(0, self.scope.eventsSchedule.length);
                angular.forEach(response.data, function (value) {
                    self.scope.eventsSchedule.push({
                        title:value.eventName,
                        id: value.eventId,
                        start: value.eventStartTime,
                        end:value.eventEndTime,
                        color: '#378006',
                    })
                });
                console.log("aa")
                console.log(self.scope.eventsSchedule);
    
            });

        self.scope.uiConfig = {
            calendar: {
                minTime: "08:00:00",
                maxTime: "19:00:00",
                weekends: true,
                height: 650,
                firstDay: 1,
                locale: 'pl',
                lang: 'pl',
                timezone: 'local',
                allDaySlot: true,
                editable: true,
                header: {
                    left: 'month,agendaWeek,agendaDay',
                    center: 'title',
                    right: 'prev,next today'
                },
                eventClick: self.scope.alertOnEventClick,
                eventDrop: self.scope.alertOnDrop,
                eventResize: self.scope.alertOnResize,
                // Select options
                selectable: true,
                selectHelper: true,
                unselectAuto: true,
                select: function (start, end, moment) {
                    var title = prompt('Event Title:');
                    var eventData;
                    console.log("another:");
                    console.log("aaaa");
                    console.log(start);
                    console.log(moment);
                    console.log(end);
                    console.log("aaaa");
                    if (title) {
                        eventData = {
                            notes: title,
                            treatmentDate: start._d,
                            userId:$window.sessionStorage.getItem('userInfo-userId'),
                            doctorId:1,
                            treatmentId:1
                        };
                        self.scope.addEvent(eventData);
                    }
                }
            }
        };

        self.scope.addEvent = function (eventData) {
            console.log("jestem w funkcji");
            console.log(eventData);
            console.log("jestem w funkcji");
            /*$http.post('/postVisit', eventData).then(
                function (response) {
                    console.log(response.data);
                    $window.location.reload();
                }, function (error) { console.log(error) }
            );*/
        }

        $http({
            url: '/loggedUser',
            method: 'GET',
            transformResponse: [function (data) {
                self.scope.userNickName = data;
            }]
        });
    };
}]);
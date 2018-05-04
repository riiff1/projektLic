var app = angular.module('myApp');
app.service('scheduleService',['$http','$window', function ($http, $window) {
    var self = this;
    var specData;
    self.scope = null;
    var name;
    self.setScope = function(scope){
        self.scope = scope;
        self.scope.today = new Date();
        self.scope.setScope = function (scope) {
            self.scope = scope;
        };
        self.scope.eventsSchedule = [];
        self.scope.eventSources = [self.scope.eventsSchedule];
        //pobranie specjalizacji
        $http.get("/specializationColor/availableByCurrentUser")
            .then(function (response) {
                specData = response.data;
                self.scope.eventSpecialization = response.data;
                if(response.data.length != 0) {
                    self.scope.isSpecialization = true;
                    //pobranie eventow
                    $http.get("/event/allEventsForAllSpecializationByUser")
                        .then(function (response) {
                            angular.forEach(response.data, function (value) {

                                var colorSpec;
                                angular.forEach(specData, function (valueSpec) {
                                    if(valueSpec.specializationId === value.specializationId) {

                                        colorSpec = valueSpec.color;
                                    }
                                });
                                self.scope.eventsSchedule.push({
                                    title:value.eventName,
                                    id: value.eventId,
                                    start: value.eventStartTime,
                                    end:value.eventEndTime,
                                    color: colorSpec,
                                    stick: true,
                                })
                            });

                        });

                    self.scope.uiConfig = {
                        calendar: {
                            minTime: "08:00:00",
                            maxTime: "22:00:00",
                            weekends: true,
                            height: 650,
                            firstDay: 1,
                            locale: 'pl',
                            lang: 'pl',
                            timezone: 'local',
                            allDaySlot: false,
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
                            /*                        select: function (start, end, moment) {
                                                        var title = prompt('Event Title:');
                                                        var eventData;
                                                        if (title) {
                                                            eventData = {
                                                            };
                                                            self.scope.addEvent(eventData);
                                                        }
                                                    }*/
                        }
                    };
                } else {
                    self.scope.isSpecialization = false;
                }
            });

        self.scope.addEvent = function (eventData) {
            console.log(eventData);

        };

        $http({
            url: '/loggedUser',
            method: 'GET',
            transformResponse: [function (data) {
                self.scope.userNickName = data;
            }]
        });
    };


    self.saveColorButton = function (specializationArray) {
        var dataRaf = $.param({
            rows: specializationArray
        });
        $http({
            method: 'POST',
            url: '/specializationColor/postColor',
            data: specializationArray,
            headers: {'Content-Type': 'application/json'}
        }).then(function (response) {
            location.reload();
        }, function (error) {

        });
    }
}]);
var app = angular.module('myApp',['ui.calendar']);
app.controller('scheduleController', ['$scope', 'scheduleService', function ($scope, scheduleService, uiCalendarConfig) {

    $scope.setScope = function(){
        scheduleService.setScope($scope);
    };
    $scope.setScope();

    $scope.today = new Date();
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    var treatmentIdInit;
    $scope.setScope = function (treatmentId) {
        /*console.log("setScopeMethod~!");
        console.log(treatmentId);
        treatmentIdInit = treatmentId;*/
        $location.path("/visitAppointment")
    };
    $scope.treatmentsSchedules = [];
    $scope.eventSources = [$scope.treatmentsSchedules];
    /*$http.get("/getTreatmentsSchedule/"+$window.sessionStorage.getItem('userInfo-userId'))
        .then(function (response) {
            $scope.treatmentsSchedules.slice(0, $scope.treatmentsSchedules.length);
            angular.forEach(response.data, function (value) {
                $scope.treatmentsSchedules.push({
                    title:value.title,
                    id: value.id,
                    start: value.start,
                    end:value.end
                })
            });
            console.log($scope.treatmentsSchedules);

        });*/

    $scope.uiConfig = {
        calendar: {

            minTime: "08:00:00",
            maxTime: "19:00:00",
            weekends: false,
            height: 400,
            firstDay: 1,
            locale: 'pl',
            lang: 'pl',
            timezone: 'local',
            editable: true,
            header: {
                left: 'month,agendaWeek,agendaDay',
                center: 'title',
                right: 'prev,next today'
            },
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
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
                    $scope.addEvent(eventData);
                }
            }
        }
    };
    $scope.addEvent = function (eventData) {
        /*$http.post('/postVisit', eventData).then(
            function (response) {
                console.log(response.data);
                $window.location.reload();
            }, function (error) { console.log(error) }
        );*/
    }

}]);
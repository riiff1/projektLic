var app = angular.module('myApp',['ui.calendar', 'colorpicker']);
app.controller('scheduleController', ['$scope', 'scheduleService', function ($scope, scheduleService, uiCalendarConfig) {

    $scope.setScope = function(){
        scheduleService.setScope($scope);
    };
    $scope.setScope();
}]);
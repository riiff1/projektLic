var app = angular.module('myApp',['ui.calendar', 'colorpicker']);
app.controller('scheduleController', ['$scope', 'scheduleService', function ($scope, scheduleService) {

    $scope.setScope = function(){
        scheduleService.setScope($scope);
    };
    $scope.setScope();

    $scope.saveColorButton = function (specializationArray) {
        scheduleService.saveColorButton(specializationArray);
    }
}]);
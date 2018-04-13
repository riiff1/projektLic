var app = angular.module('myApp',[]);
app.controller('homeController', ['$scope', 'homeService', function ($scope, homeService) {

    $scope.setScope = function(){
        homeService.setScope($scope);
    };
    $scope.setScope();

    $scope.pickedSpecializationFunction = function (specialization) {
        homeService.pickedSpecializationFunction(specialization);
    }

}]);
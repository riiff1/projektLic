var app = angular.module('myApp',[]);
app.controller('registrationController', ['$scope', 'registrationService', function ($scope, registrationService) {

    $scope.setScope = function(){
        registrationService.setScope($scope);
    };
    $scope.submit = function () {
        registrationService.submit();
    };
    $scope.setScope();

}]);
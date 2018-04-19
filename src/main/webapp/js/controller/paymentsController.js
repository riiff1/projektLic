var app = angular.module('myApp',['ui.bootstrap']);
app.controller('paymentsController', ['$scope', 'paymentsService', function ($scope, paymentsService, $log) {

    $scope.setScope = function(){
        paymentsService.setScope($scope);
    };
    $scope.setScope();
}]);
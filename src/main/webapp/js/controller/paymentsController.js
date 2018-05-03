var app = angular.module('myApp',['ui.bootstrap']);
app.controller('paymentsController', ['$scope', 'paymentsService', function ($scope, paymentsService, $log) {

    $scope.setScope = function(){
        paymentsService.setScope($scope);
    };
    $scope.setScope();

    $scope.specializationsAvailableToBuy = function () {
        paymentsService.specializationsAvailableToBuy();
    };

    $scope.selectAll = function(valueOfCheckbox)
    {
        paymentsService.selectAll(valueOfCheckbox);
    };

    $scope.selectFunction = function (spec, boolValue) {
        paymentsService.selectFunction(spec, boolValue);
    };

    $scope.buyButton = function () {
        paymentsService.buyButton();
    };

    $scope.saveSpecializationForBuy = function () {
        paymentsService.saveSpecializationForBuy();
    };

    $scope.paymentHistory = function () {
        paymentsService.paymentHistory();
    };

    $scope.paymentDetails = function (paymentId, paymentObject) {
        paymentsService.paymentDetails(paymentId, paymentObject);
    }
}]);
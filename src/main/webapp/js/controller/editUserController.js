var app = angular.module('myApp',['ui.bootstrap']);
app.controller('editUserController', ['$scope', 'editUserService', function ($scope, editUserService, $log) {

    $scope.setScope = function(){
        $scope.borderRed = "border-color: red";
        editUserService.setScope($scope);
    };
    $scope.setScope();

    $scope.editName = function () {
        editUserService.editName();
    };
    $scope.editEmail = function () {
        editUserService.editEmail();
    };
    $scope.editPassword = function () {
        editUserService.editPassword();
    };

    $scope.updateData = function (newValue, pass) {
        editUserService.updateData(newValue, pass);
    };

    $scope.checkUser = function () {
        editUserService.checkUser();
    };

    $scope.checkEmail = function () {
        editUserService.checkEmail();
    };

    $scope.checkConfirm = function () {
        editUserService.checkConfirm();
    };
}]);
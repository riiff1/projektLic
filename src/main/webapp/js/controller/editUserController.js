var app = angular.module('myApp',['ui.bootstrap']);
app.controller('editUserController', ['$scope', 'editUserService', function ($scope, editUserService, $log) {

    $scope.setScope = function(){
        editUserService.setScope($scope);
    };
    $scope.setScope();
}]);
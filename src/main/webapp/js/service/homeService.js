var app = angular.module('myApp');
app.service('homeService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    self.setScope = function(scope){
        self.scope = scope;


        $http.get("/loggedUser").then(function (data) {
            self.scope.userNickName = data.data;
        });

        $http.get("/specialization/availableByCurrentUser").then(function (data) {
            self.scope.specializations = data.data;
        });
    };
    self.pickedSpecializationFunction = function (specialization) {
        self.scope.pickedSpecialization = specialization;
        $http.get("/specialization/availableByCurrentUser").then(function (data) {
            self.scope.specializations = data.data;
        });
    }


}]);
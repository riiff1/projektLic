var app = angular.module('myApp');
app.service('scheduleService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    var name;
    self.setScope = function(scope){
        self.scope = scope;
    };
}]);
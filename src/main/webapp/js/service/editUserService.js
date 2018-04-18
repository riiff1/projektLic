var app = angular.module('myApp');
app.service('editUserService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;

    self.setScope = function(scope){
        self.scope = scope;
        $http({
            url: '/loggedUser',
            method: 'GET',
            transformResponse: [function (data) {
                self.scope.userNickName = data;
            }]
        });
    };
}]);
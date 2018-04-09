var app = angular.module('myApp');
app.service('registrationService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    var name;
    self.setScope = function(scope){
        self.scope = scope;
    };
    this.submit = function () {
        name = self.scope.userName;
        var data1 = $.param({
            userName: self.scope.userName,
            password: self.scope.password,
            email: self.scope.email,
        });
        $http({
            method: 'POST',
            url: '/registration',
            data: data1,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function (response) {
            console.log("tu zwracam cos");
            console.log(response);
            console.log(angular.element('#myModal').modal('show'));
            angular.element('#myModal').modal('show');
        }, function (error) {
            if(error.data.exception === "org.springframework.dao.DuplicateKeyException") {
                self.scope.alert1 = 'This name: ';
                self.scope.wrongName = name;
                self.scope.alert2 = ' exist. Please change it and try again.';
            } else {
                self.scope.alert1 = 'Something goes wrong. Please try again.';
            }
        });
    };
}]);
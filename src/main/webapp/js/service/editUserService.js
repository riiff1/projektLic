var app = angular.module('myApp');
app.service('editUserService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;

    self.setScope = function(scope){
        self.scope = scope;
        self.setFalseForInputs();
        self.scope.editString = " Edit";
        $http({
            url: '/loggedUser',
            method: 'GET',
            transformResponse: [function (data) {
                self.scope.userNickName = data;
            }]
        });
    };

    self.updateData = function(newValue, pass) {
        if(self.scope.isEditName) {
        } else if(self.scope.isEditEmail) {
        } else {
        }
        //$('#editModal').modal('hide');

    };

    self.setFalseForInputs = function() {
        self.scope.password = "";
        self.scope.isEditName = false;
        self.scope.isEditEmail = false;
        self.scope.isEditPassword = false;

        self.scope.wrongName = false;
        self.scope.wrongEmail = false;
        self.scope.wrongConfirm = false;
    };

    self.editName = function () {
        self.setFalseForInputs();
        self.scope.whatEdit = "user name";
        self.scope.isEditName = true;
        $('#editModal').modal('show');
    };

    self.editEmail = function () {
        self.setFalseForInputs();
        self.scope.whatEdit = "e-mail";
        self.scope.isEditEmail = true;
        $('#editModal').modal('show');
    };

    self.editPassword = function () {
        self.setFalseForInputs();
        self.scope.whatEdit = "password";
        self.scope.isEditPassword = true;
        $('#editModal').modal('show');
    };

    self.checkUser = function () {
        self.scope.wrongName = false;
        if(self.scope.userName !== undefined) {
            $http({
                method: "GET",
                url: "/edit/userNameExist",
                params: {nick: self.scope.userName}
            }).then(function (response) {
                if(response.data) {
                    self.scope.wrongName = true;
                }
            });
        } else {
            self.scope.wrongName = true;
        }
    };

    self.checkEmail = function () {
        self.scope.wrongEmail = false;
        if(self.scope.userEmail !== undefined) {
            var re = /(?:[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+\.)*[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+@(?:(?:(?:[a-zA-Z0-9_](?:[a-zA-Z0-9_\-](?!\.)){0,61}[a-zA-Z0-9_-]?\.)+[a-zA-Z0-9_](?:[a-zA-Z0-9_\-](?!$)){0,61}[a-zA-Z0-9_]?)|(?:\[(?:(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\.){3}(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\]))$/;
            if(re.test(String(self.scope.userEmail).toLowerCase())) {
                console.log("a")
            }
        } else {
            self.scope.wrongEmail = true;
        }
    };

    self.checkConfirm = function () {
        self.scope.wrongConfirm = false;
        if(self.scope.confirmPassword !== undefined && self.scope.newPassword !== undefined) {
            if(self.scope.confirmPassword !== self.scope.newPassword) {
                self.scope.wrongConfirm = true;
            }
        } else {
            self.scope.wrongConfirm = true;
        }
    };
}]);
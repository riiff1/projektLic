var app = angular.module('myApp');
app.service('paymentsService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;

    self.setScope = function(scope){
        self.scope = scope;
        self.scope.sel = {};
        self.scope.selectedItems = 0;
        $http({
            url: '/loggedUser',
            method: 'GET',
            transformResponse: [function (data) {
                self.scope.userNickName = data;
            }]
        });
        this.specializationsAvailableToBuy();
    };

    self.specializationsAvailableToBuy = function () {
        $http.get("/specialization").then(function (data) {
            self.scope.specializationsToBuy = data.data;
        });
    };

    /*self.checkBoxWatcher = function (items) {
        console.log("watcher")
        var selectedItems = 0;
        angular.forEach(items, function(item){
            console.log("a")
            selectedItems += item.selected ? 1 : 0;
        });
        self.scope.selectedItems = selectedItems;
    };*/

    self.selectAll = function (valueOfCheckbox) {
        if(valueOfCheckbox) {
            for(i in self.scope.specializationsToBuy) {
                self.scope.sel[self.scope.specializationsToBuy[i].specializationId]=true;
            }
            self.scope.selectedItems = self.scope.specializationsToBuy.length;
        } else {
            self.scope.sel = {};
            self.scope.selectedItems = 0;
        }
    };

    self.selectFunction = function (spec, boolValue) {
        if(boolValue) {
            self.scope.sel[spec.specializationId]=true;
            self.scope.selectedItems +=1;
        } else {
            self.scope.selectedItems -=1;
        }
        console.log(self.scope.sel)
        console.log("aa")
        for(i in self.scope.sel) {
            if(self.scope.sel[i]) {
                console.log(i, "aaaaaaaa");
            }
        }
    };

    self.buyButton = function () {
        console.log("kupuj");
    }
}]);
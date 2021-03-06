var app = angular.module('myApp');
app.service('paymentsService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;

    self.setScope = function(scope){
        self.scope = scope;
        self.scope.sel = {};
        self.scope.paymentsHistory = {};
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
        $http.get("/specialization/notAvailableByCurrentUser").then(function (data) {
            self.scope.specializationsToBuy = data.data;
        });
    };

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
    };

    self.buyButton = function () {
        $http.get("/specialization/notAvailableByCurrentUser").then(function (data) {
            var count = 0;
            var listToBuyVerification = {};
            angular.forEach(data.data, function (specialization) {
                for (i in self.scope.sel) {
                    if (self.scope.sel[i] && specialization.specializationId == i) {
                        listToBuyVerification[i] = specialization;
                        count += specialization.prize;
                    }
                }
            });
            self.scope.specializationsToBuyVerify = listToBuyVerification;
            self.scope.prizeCounter = count;
        });
    };

    self.saveSpecializationForBuy = function () {
        var specializationList = [];
        for(i in self.scope.sel) {
            if(self.scope.sel[i]) {
                specializationList.push(i);
            }
        }
        $http({
            method: "POST",
            url: "/payment/savePayment",
            params: {specializationList: specializationList}
        }).then(function () {
            $window.location.reload();
        });
    };

    self.paymentHistory = function () {
        $http.get("/payment/allPaymentsByUser").then(function (data) {
            for(let i=0; i<data.data.length; i++) {
                data.data[i].showDetails = false;
            }
            self.scope.paymentsHistory = data.data;
        });
    };

    self.paymentDetails = function (paymentId, paymentObject) {
        $http({
            method: "GET",
            url: "/payment/paymentDetails",
            params: {payment: paymentId}
        }).then(function (response) {
            paymentObject.showDetails = !paymentObject.showDetails;
            paymentObject.detailsObject = response.data;
        });
    }
}]);
var app = angular.module('myApp',['ui.bootstrap']);
app.controller('homeController', ['$scope', 'homeService', function ($scope, homeService, $log) {

    $scope.setScope = function(){
        homeService.setScope($scope);
    };
    $scope.setScope();

    $scope.pickedSpecializationFunction = function (specialization) {
        homeService.pickedSpecializationFunction(specialization);
    };

    $scope.deleteNote = function (dictionaryAndNote) {
        homeService.deleteNote(dictionaryAndNote);
    };


    $scope.forModalParameter = function (dictionary) {
        homeService.forModalParameter(dictionary);
    };

    $scope.saveDataFromModal = function(noteId, dictionaryId, message) {
        homeService.saveDataFromModal(noteId, dictionaryId, message);
    };

    $scope.editNote = function (dictionaryAndNote) {
        homeService.editNote(dictionaryAndNote);
    };

    $scope.addNote = function(dictionaryAndNote) {
        homeService.addNote(dictionaryAndNote);
    };

}]);
app.directive('ngReallyClick', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                var message = attrs.ngReallyMessage;
                if (message && confirm(message)) {
                    scope.$apply(attrs.ngReallyClick);
                }
            });
        }
    }
}]);
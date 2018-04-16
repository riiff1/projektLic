var app = angular.module('myApp',['ui.bootstrap']);
app.controller('homeController', ['$scope', 'homeService', function ($scope, homeService, $log) {

    $scope.setScope = function(){
        homeService.setScope($scope);
    };
    $scope.setScope();

    $scope.pickedSpecializationFunction = function (specialization) {
        homeService.pickedSpecializationFunction(specialization);
    };

    $scope.getNoteMessageForDictionary = function(dictionaryId) {
        homeService.getNoteMessageForDictionary(dictionaryId);
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

    $scope.pickedWordFromSearch = function ($item) {
        console.log("item");
        console.log($item);
    };

    $scope.totalItems = 64;
    $scope.currentPage = 4;

    $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
    };

    $scope.pageChanged = function() {
        $log.log('Page changed to: ' + $scope.currentPage);
    };

    $scope.maxSize = 5;
    $scope.bigTotalItems = 175;
    $scope.bigCurrentPage = 1;

}]);
var app = angular.module('myApp');
app.service('homeService',['$http','$window', function ($http, $window) {
    var self = this;
    var specializationIsShow= null;
    var dictionaryObjectFromList;
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

        $http.get("/specialization/availableByCurrentUser").then(function (data) {
            self.scope.specializations = data.data;
        });
    };
    self.pickedSpecializationFunction = function (specialization) {
        self.scope.pickedSpecialization = specialization.name;
        self.scope.isPickedSpecialization = true;
        $http({
            method: "GET",
            url: "/getDictionaryAndNoteBySpecialization",
            params: {specializationId: specialization.specializationId}
        }).then(function (response) {
            self.scope.dictionaries = response.data;
        });
    };

    self.deleteNote = function (dictionaryAndNote) {
        dictionaryAndNote.message = null;
        specializationIsShow = dictionaryAndNote.specializationId;
        $http({
            method: "DELETE",
            url: "/note/removeNote",
            params: {noteId: dictionaryAndNote.noteId}
        }).then(function (response) {
            this.pickedSpecializationFunction = specializationIsShow;
        });
    };

    self.forModalParameter = function (dictionaryForModal) {
        dictionaryObjectFromList = dictionaryForModal;
        self.scope.headerModal = dictionaryForModal.word;
        self.scope.modalNoteId = dictionaryForModal.noteId;
        self.scope.modalDictionaryId = dictionaryForModal.dictionaryId;
        if(dictionaryForModal.message != null) {
            self.scope.myTextarea = dictionaryForModal.message;
        } else {
            self.scope.myTextarea = "";
        }
    };

    self.saveDataFromModal = function (noteId, dictionaryId,  message) {
        dictionaryObjectFromList.message = message;
        if(noteId === 0) {
            $http({
                method: "POST",
                url: "/note/insertNote",
                params: {dictionaryId: dictionaryId, message: message}
            }).then(function (response) {
                dictionaryObjectFromList.noteId = response.data;
            });
        } else {
            $http({
                method: "POST",
                url: "/note/updateNote",
                params: {noteId: noteId, message: message}
            }).then(function (response) {

            });
        }
    };

}]);
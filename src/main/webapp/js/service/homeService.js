var app = angular.module('myApp');
app.service('homeService',['$http','$window', function ($http, $window) {
    var self = this;
    var specializationIsShow= null;
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
        $http({
            method: "GET",
            url: "/getDictionaryAndNoteBySpecialization",
            params: {specializationId: specialization.specializationId}
        }).then(function (response) {
            self.scope.dictionaries = response.data;
        });
    };
    self.getNoteMessageForDictionary = function (dictionaryId) {
        self.scope.isNoteMessage = false;
        $http({
            method: "GET",
            url: "/note/getNoteMessageForDictionary",
            params: {dictionaryId: dictionaryId}
        }).then(function (response) {
            /*console.log("aaaaa");
            console.log(response);
            self.scope.noteMessage = response;*/
        });
    };

    self.deleteNote = function (dictionaryAndNote) {
        specializationIsShow = dictionaryAndNote.specializationId;
        $http({
            method: "DELETE",
            url: "/note/removeNote",
            params: {noteId: dictionaryAndNote.noteId}
        }).then(function (response) {
            this.pickedSpecializationFunction = specializationIsShow;
            /*$window.location.reload();
            this.pickedSpecializationFunction = specializationIsShow;
            console.log("Usuniety");*/
        });
    };

    self.forModalParameter = function (dictionaryForModal) {
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
        if(noteId === 0) {
            $http({
                method: "POST",
                url: "/note/insertNote",
                params: {dictionaryId: dictionaryId, message: message}
            }).then(function (response) {

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
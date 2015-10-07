(function () {
    'use strict';
    angular.module("bookcaseApp")
        .controller("bookcaseController", function($scope,bookcaseService,$localStorage,HTTPCache){
            $scope.errorMessage = "";
            $scope.books = {};
            $scope.hasBooks = true;
            $scope.flashMessage = "";

            var loadBooks = function(){
                bookcaseService.getBooks().then(function(data){
                    $scope.books = data;
                }, function(error){
                    $scope.errorMessage = "Aconteceu um problema ao resgatar os dados do servidor. " +
                     "Tente novamente mais tarde. ";
                }).finally(function(){
                    hasBooksLoaded();
                    loadLocalFlashMessage();
                    HTTPCache.init();
                });
            }();
            var hasBooksLoaded = function(){
                if($scope.books.length === 0){
                    $scope.hasBooks = false;
                }
            };
            var loadLocalFlashMessage = function(){
                $scope.flashMessage = $localStorage.flashMessage;
                delete $localStorage.flashMessage;
            };
        });
}());
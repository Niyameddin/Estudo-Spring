(function () {
    'use strict';
    angular.module("bookcaseApp")
        .controller("bookcaseController", function($scope,bookcaseService,$localStorage,HTTPCache,$state,$stateParams){
            $scope.errorMessage = "";
            $scope.books = {};
            $scope.hasBooks = true;
            $scope.flashMessage = "";

            $scope.deleteBook = function(isbn){
                bookcaseService.deleteBook(isbn).then(function(response){
                    var successResponse = response[Object.keys(response)[0]];
                    $localStorage.flashMessage = successResponse;
                    $state.transitionTo($state.current, $stateParams, {
                        reload: true,
                        inherit: false,
                        notify: true
                    });
                }, function(error){

                }).finally(function(){});
            };

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
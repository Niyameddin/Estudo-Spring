(function () {
    'use strict';
    angular.module("bookcaseApp")
        .controller("bookcaseController", function($scope,bookcaseService,$localStorage,
                                                    HTTPCache,$state,$stateParams){
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
                    cacheInit();
                });
            }();
            var hasBooksLoaded = function(){
                if($scope.books.length === 0){
                    $scope.hasBooks = false;
                }else{
                    $scope.hasBooks = true;
                }
            };
            var loadLocalFlashMessage = function(){
                $scope.flashMessage = $localStorage.flashMessage;
                delete $localStorage.flashMessage;
            };
            var removeFromBookList = function(index){
                if (index > -1) $scope.books.splice(index,1);
                hasBooksLoaded();                       
            };
            $scope.deleteBook = function(book){
                bookcaseService.deleteBook(book.isbn).then(function(response){
                    var successResponse = response[Object.keys(response)[0]];
                    $localStorage.flashMessage = successResponse;                    
                    var index = $scope.books.indexOf(book);
                    removeFromBookList(index);
                    $state.transitionTo($state.current, $stateParams, {
                        reload: true,
                        inherit: false,
                        notify: true
                    });                                                             
                }, function(error){
                    $scope.errorMessage = "Aconteceu um problema ao resgatar os dados do servidor. " +
                     "Tente novamente mais tarde. ";
                });
            };
            var cacheInit = function(){
                return HTTPCache.init();
            };
        });
}());
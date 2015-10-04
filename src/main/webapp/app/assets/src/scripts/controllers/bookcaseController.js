(function () {
    'use strict';
    angular.module("bookcaseApp")
        .controller("bookcaseController", function($scope,bookcaseService,$localStorage){
            $scope.errorMessage = "";
            $scope.books = {};
            $scope.hasBooks = true;

            var loadBooks = function(){
                var promisedData = bookcaseService.getBooks();                
                promisedData.$promise.then(function(data){
                    $scope.books = data;                    
                    $localStorage.bookList = $scope.books;                    
                }, function(error){
                    $scope.errorMessage = "Aconteceu um problema ao resgatar os dados do servidor. " +
                     "Tente novamente mais tarde. ";
                }).finally(function(){
                    hasBooksLoaded();
                });
            }();
            var hasBooksLoaded = function(){                
                if($scope.books.length === 0){
                    $scope.hasBooks = false;
                }
            };
        });
}());
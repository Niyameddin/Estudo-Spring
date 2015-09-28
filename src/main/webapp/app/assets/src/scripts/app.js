(function () {
    'use strict';

    angular.module("bookcaseApp", ['ui.router','ngResource','ngStorage']);

    angular.module("bookcaseApp")
        .constant("$config",{
            baseUrl:"http://localhost:9090",
            bookApi: "/bookcase/api/books",
            projectName:"Estudo-Spring"
        });

    angular.module("bookcaseApp")
        .config(function($stateProvider, $urlRouterProvider, $config){
            $stateProvider
            // Home Page
            .state('bookcase', {
               url: '/bookcase',
               templateUrl: 'assets/views/partial-bookcase.html',
               controller: 'bookcaseController'
            })
            // Books Page
            .state('books', {
               url: '/bookcase/books',
               templateUrl: 'assets/views/partial-bookcase.html',
               controller: 'bookcaseController'
            })
            // Register a Book Page
            .state('register', {
               url: '/bookcase/register',
               templateUrl: 'assets/views/partial-register-book.html',
               controller: 'registerBookController'
            });
            $urlRouterProvider.otherwise('/bookcase');
        })
        //Run when app starts
        .run(function($state){
              $state.go('bookcase');
            });
}());    
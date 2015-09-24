    var bookcase = angular.module("bookcaseApp", ['ui.router']);

    bookcase.constant("$config",{
        baseUrl:"http://localhost:9090",
        bookApi: "/bookcase/api/books",
        projectName:"Estudo-Spring"
    });

    bookcase.config(function($stateProvider, $urlRouterProvider, $config){
        $stateProvider
        // Home Page
        .state('bookcase', {
           url: '/bookcase',
           templateUrl: 'assets/views/partial-bookcase.html',
           controller: 'bookcaseController'
        })
        .state('books', {
           url: '/bookcase/books',
           templateUrl: 'assets/views/partial-bookcase.html',
           controller: 'bookcaseController'
        })
        .state('register', {
           url: '/bookcase/register',
           template: '<div class="title">Em Breve</div>'
        });
        $urlRouterProvider.otherwise('/bookcase');
    });
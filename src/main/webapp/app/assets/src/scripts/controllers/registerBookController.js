(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,Book,$state){
			$scope.newBook = {isbn:"9999999999993",
							  title:"angular+springmvc",
							  author:"guilherme",
							  edition:"0"};

			$scope.response = {};

			$scope.getBooks = function () {
				$scope.books = Book.query();
			};

			$scope.createBook = function () {
				var book = new Book($scope.newBook);
				book.$save({}, function(response) {
					$scope.response = response;
					console.log($scope.response);
					// $state.transitionTo("books");
				}, function(failedResponse){
				  	$scope.response = failedResponse;
				});
			};

			var save_book = function(){
				$scope.createBook();
			}();
		});
}());   
(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,bookcaseService,$state){
			$scope.newBook = {isbn:"9999999999991",
							  title:"angular+springmvc",
							  author:"guilherme",
							  edition:"1"};

			$scope.response = {};			

			var save_book = function(){
				$scope.response = bookcaseService.createBook($scope.newBook);
				$state.transitionTo("books");
			}();
		});
}());   
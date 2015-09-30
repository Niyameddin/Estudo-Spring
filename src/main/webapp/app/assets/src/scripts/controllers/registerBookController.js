(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,bookcaseService,$state){
			$scope.newBook = {isbn:"9999999999991",
							  title:"angular+springmvc",
							  author:"guilherme",
							  edition:"1"};

			$scope.response = {};

			var registerNewBook = function(){
				$scope.response = bookcaseService.createBook($scope.newBook);				
				if($scope.response){
					if($scope.response.status == "SUCCESS"){
						$state.transitionTo("books");
					}
				}else{
					$scope.response = {
						status:"NULL",
						objectType:"null",
						objectAttributes:"null",
						defaultMessage:"Houve um problema ao receber a resposta do servidor"
					};
				}	
			}();
		});
}());
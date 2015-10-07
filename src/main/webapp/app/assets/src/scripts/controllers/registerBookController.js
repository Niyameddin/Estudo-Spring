(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,$state,$localStorage,bookcaseService){
			$scope.newBook = {isbn:"",
							  title:"",
							  author:"",
							  edition:""};

			$scope.response = {};

			var resetFields = function(){
				return {isbn:"",
						title:"",
						author:"",
						edition:""};
			};

			$scope.registerNewBook = function(book){
				bookcaseService.createBook(book).then(function(response){
					var successResponse = response[Object.keys(response)[0]];
					$scope.newBook = resetFields();
					$localStorage.flashMessage = successResponse;
					$state.transitionTo("books");

				}, function(failedResponse){
					var badResponse = failedResponse.data[Object.keys(failedResponse.data)[0]];
					$scope.response = badResponse;
					if(badResponse.status == "WARNING"){
						console.log("WARNING");
					}else if(badResponse.status == "ERROR"){
						console.log("ERROR");
					}else{
						$scope.response = {
							status:"NULL",
							objectType:"null",
							objectAttributes:"null",
							defaultMessage:"Houve um problema ao receber a resposta do servidor. "+
							"Tente novamente daqui alguns instantes."
						};
					}
				});
			};
		});
}());
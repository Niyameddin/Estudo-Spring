(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,$state,$localStorage,bookcaseService){
			$scope.newBook = {isbn:"",
							  title:"",
							  author:"",
							  edition:""};
			$scope.responseStyle = "";
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
					if(failedResponse.data){
						var badResponse = failedResponse.data[Object.keys(failedResponse.data)[0]];
						if(badResponse.status == "WARNING"){
							$scope.responseStyle = "alert alert-warning";						
							$scope.response = failedResponse.data;
						}else if(badResponse.status == "ERROR"){
							$scope.responseStyle = "alert alert-danger";
							$scope.response = failedResponse.data;						
						}
					}else{
						$scope.responseStyle = "alert alert-danger";
						$scope.response = {
							0:{
								status:"NULL",
								objectType:"null",
								objectAttributes:"null",
								defaultMessage:"Houve um problema ao receber a resposta do servidor."+
								"Tente novamente daqui alguns instantes."
							}							
						};
					}
				});
			};
		});
}());
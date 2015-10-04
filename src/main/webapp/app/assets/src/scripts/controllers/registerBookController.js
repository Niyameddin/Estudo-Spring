(function () {
	'use strict';

	angular.module("bookcaseApp")
		.controller("registerBookController", function($scope,bookcaseService,$state){			
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
				$scope.response = bookcaseService.createBook(book);										
				if($scope.response){
					if($scope.response.status == "SUCCESS"){
						$scope.newBook = resetFields();
						$state.transitionTo("books");
					}else if($scope.response.status == "WARNING"){
						alert("warning");						
					}else{
						alert("error");						
					}
				}else{
					$scope.response = {
						status:"NULL",
						objectType:"null",
						objectAttributes:"null",
						defaultMessage:"Houve um problema ao receber a resposta do servidor. "+ 
						"Tente novamente daqui alguns instantes."
					};
				}				
			};
		});
}());
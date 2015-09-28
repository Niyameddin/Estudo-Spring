(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function($http,Book,$localStorage,$config){
			var _getBooks = function(){
				return $http.get($config.baseUrl + $config.bookApi);
			};
			var _createBook = function(newBook){
				var book = new Book(newBook);
				var responseData = {};
				book.$save({}, function(response) {
					var resp = response[Object.keys(response)[0]];
					$localStorage.bookList.data.push(resp.objectAttributes);
					_sortArrayById();
					$localStorage.responseData = resp;				
				}, function(failedResponse){
					if(failedResponse.data){
						var resp = failedResponse.data[Object.keys(failedResponse.data)[0]];
						$localStorage.responseData = resp;				  		
					}				  	
				});				
				responseData = $localStorage.responseData;
				delete $localStorage.responseData;				
				return responseData;
			};
			var _sortArrayById = function(){
				$localStorage.bookList.data.sort(function(e1,e2){return e1.isbn-e2.isbn;});
			};
			return{
				getBooks: _getBooks(),
				createBook: _createBook
			};
		});
}());
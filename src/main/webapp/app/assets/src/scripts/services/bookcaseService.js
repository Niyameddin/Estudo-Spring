(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function(Book,$localStorage,$config){
			var _getBooks = function(){				
				return Book.query();
			};
			var _createBook = function(newBook){
				var book = new Book(newBook);
				var responseData = {};				
				book.$save(function(response) {					
					var resp = response[Object.keys(response)[0]];
					$localStorage.bookList.push(resp.objectAttributes);
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
			return{
				getBooks: _getBooks,
				createBook: _createBook
			};
		});
}());
(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function(BookApi){
			var _getBooks = function(){				
				return BookApi.getList();
			};
			var _createBook = function(newBook){
				return BookApi.post(newBook);
			};
			return{
				getBooks: _getBooks,
				createBook: _createBook
			};
		});
}());
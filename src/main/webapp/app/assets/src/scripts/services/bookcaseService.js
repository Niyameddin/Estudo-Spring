(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function(BookApi,Restangular){
			var _getBooks = function(){				
				return BookApi.getList();
			};
			var _createBook = function(newBook){
				return BookApi.post(newBook);
			};
			var _deleteBook = function(isbn){
				return Restangular.one("delete",isbn).remove();
			};
			return{
				getBooks: _getBooks,
				createBook: _createBook,
				deleteBook: _deleteBook
			};
		});
}());
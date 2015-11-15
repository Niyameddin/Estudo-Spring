(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function(BookApi){
			var _getBooks = function(){				
				return BookApi.getAll().getList();
			};
			var _getBook = function(book){
				return BookApi.getOne("books",book.isbn).get();
			};
			var _createBook = function(newBook){
				return BookApi.getAll().post(newBook);
			};
			var _deleteBook = function(isbn){
				return BookApi.getOne("delete",isbn).remove();
			};			
			var _updateBook = function(book){						
				return book.put();
			};
			var _copyBook = function(original){
				return BookApi.copy(original);
			};
			return{
				getBooks: _getBooks,
				getBook: _getBook,
				createBook: _createBook,
				deleteBook: _deleteBook,
				updateBook: _updateBook,
				copyBook: _copyBook
			};
		});
}());
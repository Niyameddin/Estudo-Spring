(function () {
	'use strict';
	angular.module("bookcaseApp")
		.service("bookcaseService", function($http, $config){
			var _getBooks = function () {
				return $http.get($config.baseUrl + $config.bookApi);
			};
			return{
				getBooks: _getBooks()
			};
		});
}());
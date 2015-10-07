(function () {
    'use strict';

    angular.module("bookcaseApp")
	    .factory("BookApi", function (Restangular) {
		    return Restangular.all('books');
		});
}());
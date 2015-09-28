(function () {
    'use strict';

    angular.module("bookcaseApp")
	    .factory("Book", function ($resource, $config) {
		    return $resource($config.baseUrl+$config.bookApi+"/:id", {id: "@id"}, {
		        update: {
		            method: 'PUT'
		        }
		    });
		});
}());   
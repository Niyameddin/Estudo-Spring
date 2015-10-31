(function () {
    'use strict';

    angular.module("bookcaseApp")
    	.factory('HTTPCache', ['Restangular', '$cacheFactory', 
	      function(Restangular, $cacheFactory) {
	       var service = {};
	       var cache;

	       // Creates the cache
	       service.init = function() {
	           cache = $cacheFactory('http');
	           Restangular.setDefaultHttpFields({cache: cache});

	           Restangular.setResponseInterceptor(function(response, operation) {
	               if (operation === 'put' || operation === 'post' || operation === 'remove') {
	                   cache.removeAll();
	               }
	               return response;
	           });
	       };

	       return service;

	    }]);
}());
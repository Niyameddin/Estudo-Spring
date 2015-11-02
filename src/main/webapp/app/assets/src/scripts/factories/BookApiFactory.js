(function () {
    'use strict';

    angular.module("bookcaseApp")
	    .factory("BookApi", function (Restangular) {
	    	var _getAll = function(){
	    		return Restangular.all('books');
	    	};
	    	var _getOne = function(path,param){
	    		return Restangular.one(path,param);
	    	};
		    return {
		    	getAll: _getAll,
		    	getOne: _getOne
		    };
		});
}());
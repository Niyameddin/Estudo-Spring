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
	    	var _copy = function(original){
	    		return Restangular.copy(original);
	    	};
		    return {
		    	getAll: _getAll,
		    	getOne: _getOne,
		    	copy: _copy
		    };
		});
}());
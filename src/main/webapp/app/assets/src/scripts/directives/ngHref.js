(function () {
	'use strict';
	angular.module("bookcaseApp")
		.directive('ngHref', ['$config',function($config) {
			return function(scope, element, attrs) {
				element.bind('click', ngHref);

				function ngHref(event) {         	                               
					event.target.href = $config.baseUrl+$config.bookApi+"books";
				}
			};
		}]);
}());
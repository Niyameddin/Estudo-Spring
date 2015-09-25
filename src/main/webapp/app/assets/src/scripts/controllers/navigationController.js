(function () {
	'use strict';
	angular.module("bookcaseApp")
		.controller("navigationController", function($scope, $config){
			$scope.appName = $config.projectName;
			$scope.baseUrl = $config.baseUrl;
		});
}());
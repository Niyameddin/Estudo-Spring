    bookcase.controller("navigationController", function($scope, $config){
        $scope.appName = $config.projectName;
        $scope.baseUrl = $config.baseUrl;
    });
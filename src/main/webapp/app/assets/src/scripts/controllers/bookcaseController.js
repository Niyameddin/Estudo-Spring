    bookcase.controller("bookcaseController", function($scope,bookcaseService){
        $scope.errorMessage = "";
        $scope.books = {data:[]};
        $scope.hasBooks = true;

        var loadBooks = function(){
            bookcaseService.getBooks.success(function (data){
                $scope.books = {
                    data: data
                };
            }).error(function (data, status) {
                $scope.errorMessage = "Aconteceu um problema ao resgatar os dados do servidor. " +
                "Tente novamente mais tarde. ";
            }).finally(function(){
              hasBooksLoaded();
          });
        }();
        var hasBooksLoaded = function(){
            if(Object.keys($scope.books.data).length === 0){
              $scope.hasBooks = false;
          }
      };
  });
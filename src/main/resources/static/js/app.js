(function() {
	var app = angular.module('app', []);

	app.controller('Hello', function($http, $scope) {
		$http.get('http://rest-service.guides.spring.io/greeting').success(
				function(data) {
					$scope.greeting = data;
				});
	});

})();


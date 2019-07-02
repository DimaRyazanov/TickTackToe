var applicationRoute = angular.module('applicationRoute', ['ngRoute', 'gameControllers']);

applicationRoute.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider.
        when('/game', {
            templateUrl: '../templates/gameBoard.html',
            controller: 'gameController'
        })
        .otherwise({
        redirectTo: '/',
    });
}]);

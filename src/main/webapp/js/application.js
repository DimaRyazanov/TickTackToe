var applicationRoute = angular.module('applicationRoute', ['ngRoute', 'gameController', 'roomController']);

applicationRoute.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider.
        when('/game/:id', {
            templateUrl: '../templates/gameBoard.html',
            controller: 'gameController'
        })
        .when('/room',{
            templateUrl: '../templates/room.html',
            controller: 'roomController'
        })
        .otherwise({
        redirectTo: '/',
    });
}]);

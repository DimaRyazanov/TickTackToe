var gameController = angular.module('gameController', []);
var roomController = angular.module('roomController', []);

gameController.controller('gameController', ['$scope', '$http', '$interval', '$routeParams', '$location',
    function (scope, http, interval, routeParams, location) {

        getGameStatus();
        getInitGame();
        if (isEndGame()) {
            initFirstData();
        } else {
            initFirstData();
            getCreator();
            getCurrentPlayer();
            getPlayerTurn();

            var timerUpdate = interval(function () {
                if (isRegistrationGame()) {
                    getGamePlayers();
                    getGameStatus();
                }
                if (isProgressGame()) {
                    getPlayerTurn();
                    getMoves();
                    getGameStatus();
                }
                if (isEndGame()) {
                    stopTimer();
                }
            }, 500);
        }

        function initFirstData() {
            initializationFields();
            getMoves();
            getGamePlayers();
            getGameStatus();
        }

        function getInitGame() {
            http.get('/api/room/' + routeParams.id)
                .then(function onSuccess(response) {
                    scope.gameProperties = response.data;
                })
                .catch(function onError(response) {
                    location.path('/room');
                    scope.errorMessage = "Failed to load game properties " + response.status;
                });
        }

        function isEndGame() {
            var status = (scope.gameStatus === "FINISH" || scope.gameStatus === "CANCEL" || scope.gameStatus === "ERROR");
            scope.isEndGame = status;
            return status;
        }

        function isRegistrationGame() {
            var status = scope.gameStatus === "REGISTRATION";
            scope.isRegistrationGame = status;
            return status;
        }

        function isProgressGame() {
            var status = scope.gameStatus === "PROGRESS";
            scope.isProgressGame = status;
            return status;
        }

        function isStartGame() {
            var status = (scope.gameStatus === "REGISTRATION" || scope.gameStatus === "PROGRESS");
            scope.isStartGame = status;
            return status;
        }

        function getCurrentPlayer() {
            http.get('/api/game/current')
                .then(function onSuccess(response) {
                    scope.currentPlayer = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load current user " + response.status;
                    setStatus("ERROR");
                });
        }

        function getPlayerTurn() {
            http({
                url: '/api/game/turn',
                method: "GET",
                params: {gameId: routeParams.id}
            })
                .then(function onSuccess(response) {
                    scope.playerTurn = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load player turn " + response.status;
                    setStatus("ERROR");
                });
        }

        function getMoves() {
            http({
                url: '/api/game/moves',
                method: "GET",
                params: {gameId: routeParams.id}
            })
                .then(function onSuccess(response) {
                    scope.moves = response.data;
                    angular.forEach(scope.moves, function (field) {
                        scope.fields[field.cellRow][field.cellColumn].letter = field.symbol;
                    });
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load moves " + response.status;
                    setStatus("ERROR");
                });
        }

        function initializationFields() {
            scope.fields = [
                [
                    {'id': '00', 'letter': '', 'class': 'box'},
                    {'id': '01', 'letter': '', 'class': 'box'},
                    {'id': '02', 'letter': '', 'class': 'box'}
                ],
                [
                    {'id': '10', 'letter': '', 'class': 'box'},
                    {'id': '11', 'letter': '', 'class': 'box'},
                    {'id': '12', 'letter': '', 'class': 'box'}
                ],
                [
                    {'id': '20', 'letter': '', 'class': 'box'},
                    {'id': '21', 'letter': '', 'class': 'box'},
                    {'id': '22', 'letter': '', 'class': 'box'}
                ]
            ];
        }

        function getCreator() {
            http({
                url: '/api/game/creator',
                method: "GET",
                params: {gameId: routeParams.id}
            })
                .then(function onSuccess(response) {
                    scope.creator = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load creator game " + response.status;
                    setStatus("ERROR");
                });
        }

        function getGamePlayers() {
            http({
                url: '/api/game/game_players',
                method: "GET",
                params: {gameId: routeParams.id}
            })
                .then(function onSuccess(response) {
                    scope.gamePlayers = response.data;
                    scope.isMoreOnePlayer = scope.gamePlayers.length > 1;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load players in game " + response.status;
                    setStatus("ERROR");
                });
        }

        function getGameStatus() {
            http({
                url: '/api/game/status',
                method: "GET",
                params: {gameId: routeParams.id}
            })
                .then(function onSuccess(response) {
                    scope.gameStatus = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load game status " + response.status;
                    setStatus("ERROR");
                });
        }

        function setStatus(status) {
            var param = {gameStatus: status, gameId: routeParams.id};
            http({
                url: '/api/game/set_status',
                method: "POST",
                data: JSON.stringify(param),
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        }

        scope.canStartGame = function () {
            if (isRegistrationGame())
                return (scope.creator.id === scope.currentPlayer.id) && scope.isMoreOnePlayer;
            else
                return false;
        };

        scope.canCancelGame = function () {
            if (isStartGame())
                return scope.creator.id === scope.currentPlayer.id;
            else
                return false;
        };

        scope.clickPlayerMove = function (cell) {
            getPlayerTurn();
            if (isProgressGame()) {
                if (cell.letter === "") {
                    if (scope.currentPlayer.id === scope.playerTurn.id) {
                        var cellRow = parseInt(cell.id.charAt(0));
                        var cellColumn = parseInt(cell.id.charAt(1));
                        var move = {'gameId' : routeParams.id,
                                    'cellRow': cellRow,
                                    'cellColumn': cellColumn};

                        http.post('/api/game/move', JSON.stringify(move), {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                            .then(function successCallback(response) {
                                getPlayerTurn();
                                getMoves();
                            })
                            .catch(function errorCallback(response) {
                                scope.errorMessage = "Can't send the move " + response.status;
                                setStatus("ERROR");
                            });

                        getGameStatus();
                    }
                }
            }
        };

        scope.clickStartGame = function () {
            if (isRegistrationGame()) {
                setStatus("PROGRESS")
                    .then(function successCallback(response) {
                        getGameStatus();
                    })
                    .catch(function errorCallback(response) {
                        scope.errorMessage = "Can't start game " + response.status;
                        setStatus("ERROR");
                    });
            }
        };

        scope.clickCancelGame = function () {
            if (isStartGame()) {
                setStatus("CANCEL")
                    .then(function successCallback(response) {
                        getGameStatus();
                    })
                    .catch(function errorCallback(response) {
                        scope.errorMessage = "Can't cancel game " + response.status;
                        setStatus("ERROR");
                    });
            }
        };

        function stopTimer() {
            interval.cancel(timerUpdate);
        }
    }]);

roomController.controller('roomController', ['$rootScope', '$scope', '$http', '$location', '$interval',
    function (rootScope, scope, http, location, interval) {
        getCurrentPlayer();
        isCurrentPlayerInGame();
        getRegistrationGames();

        function getRegistrationGames() {
            http.get('/api/room/games')
                .then(function onSuccess(response) {
                    scope.games = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load current games " + response.status;
                });
        }

        function getCurrentPlayer() {
            http.get('/api/game/current')
                .then(function onSuccess(response) {
                    scope.currentPlayer = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load current user " + response.status;
                });
        }

        function isCurrentPlayerInGame() {
            http.get('/api/room/in_game')
                .then(function onSuccess(response) {
                    scope.inGame = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load in game status " + response.status;
                });
        }

        scope.joinGame = function joinGame(gameId) {
            http.post('/api/room/join_game', JSON.stringify(gameId), {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                .then(function onSuccess() {
                    location.path('/game/' + gameId);
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to join in game " + response.status;
                });
        }

        scope.createGame = function createGame() {
            var count_players = 2;
            http.post("/api/room/create", JSON.stringify(count_players), {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            })
                .then(function onSuccess(response) {
                    rootScope.gameId = response.data.id;
                    location.path('/game/' + rootScope.gameId);
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to create game " + response.status;
                    location.path('/room');
                });
        }
    }]);
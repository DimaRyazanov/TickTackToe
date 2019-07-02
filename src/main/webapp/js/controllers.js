var gameControllers = angular.module('gameControllers', []);

gameControllers.controller('gameController', ['$scope', '$http', '$interval',
    function (scope, http, interval) {
        initializationFields();
        getCurrentPlayer();
        getCreator();
        getPlayerTurn();
        getMoves();
        getGamePlayers();
        getGameStatus();

        function getCurrentPlayer() {
            http.get('/api/game/current')
                .then(function onSuccess(response) {
                scope.currentPlayer = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load current user " + response.status;
                    setErrorStatus();
                });
        }

        function getPlayerTurn() {
            http.get('/api/game/turn')
                .then(function onSuccess(response) {
                    scope.playerTurn = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load player turn " + response.status;
                    setErrorStatus();
                });
        }

        function getMoves() {
            http.get('/api/game/moves')
                .then(function onSuccess(response) {
                    scope.moves = response.data;
                    angular.forEach(scope.moves, function (field) {
                        scope.fields[field.cellRow][field.cellColumn].letter = field.symbol;
                    });
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load moves " + response.status;
                    setErrorStatus();
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
            http.get('/api/game/creator')
                .then(function onSuccess(response) {
                    scope.creator = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load creator game " + response.status;
                    setErrorStatus();
                });
        }

        function getGamePlayers() {
            http.get('/api/game/game_players')
                .then(function onSuccess(response) {
                    scope.gamePlayers = response.data;
                    scope.isMoreOnePlayer = scope.gamePlayers.length > 1;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load players in game " + response.status;
                    setErrorStatus();
                });
        }

        function getGameStatus() {
            http.get('/api/game/status')
                .then(function onSuccess(response) {
                    scope.gameStatus = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load game status " + response.status;
                    setErrorStatus();
                });
        }

        function setErrorStatus() {
            http.post('/api/game/set_status', JSON.stringify("ERROR"), {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                .then(function successCallback(response) {
                    getGameStatus();
                });
        }

        scope.isStartGame = function () {
            return (scope.gameStatus === "REGISTRATION") && (scope.creator.id === scope.currentPlayer.id) && scope.isMoreOnePlayer;
        };

        scope.canCancelGame = function () {
            return (scope.gameStatus === "REGISTRATION" || scope.gameStatus === "PROGRESS") && (scope.creator.id === scope.currentPlayer.id);
        };

        scope.clickPlayerMove = function (cell) {
            getPlayerTurn();
            if (scope.gameStatus === "PROGRESS") {
                if (cell.letter === "") {
                    if (scope.currentPlayer.id === scope.playerTurn.id) {
                        var cellRow = parseInt(cell.id.charAt(0));
                        var cellColumn = parseInt(cell.id.charAt(1));
                        var move = {'cellRow': cellRow, 'cellColumn': cellColumn};

                        http.post('/api/game/move', JSON.stringify(move), {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                            .then(function successCallback(response) {
                                getPlayerTurn();
                                getMoves();
                            })
                            .catch(function errorCallback(response) {
                                scope.errorMessage = "Can't send the move " + response.status;
                                setErrorStatus();
                            });

                        getGameStatus();
                    }
                }
            }
        };

        scope.clickStartGame = function () {
            if (scope.gameStatus === "REGISTRATION") {
                http.post(
                    '/api/game/set_status',
                    JSON.stringify("PROGRESS"),
                    {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                    .then(function successCallback(response) {
                        getGameStatus();
                    })
                    .catch(function errorCallback(response) {
                        scope.errorMessage = "Can't start game " + response.status;
                        setErrorStatus();
                    });
            }
        };

        scope.clickCancelGame = function () {
            if (scope.gameStatus === "REGISTRATION" || scope.gameStatus === "PROGRESS") {
                http.post('/api/game/set_status', JSON.stringify("CANCEL"), {headers: {'Content-Type': 'application/json; charset=UTF-8'}})
                    .then(function successCallback(response) {
                        getGameStatus();
                    })
                    .catch(function errorCallback(response) {
                        scope.errorMessage = "Can't cancel game " + response.status;
                        setErrorStatus();
                    });
            }
        };

        var timerUpdate = interval(function () {
            if (scope.gameStatus === "REGISTRATION") {
                getGamePlayers();
                getGameStatus();
            }
            if (scope.gameStatus === "PROGRESS") {
                getPlayerTurn();
                getMoves();
                getGameStatus();
            }
            if (scope.gameStatus === "FINISH" || scope.gameStatus === "CANCEL" || scope.gameStatus === "ERROR") {
                stopTimer();
            }
        }, 500);

        function stopTimer() {
            interval.cancel(timerUpdate);
        }
    }]);
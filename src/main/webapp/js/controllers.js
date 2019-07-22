var gameController = angular.module('gameController', []);
var roomController = angular.module('roomController', []);

gameController.controller('gameController', ['$scope', '$http', '$interval',
    function (scope, http, interval) {

        getGameStatus();

        if (isEndGame()){
            initFirstData();
        }else{
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
            http.get('/api/game/turn')
                .then(function onSuccess(response) {
                    scope.playerTurn = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load player turn " + response.status;
                    setStatus("ERROR");
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
            http.get('/api/game/creator')
                .then(function onSuccess(response) {
                    scope.creator = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load creator game " + response.status;
                    setStatus("ERROR");
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
                    setStatus("ERROR");
                });
        }

        function getGameStatus() {
            http.get('/api/game/status')
                .then(function onSuccess(response) {
                    scope.gameStatus = response.data;
                })
                .catch(function onError(response) {
                    scope.errorMessage = "Failed to load game status " + response.status;
                    setStatus("ERROR");
                });
        }

        function setStatus(status) {
            http.post('/api/game/set_status', JSON.stringify(status), {headers: {'Content-Type': 'application/json; charset=UTF-8'}});
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
                        var move = {'cellRow': cellRow, 'cellColumn': cellColumn};

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

roomController.controller('roomController', ['$scope', '$http', '$interval',
    function (scope, http, interval) {
        getCurrentPlayer();
        getRegistrationGames();

        function getRegistrationGames() {
            http.get('/api/room/games')
                .then(function onSuccess(response) {
                    scope.games = response.data;
                    scope.isHost = false;
                    angular.forEach(scope.games, function (game) {
                        if (game.playerCreator.id === scope.currentPlayer.id){
                            scope.isHost = true;
                        }
                    })
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

        scope.joinGame = function joinGame(gameId) {
            http.post('/api/room/join_game', JSON.stringify(gameId), {headers: {'Content-Type': 'application/json; charset=UTF-8'}});
        }
}]);
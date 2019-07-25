package ru.ryazanov.ticktacktoe.to;

import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

public class GameStatusTO {
    private int gameId;
    private GameStatus gameStatus;

    public GameStatusTO(int gameId, GameStatus gameStatus) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
    }

    public int getGameId() {
        return gameId;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}

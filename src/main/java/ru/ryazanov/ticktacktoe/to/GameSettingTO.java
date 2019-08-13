package ru.ryazanov.ticktacktoe.to;

public class GameSettingTO {
    private int countPlayers;
    private int fieldSize;
    private int winLength;

    public GameSettingTO(int countPlayers, int fieldSize, int winLength) {
        this.countPlayers = countPlayers;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
    }

    public int getCountPlayers() {
        return countPlayers;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getWinLength() {
        return winLength;
    }
}

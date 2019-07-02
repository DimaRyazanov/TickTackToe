package ru.ryazanov.ticktacktoe.to;

public class GamePlayerTO {
    private String userName;
    private int position;
    private char symbol;

    public GamePlayerTO(String userName, int position, char symbol) {
        this.userName = userName;
        this.position = position;
        this.symbol = symbol;
    }

    public String getUserName() {
        return userName;
    }

    public int getPosition() {
        return position;
    }

    public char getSymbol() {
        return symbol;
    }
}

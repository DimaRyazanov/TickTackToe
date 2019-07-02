package ru.ryazanov.ticktacktoe.to;

public class MoveTO {
    private int id;
    private int cellRow;
    private int cellColumn;
    private char symbol;
    private String playerName;

    public MoveTO(int id, int cellRow, int cellColumn, char symbol, String playerName) {
        this.id = id;
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
        this.symbol = symbol;
        this.playerName = playerName;
    }

    public int getId() {
        return id;
    }

    public int getCellRow() {
        return cellRow;
    }

    public int getCellColumn() {
        return cellColumn;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getPlayerName() {
        return playerName;
    }
}

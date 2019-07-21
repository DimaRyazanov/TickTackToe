package ru.ryazanov.ticktacktoe.to;

public class MoveTO {
    /**
     * id MoveTO.
     */
    private int id;

    /**
     * row MoveTO.
     */
    private int cellRow;

    /**
     * cell MoveTO.
     */
    private int cellColumn;

    /**
     * symbol player MoveTO.
     */
    private char symbol;

    /**
     * player name who move.
     */
    private String playerName;

    /**
     * Constructor MoveTO.
     * @param id - id MoveTO.
     * @param cellRow - row cell move.
     * @param cellColumn - column cell move.
     * @param symbol - symbol in cell.
     * @param playerName - player who move.
     */
    public MoveTO(final int id, final int cellRow,
                  final int cellColumn, final char symbol,
                  final String playerName) {
        this.id = id;
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
        this.symbol = symbol;
        this.playerName = playerName;
    }

    /**
     * get id MoveTO.
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * get row cell MoveTO.
     * @return int cellRow.
     */
    public int getCellRow() {
        return cellRow;
    }

    /**
     * get column cell MoveTO.
     * @return int cellColumn.
     */
    public int getCellColumn() {
        return cellColumn;
    }

    /**
     * get symbol MoveTO.
     * @return char symbol.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * get name player who move.
     * @return String playerName.
     */
    public String getPlayerName() {
        return playerName;
    }
}

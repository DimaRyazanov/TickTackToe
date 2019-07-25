package ru.ryazanov.ticktacktoe.to;

import javax.validation.constraints.NotNull;

public class CreateMoveTO {

    private int gameId;

    /**
     * Row (Y coord.) move. Combine with column move.
     * Not null.
     */
    @NotNull
    private int cellRow;

    /**
     * Column (X coord.) move. Combine with row move.
     * Not null.
     */
    @NotNull
    private int cellColumn;

    /**
     * Default constructor.
     */
    public CreateMoveTO() {
    }

    /**
     * CreateMove constructor.
     * @param cellRow - row move.
     * @param cellColumn - column row.
     */
    public CreateMoveTO(@NotNull final int gameId,
                        @NotNull final int cellRow,
                        final @NotNull int cellColumn) {
        this.gameId = gameId;
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
    }

    /**
     * get row createMoveTO.
     * @return int cellRow.
     */
    public int getCellRow() {
        return cellRow;
    }

    /**
     * set new row createMoveTO.
     * @param cellRow - row move.
     */
    public void setCellRow(final int cellRow) {
        this.cellRow = cellRow;
    }

    /**
     * get column createMoveTO.
     * @return int cellColumn.
     */
    public int getCellColumn() {
        return cellColumn;
    }

    /**
     * set new column createMoveTO.
     * @param cellColumn - column move.
     */
    public void setCellColumn(final int cellColumn) {
        this.cellColumn = cellColumn;
    }

    public int getGameId() {
        return gameId;
    }
}

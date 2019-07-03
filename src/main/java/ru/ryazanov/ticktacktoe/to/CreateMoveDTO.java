package ru.ryazanov.ticktacktoe.to;


import javax.validation.constraints.NotNull;

public class CreateMoveDTO {
    @NotNull
    private int cellRow;
    @NotNull
    private int cellColumn;

    public CreateMoveDTO() {
    }

    public CreateMoveDTO(@NotNull int cellRow, @NotNull int cellColumn) {
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
    }

    public int getCellRow() {
        return cellRow;
    }

    public void setCellRow(int cellRow) {
        this.cellRow = cellRow;
    }

    public int getCellColumn() {
        return cellColumn;
    }

    public void setCellColumn(int cellColumn) {
        this.cellColumn = cellColumn;
    }
}

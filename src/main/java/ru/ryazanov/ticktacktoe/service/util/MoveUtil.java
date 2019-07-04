package ru.ryazanov.ticktacktoe.service.util;

import ru.ryazanov.ticktacktoe.model.Move;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public final class MoveUtil {

    private MoveUtil() {
    }

    /**
     * Find finish game or not.
     * Select move. Build vertical, horizontal and 2 diagonal
     * lines on size length. Check if moves contains coordinates
     * of build finish lines. If true, than it finish game.
     * Another - game is not finish.
     *
     * @param moves - list moves in game.
     * @param size  - length win line.
     * @return boolean finish.
     */
    public static boolean isFinishGame(final List<Move> moves, final int size) {
        List<Position> positions = moves
                .stream()
                .map(x -> new Position(x.getCellRow(), x.getCellColumn()))
                .collect(Collectors.toList());

        for (Position position
                : positions) {
            List<Position> searchPositionRow = new CopyOnWriteArrayList<>();
            List<Position> searchPositionColumn = new CopyOnWriteArrayList<>();
            List<Position> searchPositionDiagonal = new CopyOnWriteArrayList<>();
            List<Position> searchPositionDiagonalTwo = new CopyOnWriteArrayList<>();

            for (int i = 1; i < size; i++) {
                searchPositionRow.add(new Position(position.row, position.column + i));
                searchPositionColumn.add(new Position(position.row + i, position.column));
                searchPositionDiagonal.add(new Position(position.row + i, position.column + i));
                searchPositionDiagonalTwo.add(new Position(position.row + i, position.column - i));
            }

            if (positions.containsAll(searchPositionRow)
                    || positions.containsAll(searchPositionColumn)
                    || positions.containsAll(searchPositionDiagonal)
                    || positions.containsAll(searchPositionDiagonalTwo)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Auxiliary class for MoveUtil.
     * Map rowCell and columnCell to Position.
     */
    static class Position {
        /**
         * row in game board.
         */
        private final int row;
        /**
         * column in game board.
         */
        private final int column;

        Position(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return row == position.row
                    && column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}

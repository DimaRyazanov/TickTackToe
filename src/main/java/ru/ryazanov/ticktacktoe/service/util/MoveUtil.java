package ru.ryazanov.ticktacktoe.service.util;

import ru.ryazanov.ticktacktoe.model.Move;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MoveUtil {
    public static boolean isFinishGame(List<Move> moves, int size) {
        List<Position> positions = moves.stream().map(x -> new Position(x.getCellRow(), x.getCellColumn())).collect(Collectors.toList());

        for (Position position :
                positions) {
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

            if (positions.containsAll(searchPositionRow) || positions.containsAll(searchPositionColumn)
                    || positions.containsAll(searchPositionDiagonal) || positions.containsAll(searchPositionDiagonalTwo)){
                return true;
            }
        }

        return false;
    }

    static class Position{
        private int row;
        private int column;

        Position(int row, int column) {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row &&
                    column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}

package ru.ryazanov.ticktacktoe.util;

import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class GameBoardUtil {
    public static Player getPlayerTurn(List<GamePlayer> gamePlayerList, List<Move> moves){

        if(moves.isEmpty() || gamePlayerList.isEmpty()) return null;

        int minPosition = gamePlayerList.stream().mapToInt(GamePlayer::getPosition).min().orElse(1);
        int maxPosition = gamePlayerList.stream().mapToInt(GamePlayer::getPosition).max().orElse(1);

        Player lastMovePlayer = moves.stream().max(Comparator.comparing(Move::getCreated)).get().getPlayer();

        if (lastMovePlayer == null) return null;
        GamePlayer gamePlayer = gamePlayerList.stream().filter(x -> x.getPlayer().getId() == lastMovePlayer.getId()).findFirst().get();

        if(gamePlayer == null) return null;
        int currentPosition = gamePlayer.getPosition();

        if (currentPosition == maxPosition)
            currentPosition = minPosition;
        else
            currentPosition++;

        int finalCurrentPosition = currentPosition;
        return gamePlayerList.stream().filter(x -> x.getPosition() == finalCurrentPosition).findFirst().get().getPlayer();
    }

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

    protected static class Position{
        private int row;
        private int column;

        public Position(int row, int column) {
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

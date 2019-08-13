package ru.ryazanov.ticktacktoe.service.interfaces;

import ru.ryazanov.ticktacktoe.model.Game;

import java.util.List;

public interface RoomGameService {
    List<Game> getRegistrationGames();
    void joinToGame(int gameId);
    boolean isCurrentPlayerPlay();
    Game createNewGame(int countPlayers, int fieldSize, int winLength);
    Game get(Integer gameId);
    List<Game> getFinishedGames();
}

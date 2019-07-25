package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

public interface GamePlayerService {
    /**
     * Get all GamePlayers from repository by game.
     * @param game - Game object.
     * @return list GamePlayer
     */
    List<GamePlayer> getAllByGame(Game game);
    boolean isFullGame(Game game);
    int getLastPosition(Game game);
    boolean isPlayerInGame(Game game, Player player);
    void add(GamePlayer gamePlayer);
    Game playerInAnyGame(Player player);
    List<GamePlayer> getAllByPlayer(Player player);
}

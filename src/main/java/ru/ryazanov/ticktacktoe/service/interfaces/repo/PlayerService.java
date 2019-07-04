package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

public interface PlayerService {
    /**
     * get current player from repo (delete in future).
     * @return Player map to PlayerTO.
     */
    PlayerTO getCurrentPlayer();

    /**
     * Get player who create game.
     * @param currentGame - game where find.
     * @return Player map to PlayerTO.
     */
    PlayerTO getPlayerCreator(Game currentGame);

    /**
     * get logged player.
     * @return Player.
     */
    Player getLoggedPlayer();
}

package ru.ryazanov.ticktacktoe.service.interfaces;

import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

public interface ObserverInGameService {
    /**
     * get current player in system.
     * Analog logged (may be delete?).
     * @return PlayerTO object.
     */
    PlayerTO getCurrentPlayer();

    /**
     * get player, who must move in this turn.
     * @param gameId - int id Game.
     * @param lastPlayerMove - Player, who move last.
     * @return PlayerTO object.
     */
    PlayerTO getPlayerTurn(int gameId, Player lastPlayerMove);

    /**
     * Get player who create game.
     * @param gameId - int id Game.
     * @return PlayerTO object.
     */
    PlayerTO getPlayerCreator(int gameId);

    /**
     * Get logged player.
     * @return PlayerTO object.
     */
    Player getLoggedPlayer();
}

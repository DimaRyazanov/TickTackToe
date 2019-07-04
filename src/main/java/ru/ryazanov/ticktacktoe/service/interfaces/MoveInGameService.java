package ru.ryazanov.ticktacktoe.service.interfaces;

import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;

import java.util.List;

public interface MoveInGameService {
    /**
     * Get status game by id.
     * @param gameId - int id game.
     * @return enum GameStatus.
     */
    GameStatus getGameStatus(int gameId);

    /**
     * Set new status game by id.
     * @param gameId - int id game.
     * @param gameStatus - new status game.
     */
    void setGameStatus(int gameId, GameStatus gameStatus);

    /**
     * get player, who move last in game by id.
     * @param gameId - int id game.
     * @return Player object.
     */
    Player getLastTurnPlayer(int gameId);

    /**
     * get list GamePlayersTO by game id.
     * @param gameId - int id game.
     * @return List GamePlayerTO.
     */
    List<GamePlayerTO> getGamePlayers(int gameId);

    /**
     * get list MoveTO by game id.
     * @param gameId - int id game.
     * @return List MoveTO.
     */
    List<MoveTO> getMoves(int gameId);

    /**
     * Create new move player.
     * @param gameId - int id game.
     * @param createMoveDTO - new move.
     * @param player - player who move.
     */
    void createMove(int gameId, CreateMoveDTO createMoveDTO, Player player);
}

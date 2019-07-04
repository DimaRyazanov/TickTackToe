package ru.ryazanov.ticktacktoe.service.implementation;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GamePlayerService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;
import ru.ryazanov.ticktacktoe.service.interfaces.ObserverInGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.PlayerService;
import ru.ryazanov.ticktacktoe.service.util.ObserverUtil;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

@Service
public class ObserverInGameServiceImpl implements ObserverInGameService {

    /**
     * PlayerService - work with player repository.
     */
    private final PlayerService playerService;

    /**
     * GameService - work with game repository.
     */
    private final GameService gameService;

    /**
     * GamePlayerService - work with gamePlayer repository.
     */
    private final GamePlayerService gamePlayerService;

    /**
     * Constructor ObserverInGameServiceImpl.
     * Injects PlayerService, GameService and GamePlayerService implementations.
     * @param playerService - impl PlayerService.
     * @param gameService - impl GameService.
     * @param gamePlayerService - impl GamePlayerService.
     */
    public ObserverInGameServiceImpl(final PlayerService playerService,
                                     final GameService gameService,
                                     final GamePlayerService gamePlayerService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.gamePlayerService = gamePlayerService;
    }

    /**
     * get current player in system.
     * Analog logged (may be delete?).
     *
     * @return PlayerTO object.
     */
    @Override
    public PlayerTO getCurrentPlayer() {
        return playerService.getCurrentPlayer();
    }

    /**
     * get player, who must move in this turn.
     *
     * @param gameId         - int id Game.
     * @param lastPlayerMove - Player, who move last.
     * @return PlayerTO object.
     */
    @Override
    public PlayerTO getPlayerTurn(final int gameId, @Nullable final Player lastPlayerMove) {
        if (lastPlayerMove == null) {
            return getPlayerCreator(gameId);
        }

        Player playerTurn = ObserverUtil
                .getTurn(gamePlayerService.getAllByGame(gameService.get(gameId)), lastPlayerMove);
        if (playerTurn == null) {
            return getPlayerCreator(gameId);
        }

        return new PlayerTO(playerTurn.getId(), playerTurn.getUserName());
    }

    /**
     * Get player who create game.
     *
     * @param gameId - int id Game.
     * @return PlayerTO object.
     */
    @Override
    public PlayerTO getPlayerCreator(final int gameId) {
        return playerService.getPlayerCreator(gameService.get(gameId));
    }

    /**
     * Get logged player.
     *
     * @return PlayerTO object.
     */
    @Override
    public Player getLoggedPlayer() {
        return playerService.getLoggedPlayer();
    }
}

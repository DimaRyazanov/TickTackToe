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

    private PlayerService playerService;
    private GameService gameService;
    private GamePlayerService gamePlayerService;

    public ObserverInGameServiceImpl(PlayerService playerService, GameService gameService, GamePlayerService gamePlayerService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.gamePlayerService = gamePlayerService;
    }

    @Override
    public PlayerTO getCurrentPlayer() {
        return playerService.getCurrentPlayer();
    }

    @Override
    public PlayerTO getPlayerTurn(int gameId, @Nullable Player lastPlayerMove) {
        if (lastPlayerMove == null)
            return getPlayerCreator(gameId);

        Player playerTurn = ObserverUtil.getTurn(gamePlayerService.getAllByGame(gameService.get(gameId)), lastPlayerMove);
        if (playerTurn == null)
            return getPlayerCreator(gameId);

        return new PlayerTO(playerTurn.getId(), playerTurn.getUserName());
    }

    @Override
    public PlayerTO getPlayerCreator(int gameId) {
        return playerService.getPlayerCreator(gameService.get(gameId));
    }

    @Override
    public Player getLoggedPlayer() {
        return playerService.getLoggedPlayer();
    }
}

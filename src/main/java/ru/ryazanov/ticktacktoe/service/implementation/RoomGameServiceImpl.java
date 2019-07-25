package ru.ryazanov.ticktacktoe.service.implementation;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.exeptions.FullGameException;
import ru.ryazanov.ticktacktoe.service.exeptions.PlayerInGameException;
import ru.ryazanov.ticktacktoe.service.interfaces.RoomGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GamePlayerService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.PlayerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.ryazanov.ticktacktoe.util.EntityUtil.*;

@Service
public class RoomGameServiceImpl implements RoomGameService {

    private final GameService gameService;
    private final PlayerService playerService;
    private final GamePlayerService gamePlayerService;

    public RoomGameServiceImpl(final GameService gameService,
                               PlayerService playerService,
                               GamePlayerService gamePlayerService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.gamePlayerService = gamePlayerService;
    }

    @Override
    public List<Game> getRegistrationGames() {
        return gameService.getGamesByStatus(GameStatus.REGISTRATION);
    }

    @Override
    public void joinToGame(final int gameId) {
        Game currentGame = gameService.get(gameId);
        if (gamePlayerService.isFullGame(currentGame)) {
            throw new FullGameException();
        }
        if (gamePlayerService.isPlayerInGame(currentGame, playerService.getLoggedPlayer())) {
            throw new PlayerInGameException();
        }
        int position = gamePlayerService.getLastPosition(currentGame) + 1;

        gamePlayerService.add(new GamePlayer(currentGame,
                playerService.getLoggedPlayer(), position, SYMBOLS.get(position)));
    }

    @Override
    public boolean isCurrentPlayerPlay() {
        return gamePlayerService.playerInAnyGame(playerService.getLoggedPlayer()) != null;
    }

    @Override
    public Game createNewGame() {
        Game game = new Game(LocalDateTime.now(), playerService.getLoggedPlayer(), GameStatus.REGISTRATION, MIN_PLAYERS);
        gameService.save(game);
        gamePlayerService.add(new GamePlayer(game, playerService.getLoggedPlayer(), MIN_POSITION, SYMBOLS.get(MIN_POSITION)));
        return game;
    }

    @Override
    public Game get(Integer gameId) {
        return gameService.get(gameId);
    }

    @Override
    public List<Game> getFinishedGames() {
        return gamePlayerService.getAllByPlayer(playerService.getLoggedPlayer())
                .stream()
                .filter(x -> x.getGame().getStatus() == GameStatus.FINISH)
                .map(GamePlayer::getGame)
                .collect(Collectors.toList());
    }


}

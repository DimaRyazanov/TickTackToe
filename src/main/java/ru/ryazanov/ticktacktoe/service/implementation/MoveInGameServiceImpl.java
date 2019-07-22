package ru.ryazanov.ticktacktoe.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GamePlayerService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;
import ru.ryazanov.ticktacktoe.service.interfaces.MoveInGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.MoveService;
import ru.ryazanov.ticktacktoe.service.util.MoveUtil;
import ru.ryazanov.ticktacktoe.to.CreateMoveTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.ryazanov.ticktacktoe.util.EntityUtil.GAME_SIZE;

@Service
public class MoveInGameServiceImpl implements MoveInGameService {

    /**
     * GameService - work with game repository.
     */
    private final GameService gameService;

    /**
     * MoveService - work with move repository.
     */
    private final MoveService moveService;

    /**
     * GamePlayerService - work with gamePlayer repository.
     */
    private final GamePlayerService gamePlayerService;

    /**
     * Constructor MoveInGameServiceImpl.
     * Inject implementation GameService, MoveService, GamePlayerService.
     * @param gameService - impl GameService.
     * @param moveService - impl MoveService.
     * @param gamePlayerService - impl GamePlayerService.
     */
    public MoveInGameServiceImpl(final GameService gameService,
                                 final MoveService moveService,
                                 final GamePlayerService gamePlayerService) {
        this.gameService = gameService;
        this.moveService = moveService;
        this.gamePlayerService = gamePlayerService;
    }

    /**
     * Get status game by id.
     * @param gameId - int id game.
     * @return enum GameStatus.
     */
    @Override
    public GameStatus getGameStatus(final int gameId) {
        return gameService.get(gameId).getStatus();
    }

    /**
     * Set new status game by id.
     * @param gameId - int id game.
     * @param gameStatus - new status game.
     */
    @Override
    @Transactional
    public void setGameStatus(final int gameId, final GameStatus gameStatus) {
        Game currentGame = gameService.get(gameId);
        currentGame.setStatus(gameStatus);
        gameService.save(currentGame);
    }

    /**
     * get player, who move last in game by id.
     * @param gameId - int id game.
     * @return Player object.
     */
    @Override
    public Player getLastTurnPlayer(final int gameId) {
        Optional<Move> lastMove = moveService.findByGame(gameService.get(gameId))
                .stream().max(Comparator.comparing(Move::getCreated));
        return lastMove.map(Move::getPlayer).orElse(null);
    }

    /**
     * get list GamePlayersTO by game id.
     * @param gameId - int id game.
     * @return List GamePlayerTO.
     */
    @Override
    public List<GamePlayerTO> getGamePlayers(final int gameId) {
        return gamePlayerService.getAllByGame(gameService.get(gameId)).stream()
                .map(x -> new GamePlayerTO(x.getPlayer().getUserName(),
                        x.getPosition(), x.getSymbol()))
                .sorted(Comparator.comparing(GamePlayerTO::getPosition))
                .collect(Collectors.toList());
    }

    /**
     * get list MoveTO by game id.
     * @param gameId - int id game.
     * @return List MoveTO.
     */
    @Override
    public List<MoveTO> getMoves(final int gameId) {
        Map<Integer, Character> settings = gamePlayerService.getAllByGame(gameService.get(gameId))
                .stream().collect(Collectors.toMap(GamePlayer::getId, GamePlayer::getSymbol));
        List<Move> moves = moveService.findByGame(gameService.get(gameId));
        return moves.stream().map(x -> new MoveTO(x.getId(), x.getCellRow(), x.getCellColumn(),
                settings.get(x.getPlayer().getId()), x.getPlayer().getUserName()))
                .sorted(Comparator.comparingInt(MoveTO::getId)).collect(Collectors.toList());
    }

    /**
     * Create new move player.
     * @param gameId - int id game.
     * @param createMoveTO - new move.
     * @param player - player who move.
     */
    @Override
    @Transactional
    public void createMove(final int gameId,
                           final CreateMoveTO createMoveTO, final Player player) {
        Game currentGame = gameService.get(gameId);
        moveService.save(new Move(gameService.get(gameId),
                player,
                createMoveTO.getCellRow(),
                createMoveTO.getCellColumn(),
                LocalDateTime.now()));
        List<Move> moves = moveService.findByGameAndPlayer(currentGame, player);
        boolean isFinish = MoveUtil.isFinishGame(moves, GAME_SIZE);

        if (isFinish) {
            currentGame.setStatus(GameStatus.FINISH);
            gameService.save(currentGame);
        }
    }


}

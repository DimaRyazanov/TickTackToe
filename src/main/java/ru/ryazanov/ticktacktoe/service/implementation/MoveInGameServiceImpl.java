package ru.ryazanov.ticktacktoe.service.implementation;

import org.springframework.stereotype.Service;
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
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
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

    private GameService gameService;
    private MoveService moveService;
    private GamePlayerService gamePlayerService;

    public MoveInGameServiceImpl(GameService gameService, MoveService moveService, GamePlayerService gamePlayerService) {
        this.gameService = gameService;
        this.moveService = moveService;
        this.gamePlayerService = gamePlayerService;
    }


    @Override
    public GameStatus getGameStatus(int gameId) {
        return gameService.get(gameId).getStatus();
    }

    @Override
    public void setGameStatus(int gameId, GameStatus gameStatus) {
        Game currentGame = gameService.get(gameId);
        currentGame.setStatus(gameStatus);
        gameService.save(currentGame);
    }

    @Override
    public Player getLastTurnPlayer(int gameId) {
        Optional<Move> lastMove = moveService.findByGame(gameService.get(gameId)).stream().max(Comparator.comparing(Move::getCreated));
        return lastMove.map(Move::getPlayer).orElse(null);
    }

    @Override
    public List<GamePlayerTO> getGamePlayers(int gameId) {
        return gamePlayerService.getAllByGame(gameService.get(gameId)).stream()
                .map(x -> new GamePlayerTO(x.getPlayer().getUserName(), x.getPosition(), x.getSymbol()))
                .sorted(Comparator.comparing(GamePlayerTO::getPosition)).collect(Collectors.toList());
    }

    @Override
    public List<MoveTO> getMoves(int gameId) {
        Map<Integer, Character> settings = gamePlayerService.getAllByGame(gameService.get(gameId))
                .stream().collect(Collectors.toMap(GamePlayer::getId, GamePlayer::getSymbol));
        List<Move> moves = moveService.findByGame(gameService.get(gameId));
        return moves.stream().map(x -> new MoveTO(x.getId(), x.getCellRow(), x.getCellColumn(), settings.get(x.getPlayer().getId()), x.getPlayer().getUserName()))
                .sorted(Comparator.comparingInt(MoveTO::getId)).collect(Collectors.toList());
    }

    @Override
    public void createMove(int gameId, CreateMoveDTO createMoveDTO, Player player) {
        Game currentGame = gameService.get(gameId);
        moveService.save(new Move(gameService.get(gameId), player, createMoveDTO.getCellRow(), createMoveDTO.getCellColumn(), LocalDateTime.now()));
        List<Move> moves = moveService.findByGameAndPlayer(currentGame, player);
        boolean isFinish = MoveUtil.isFinishGame(moves, GAME_SIZE);

        if (isFinish) {
            currentGame.setStatus(GameStatus.FINISH);
            gameService.save(currentGame);
        }
    }


}

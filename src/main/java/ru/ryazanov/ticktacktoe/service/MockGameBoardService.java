package ru.ryazanov.ticktacktoe.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.repository.GamePlayerRepository;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.repository.MoveRepository;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;
import ru.ryazanov.ticktacktoe.security.ContextUser;
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;
import ru.ryazanov.ticktacktoe.to.PlayerTO;
import ru.ryazanov.ticktacktoe.util.GameBoardUtil;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MockGameBoardService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final MoveRepository moveRepository;

    public MockGameBoardService(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, MoveRepository moveRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.moveRepository = moveRepository;
    }

    public PlayerTO getCurrentPlayer() {
        Player currentPlayer = getLoggedPlayer();
        return new PlayerTO(currentPlayer.getId(), currentPlayer.getUserName());
    }

    private Player getLoggedPlayer() {
        ContextUser principal = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(principal.getPlayer().getUserName());
    }

    public PlayerTO getPlayerTurn(int gameId) {
        Game currentGame = gameRepository.getOne(gameId);
        Player playerTurn = GameBoardUtil.getPlayerTurn(gamePlayerRepository.findByGame(currentGame), moveRepository.findByGame(currentGame));
        if (playerTurn == null) {
            Player playerCreator = currentGame.getPlayerCreator();
            return new PlayerTO(playerCreator.getId(), playerCreator.getUserName());
        }
        return new PlayerTO(playerTurn.getId(), playerTurn.getUserName());
    }

    public List<MoveTO> getMoves(int gameId) {
        Game currentGame = gameRepository.getOne(gameId);
        Map<Integer, Character> settings = gamePlayerRepository.findByGame(currentGame).stream().collect(Collectors.toMap(GamePlayer::getId, GamePlayer::getSymbol));
        List<Move> moves = moveRepository.findByGame(currentGame);
        return moves.stream().map(x -> new MoveTO(x.getId(), x.getCellRow(), x.getCellColumn(), settings.get(x.getPlayer().getId()), x.getPlayer().getUserName()))
                .sorted(Comparator.comparingInt(MoveTO::getId)).collect(Collectors.toList());
    }

    public void createMove(int idGame, CreateMoveDTO createMoveDTO) {
        Game currentGame = gameRepository.getOne(idGame);
        moveRepository.save(new Move(currentGame, getLoggedPlayer(), createMoveDTO.getCellRow(), createMoveDTO.getCellColumn(), LocalDateTime.now()));
        List<Move> moves = moveRepository.findByGameAndPlayer(currentGame, getLoggedPlayer());
        boolean isFinish = GameBoardUtil.isFinishGame(moves, 3);

        if (isFinish) {
            currentGame.setStatus(GameStatus.FINISH);
            gameRepository.save(currentGame);
        }
    }

    public GameStatus getGameStatus(int gameId) {
        return gameRepository.getOne(gameId).getStatus();
    }

    public List<GamePlayerTO> getGamePlayers(int gameId) {
        Game currentGame = gameRepository.getOne(gameId);
        return gamePlayerRepository.findByGame(currentGame).stream()
                .map(x -> new GamePlayerTO(x.getPlayer().getUserName(), x.getPosition(), x.getSymbol()))
                .sorted(Comparator.comparing(GamePlayerTO::getPosition)).collect(Collectors.toList());
    }

    public void setGameStatus(int gameId, GameStatus gameStatus) {
        Game currentGame = gameRepository.getOne(gameId);
        currentGame.setStatus(gameStatus);
        gameRepository.save(currentGame);
    }

    public PlayerTO getPlayerCreator(int gameId) {
        Game currentGame = gameRepository.getOne(gameId);
        return new PlayerTO(currentGame.getPlayerCreator().getId(), currentGame.getPlayerCreator().getUserName());
    }
}

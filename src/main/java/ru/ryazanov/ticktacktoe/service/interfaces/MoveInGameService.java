package ru.ryazanov.ticktacktoe.service.interfaces;

import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;

import java.util.List;

public interface MoveInGameService {
    GameStatus getGameStatus(int gameId);
    void setGameStatus(int gameId, GameStatus gameStatus);
    Player getLastTurnPlayer(int gameId);
    List<GamePlayerTO> getGamePlayers(int gameId);
    List<MoveTO> getMoves(int gameId);
    void createMove(int gameId, CreateMoveDTO createMoveDTO, Player player);
}

package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

public interface MoveService {
    List<Move> findByGame(Game game);
    void save(Move move);
    List<Move> findByGameAndPlayer(Game game, Player player);
}

package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

public interface MoveService {
    /**
     * Find all moves by game from Game repo.
     * @param game - game where moves.
     * @return list moves.
     */
    List<Move> findByGame(Game game);

    /**
     * Save new move.
     * @param move - new move.
     */
    void save(Move move);

    /**
     * Find all player move in game from Game repository.
     * @param game - game where moves.
     * @param player - player who move.
     * @return list moves.
     */
    List<Move> findByGameAndPlayer(Game game, Player player);
}

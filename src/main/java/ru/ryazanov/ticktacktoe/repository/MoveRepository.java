package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Integer> {
    /**
     * Jpa Repository for work with Move Entities. Search moves by game.
     * @param game - game where search moves for all players.
     * @return list all moves for game.
     */
    List<Move> findByGame(Game game);

    /**
     * Search moves by game and player.
     * @param game - game where search moves for all players.
     * @param player - player which search moves by game.
     * @return list moves current player in game.
     */
    List<Move> findByGameAndPlayer(Game game, Player player);
}

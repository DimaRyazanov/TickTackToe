package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {
    /**
     * Jpa Repository for work with GamePlayer entities.
     * @param game - game where found gamePlayers entities.
     * @return List of GamePlayer. Need to registration game status and
     * defenition next turn.
     */
    List<GamePlayer> findByGame(Game game);
    boolean existsByGameAndPlayer(Game game, Player player);
}

package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

import java.util.List;

/**
 * Jpa Repository for work with Game entities.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByStatus(GameStatus gameStatus);
}

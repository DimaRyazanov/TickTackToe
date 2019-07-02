package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Integer> {
    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
}

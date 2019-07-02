package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;

import java.util.List;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {
    List<GamePlayer> findByGame(Game game);
}

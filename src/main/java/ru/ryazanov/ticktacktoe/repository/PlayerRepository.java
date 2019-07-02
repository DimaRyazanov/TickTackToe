package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanov.ticktacktoe.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findOneByUserName(String userName);
}

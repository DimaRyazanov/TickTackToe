package ru.ryazanov.ticktacktoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ryazanov.ticktacktoe.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * Jpa Repository for work with Player entity.
     * Search player by userName.
     * @param userName - userName Player, unique.
     * @return desired Player
     */
    Player findOneByUserName(String userName);
}

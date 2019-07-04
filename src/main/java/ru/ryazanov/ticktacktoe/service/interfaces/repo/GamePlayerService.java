package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;

import java.util.List;

public interface GamePlayerService {
    /**
     * Get all GamePlayers from repository by game.
     * @param game - Game object.
     * @return list GamePlayer
     */
    List<GamePlayer> getAllByGame(Game game);
}

package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;

public interface GameService {
    /**
     * get game from repository by game id.
     * @param gameId - int id game.
     * @return Game object.
     */
    Game get(int gameId);

    /**
     * save created/updated game.
     * @param game - saved game.
     */
    void save(Game game);
}

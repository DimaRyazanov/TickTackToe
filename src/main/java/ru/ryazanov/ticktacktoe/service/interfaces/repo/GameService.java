package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;

public interface GameService {
    Game get(int gameId);
    void save(Game game);
}

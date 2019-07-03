package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;

import java.util.List;

public interface GamePlayerService {
    List<GamePlayer> getAllByGame(Game game);
}

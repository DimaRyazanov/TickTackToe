package ru.ryazanov.ticktacktoe.service.interfaces.repo;

import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

public interface PlayerService {
    PlayerTO getCurrentPlayer();
    PlayerTO getPlayerCreator(Game currentGame);
    Player getLoggedPlayer();
}

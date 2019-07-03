package ru.ryazanov.ticktacktoe.service.interfaces;

import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

public interface ObserverInGameService {
    PlayerTO getCurrentPlayer();
    PlayerTO getPlayerTurn(int gameId, Player lastPlayerMove);
    PlayerTO getPlayerCreator(int gameId);
    Player getLoggedPlayer();
}

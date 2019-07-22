package ru.ryazanov.ticktacktoe.service.implementation;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.interfaces.RoomGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;

import java.util.List;

@Service
public class RoomGameServiceImpl implements RoomGameService {

    private final GameService gameService;

    public RoomGameServiceImpl(final GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public List<Game> getRegistrationGames() {
        return gameService.getGamesByStatus(GameStatus.REGISTRATION);
    }
}

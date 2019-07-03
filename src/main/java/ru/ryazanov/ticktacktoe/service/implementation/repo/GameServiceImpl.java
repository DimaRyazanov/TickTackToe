package ru.ryazanov.ticktacktoe.service.implementation.repo;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game get(int gameId) {
        return gameRepository.getOne(gameId);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }
}

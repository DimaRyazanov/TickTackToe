package ru.ryazanov.ticktacktoe.service.implementation.repo;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GameService;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    /**
     * GameRepository.
     */
    private final GameRepository gameRepository;

    /**
     * Constructor. Inject GameRepository.
     * @param gameRepository - impl GameRepository.
     */
    public GameServiceImpl(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * get game from repository by game id.
     * @param gameId - int id game.
     * @return Game object.
     */
    @Override
    public Game get(final int gameId) {
        return gameRepository.findById(gameId).get();
    }

    /**
     * save created/updated game.
     * @param game - saved game.
     */
    @Override
    public void save(final Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<Game> getGamesByStatus(GameStatus gameStatus) {
        return gameRepository.findByStatus(gameStatus);
    }
}

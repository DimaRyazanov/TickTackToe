package ru.ryazanov.ticktacktoe.service.implementation.repo;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.repository.GamePlayerRepository;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.GamePlayerService;

import java.util.Collections;
import java.util.List;

@Service
public class GamePlayerServiceImpl implements GamePlayerService {
    /**
     * GamePlayerRepository.
     */
    private final GamePlayerRepository gamePlayerRepository;

    /**
     * Constructor GamePlayerServiceImpl. Inject GamePlayerRepository.
     * @param gamePlayerRepository - impl GamePlayerRepository.
     */
    public GamePlayerServiceImpl(final GamePlayerRepository gamePlayerRepository) {
        this.gamePlayerRepository = gamePlayerRepository;
    }

    /**
     * Get all GamePlayers from repository by game.
     *
     * @param game - Game object.
     * @return list GamePlayer
     */
    @Override
    public List<GamePlayer> getAllByGame(final Game game) {
        if (game == null) {
            return Collections.emptyList();
        }
        return gamePlayerRepository.findByGame(game);
    }
}

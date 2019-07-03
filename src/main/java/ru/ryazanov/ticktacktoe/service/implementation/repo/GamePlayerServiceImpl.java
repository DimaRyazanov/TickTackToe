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

    private GamePlayerRepository gamePlayerRepository;

    public GamePlayerServiceImpl(GamePlayerRepository gamePlayerRepository) {
        this.gamePlayerRepository = gamePlayerRepository;
    }

    @Override
    public List<GamePlayer> getAllByGame(Game game) {
        if (game == null) return Collections.emptyList();
        return gamePlayerRepository.findByGame(game);
    }
}

package ru.ryazanov.ticktacktoe.service.implementation.repo;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Move;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.repository.MoveRepository;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.MoveService;

import java.util.List;

@Service
public class MoveServiceImpl implements MoveService {

    private MoveRepository moveRepository;

    public MoveServiceImpl(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    @Override
    public List<Move> findByGame(Game game) {
        return moveRepository.findByGame(game);
    }

    @Override
    public void save(Move move) {
        moveRepository.save(move);
    }

    @Override
    public List<Move> findByGameAndPlayer(Game game, Player player) {
        return moveRepository.findByGameAndPlayer(game, player);
    }
}

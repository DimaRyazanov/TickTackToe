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
    /**
     * MoveRepository.
     */
    private final MoveRepository moveRepository;

    /**
     * Constructor MoveServiceImpl.
     * Inject MoveRepository.
     * @param moveRepository - impl MoveRepository.
     */
    public MoveServiceImpl(final MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    /**
     * Find all moves by game from Game repo.
     * @param game - game where moves.
     * @return list moves.
     */
    @Override
    public List<Move> findByGame(final Game game) {
        return moveRepository.findByGame(game);
    }

    /**
     * Save new move.
     * @param move - new move.
     */
    @Override
    public void save(final Move move) {
        moveRepository.save(move);
    }

    /**
     * Find all player move in game from Game repository.
     * @param game - game where moves.
     * @param player - player who move.
     * @return list moves.
     */
    @Override
    public List<Move> findByGameAndPlayer(final Game game, final Player player) {
        return moveRepository.findByGameAndPlayer(game, player);
    }
}

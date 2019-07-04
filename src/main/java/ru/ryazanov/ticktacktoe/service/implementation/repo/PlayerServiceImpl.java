package ru.ryazanov.ticktacktoe.service.implementation.repo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;
import ru.ryazanov.ticktacktoe.security.ContextUser;
import ru.ryazanov.ticktacktoe.service.interfaces.repo.PlayerService;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

@Service
public class PlayerServiceImpl implements PlayerService {
    /**
     * PlayerRepository.
     */
    private final PlayerRepository playerRepository;

    /**
     * Constructor. Inject PlayerRepository.
     * @param playerRepository - impl PlayerRepository.
     */
    public PlayerServiceImpl(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * get current player from repo (delete in future).
     * @return Player map to PlayerTO.
     */
    @Override
    public PlayerTO getCurrentPlayer() {
        Player currentPlayer = getLoggedPlayer();
        return new PlayerTO(currentPlayer.getId(), currentPlayer.getUserName());
    }

    /**
     * Get player who create game.
     * @param currentGame - game where find.
     * @return Player map to PlayerTO.
     */
    @Override
    public PlayerTO getPlayerCreator(final Game currentGame) {
        return new PlayerTO(currentGame
                .getPlayerCreator().getId(), currentGame.getPlayerCreator().getUserName());
    }

    /**
     * get logged player.
     * @return Player.
     */
    @Override
    public Player getLoggedPlayer() {
        ContextUser principal = (ContextUser) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(principal.getPlayer().getUserName());
    }
}

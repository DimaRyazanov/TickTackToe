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

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerTO getCurrentPlayer() {
        Player currentPlayer = getLoggedPlayer();
        return new PlayerTO(currentPlayer.getId(), currentPlayer.getUserName());
    }

    @Override
    public PlayerTO getPlayerCreator(Game currentGame) {
        return new PlayerTO(currentGame.getPlayerCreator().getId(), currentGame.getPlayerCreator().getUserName());
    }

    @Override
    public Player getLoggedPlayer() {
        ContextUser principal = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(principal.getPlayer().getUserName());
    }
}

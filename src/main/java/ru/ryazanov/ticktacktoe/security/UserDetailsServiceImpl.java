package ru.ryazanov.ticktacktoe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public UserDetailsServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        checkNotNull(username);

        if (username.isEmpty()) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        Player player = playerRepository.findOneByUserName(username);

        if (player == null){
            throw new UsernameNotFoundException("Username: " + username + "doesn't register");
        }

        return new ContextUser(player);
    }
}

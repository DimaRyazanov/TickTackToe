package ru.ryazanov.ticktacktoe.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.ryazanov.ticktacktoe.model.Player;
import com.google.common.collect.ImmutableSet;


public class ContextUser extends User {
    /**
     * Player for user context.
     */
    private final Player player;

    /**
     * Create context user, that use for security work.
     * @param player - entry user player
     */
    ContextUser(final Player player) {
        super(player.getUserName(), player.getPassword(),
                true, true, true, true,
                ImmutableSet.of(new SimpleGrantedAuthority("create")));
        this.player = player;
    }

    /**
     * get logged user(Player entity).
     * @return Player player.
     */
    public Player getPlayer() {
        return player;
    }
}

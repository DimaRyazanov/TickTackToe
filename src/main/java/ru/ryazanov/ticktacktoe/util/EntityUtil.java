package ru.ryazanov.ticktacktoe.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EntityUtil {
    /**
     * Test player 'RYAZAN'.
     */
    public static final Player PLAYER_RYAZAN =
            new Player("ryazan", new BCryptPasswordEncoder().encode("ryazan"));
    /**
     * Test player 'SVLOYSO'.
     */
    public static final Player PLAYER_SVLOYSO
            = new Player("svloyso", new BCryptPasswordEncoder().encode("svloyso"));


    /**
     * Hardcode board game size.
     */
    public static final int MIN_GAME_SIZE = 3;

    public static final int MIN_PLAYERS = 2;

    public static final int MIN_POSITION = 1;

    public static final int MIN_WIN_LENGTH = 3;

    public static final Map<Integer, Character> SYMBOLS = new ConcurrentHashMap<>();
    static {
        SYMBOLS.put(1, 'X');
        SYMBOLS.put(2, 'O');
        SYMBOLS.put(3, 'Y');
        SYMBOLS.put(4, 'I');
        SYMBOLS.put(5, 'U');
    }

    /**
     * Hide constructor.
     */
    private EntityUtil() {

    }
}

package ru.ryazanov.ticktacktoe.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

import java.time.LocalDateTime;

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
     * Game for test (id = 1, players = 'RYAZAN, SVLOYSO').
     */
    public static final Game GAME = new Game(LocalDateTime.now(),
            EntityUtil.PLAYER_SVLOYSO, GameStatus.REGISTRATION, 2);

    /**
     * player SVLOYSO in Game (id = 1), position = 1, symbol 'X'.
     * Tests.
     */
    public static final GamePlayer FIRST_GAME_PLAYER =
            new GamePlayer(GAME, PLAYER_SVLOYSO, 1, 'X');

    /**
     * player RYAZAN in Game (id = 1), position = 2, symbol 'O'.
     * Tests.
     */
    public static final GamePlayer SECOND_GAME_PLAYER =
            new GamePlayer(GAME, PLAYER_RYAZAN, 2, 'O');

    /**
     * Hardcode board game size.
     */
    public static final int GAME_SIZE = 3;

    /**
     * Hide constructor.
     */
    private EntityUtil() {

    }
}

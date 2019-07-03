package ru.ryazanov.ticktacktoe.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

import java.time.LocalDateTime;

public class EntityUtil {
    public static Player PLAYER_RYAZAN = new Player("ryazan", new BCryptPasswordEncoder().encode("ryazan"));
    public static Player PLAYER_SVLOYSO = new Player("svloyso",   new BCryptPasswordEncoder().encode("svloyso"));

    public static Game GAME = new Game(LocalDateTime.now(), EntityUtil.PLAYER_SVLOYSO, GameStatus.REGISTRATION ,2);

    public static GamePlayer FIRST_GAME_PLAYER = new GamePlayer(GAME, PLAYER_SVLOYSO, 1, 'X');
    public static GamePlayer SECOND_GAME_PLAYER = new GamePlayer(GAME, PLAYER_RYAZAN, 2, 'O');

    public static int GAME_SIZE = 3;
}

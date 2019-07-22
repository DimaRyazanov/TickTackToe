package ru.ryazanov.ticktacktoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import ru.ryazanov.ticktacktoe.repository.GamePlayerRepository;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;

import static ru.ryazanov.ticktacktoe.util.EntityUtil.PLAYER_SVLOYSO;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.PLAYER_RYAZAN;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.GAME;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.FIRST_GAME_PLAYER;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.SECOND_GAME_PLAYER;

@Controller
@SpringBootApplication
public class GameApplication {
    /**
     * Start application. Load contexts.
     * @param args - commands.
     */
    public static void main(final String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    /**
     * Set test data (remove in future).
     * @param playerRepository - impl player repository.
     * @param gameRepository - impl game repository.
     * @param gamePlayerRepository - impl game player repository.
     * @return demo data.
     */
    @Bean
    public CommandLineRunner demo(final PlayerRepository playerRepository,
                                  final GameRepository gameRepository,
                                  final GamePlayerRepository gamePlayerRepository) {
        return (args) -> {
            //save a couple of players
            playerRepository.save(PLAYER_SVLOYSO);
            playerRepository.save(PLAYER_RYAZAN);

            gameRepository.save(GAME);

            gamePlayerRepository.save(FIRST_GAME_PLAYER);
            gamePlayerRepository.save(SECOND_GAME_PLAYER);

        };
    }
}

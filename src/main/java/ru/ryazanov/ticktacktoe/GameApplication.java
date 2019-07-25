package ru.ryazanov.ticktacktoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;

import static ru.ryazanov.ticktacktoe.util.EntityUtil.PLAYER_SVLOYSO;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.PLAYER_RYAZAN;

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
     * @return demo data.
     */
    @Bean
    public CommandLineRunner demo(final PlayerRepository playerRepository) {
        return (args) -> {
            //save a couple of players
            playerRepository.save(PLAYER_SVLOYSO);
            playerRepository.save(PLAYER_RYAZAN);

        };
    }
}

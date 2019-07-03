package ru.ryazanov.ticktacktoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import ru.ryazanov.ticktacktoe.repository.GamePlayerRepository;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;
import static ru.ryazanov.ticktacktoe.util.EntityUtil.*;

@Controller
@SpringBootApplication
public class GameApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
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

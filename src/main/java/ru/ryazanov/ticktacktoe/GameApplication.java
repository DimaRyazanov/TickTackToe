package ru.ryazanov.ticktacktoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.repository.GamePlayerRepository;
import ru.ryazanov.ticktacktoe.repository.GameRepository;
import ru.ryazanov.ticktacktoe.repository.PlayerRepository;

import java.time.LocalDateTime;

@Controller
@SpringBootApplication
public class GameApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        return (args) -> {
            Player ryazan = new Player("ryazan", new BCryptPasswordEncoder().encode("ryazan"));
            Player svloyso = new Player("svloyso",   new BCryptPasswordEncoder().encode("svloyso"));

            Game testGame = new Game(LocalDateTime.now(), svloyso, GameStatus.REGISTRATION ,2);
            //save a couple of players
            playerRepository.save(svloyso);
            playerRepository.save(ryazan);

            gameRepository.save(testGame);

            gamePlayerRepository.save(new GamePlayer(testGame, svloyso, 1, 'X'));
            gamePlayerRepository.save(new GamePlayer(testGame, ryazan, 2, 'O'));


        };
    }
}

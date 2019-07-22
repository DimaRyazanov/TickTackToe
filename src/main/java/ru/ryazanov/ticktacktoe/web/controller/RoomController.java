package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.service.interfaces.RoomGameService;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomGameService gameService;

    public RoomController(final RoomGameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getRegistrationGames(){
        return gameService.getRegistrationGames();
    }
}

package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.service.interfaces.RoomGameService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomGameService roomGameService;
    private final HttpSession httpSession;

    public RoomController(final RoomGameService roomGameService,
                          final HttpSession httpSession) {
        this.roomGameService = roomGameService;
        this.httpSession = httpSession;
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getRegistrationGames() {
        return roomGameService.getRegistrationGames();
    }

    @RequestMapping(value = "/in_game", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isCurrentPlayerInGameOrHost() {
        return roomGameService.isCurrentPlayerPlay();
    }

    @RequestMapping(value = "/join_game", method = RequestMethod.POST)
    public int joinToGame(@RequestBody final int gameId) {
        roomGameService.joinToGame(gameId);
        httpSession.setAttribute("gameId", gameId);
        return gameId;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Game createGame(@RequestBody final int count_players) {
        Game game = roomGameService.createNewGame();
        httpSession.setAttribute("gameId", game.getId());
        return game;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game getGameProperties(@PathVariable Integer id) {
        Game playerInGame = roomGameService.playerInGame();
        if (playerInGame != null && id == playerInGame.getId()) {
            httpSession.setAttribute("gameId", id);
        }
        return roomGameService.get(id);
    }
}

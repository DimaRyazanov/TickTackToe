package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ryazanov.ticktacktoe.model.Game;
import ru.ryazanov.ticktacktoe.service.exeptions.BadSettingGameException;
import ru.ryazanov.ticktacktoe.service.interfaces.RoomGameService;
import ru.ryazanov.ticktacktoe.to.GameSettingTO;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomGameService roomGameService;

    public RoomController(final RoomGameService roomGameService) {
        this.roomGameService = roomGameService;
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
        return gameId;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Game createGame(@RequestBody final GameSettingTO setting) {
        if (setting.getWinLength() <= setting.getFieldSize()
                && setting.getCountPlayers() < setting.getFieldSize()) {
            return roomGameService.createNewGame(setting.getCountPlayers(),
                    setting.getFieldSize(), setting.getWinLength());
        }

        throw new BadSettingGameException();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game getGameProperties(@PathVariable Integer id) {
        return roomGameService.get(id);
    }

    @RequestMapping(value = "/finish_games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getFinishedGames() {
        return roomGameService.getFinishedGames();
    }
}

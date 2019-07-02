package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.MockGameBoardService;
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameBoardController {

    private final MockGameBoardService mockGameBoardService;

    public GameBoardController(MockGameBoardService mockGameBoardService) {
        this.mockGameBoardService = mockGameBoardService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getCurrentPlayer(){
        return mockGameBoardService.getCurrentPlayer();
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerCreator(){
        return mockGameBoardService.getPlayerCreator(1);
    }

    @RequestMapping(value = "/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerTurn(){
        return mockGameBoardService.getPlayerTurn(1);
    }

    @RequestMapping(value = "/moves", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MoveTO> getMoves(){
        return mockGameBoardService.getMoves(1);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatus getGameStatus(){
        return mockGameBoardService.getGameStatus(1);
    }

    @RequestMapping(value = "/game_players", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GamePlayerTO> getGamePlayers(){
        return mockGameBoardService.getGamePlayers(1);
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public CreateMoveDTO createMove(@RequestBody CreateMoveDTO createMoveDTO){
        mockGameBoardService.createMove(1, createMoveDTO);
        return createMoveDTO;
    }

    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public GameStatus setGameStatus(@RequestBody GameStatus gameStatus){
        mockGameBoardService.setGameStatus(1, gameStatus);
        return gameStatus;
    }
}

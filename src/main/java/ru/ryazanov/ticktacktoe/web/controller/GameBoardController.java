package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.interfaces.MoveInGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.ObserverInGameService;
import ru.ryazanov.ticktacktoe.to.CreateMoveDTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;
import ru.ryazanov.ticktacktoe.to.PlayerTO;
import java.util.List;

import static ru.ryazanov.ticktacktoe.util.EntityUtil.GAME;

@RestController
@RequestMapping("/api/game")
public class GameBoardController {

    private final ObserverInGameService observerInGameService;
    private final MoveInGameService moveInGameService;

    public GameBoardController(ObserverInGameService observerInGameService, MoveInGameService moveInGameService) {
        this.observerInGameService = observerInGameService;
        this.moveInGameService = moveInGameService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getCurrentPlayer(){
        return observerInGameService.getCurrentPlayer();
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerCreator(){
        return observerInGameService.getPlayerCreator(GAME.getId());
    }

    @RequestMapping(value = "/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerTurn(){
        return observerInGameService.getPlayerTurn(GAME.getId(), moveInGameService.getLastTurnPlayer(GAME.getId()));
    }

    @RequestMapping(value = "/moves", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MoveTO> getMoves(){
        return moveInGameService.getMoves(GAME.getId());
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatus getGameStatus(){
        return moveInGameService.getGameStatus(GAME.getId());
    }

    @RequestMapping(value = "/game_players", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GamePlayerTO> getGamePlayers(){
        return moveInGameService.getGamePlayers(GAME.getId());
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public CreateMoveDTO createMove(@RequestBody CreateMoveDTO createMoveDTO){
        moveInGameService.createMove(GAME.getId(), createMoveDTO, observerInGameService.getLoggedPlayer());
        return createMoveDTO;
    }

    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public GameStatus setGameStatus(@RequestBody GameStatus gameStatus){
        moveInGameService.setGameStatus(GAME.getId(), gameStatus);
        return gameStatus;
    }
}

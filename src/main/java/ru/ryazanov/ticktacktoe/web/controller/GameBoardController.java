package ru.ryazanov.ticktacktoe.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;
import ru.ryazanov.ticktacktoe.service.interfaces.MoveInGameService;
import ru.ryazanov.ticktacktoe.service.interfaces.ObserverInGameService;
import ru.ryazanov.ticktacktoe.to.CreateMoveTO;
import ru.ryazanov.ticktacktoe.to.GamePlayerTO;
import ru.ryazanov.ticktacktoe.to.MoveTO;
import ru.ryazanov.ticktacktoe.to.PlayerTO;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameBoardController {

    /**
     * Service that get info about game.
     */
    private final ObserverInGameService observerInGameService;

    /**
     * Service that get info moves in game.
     */
    private final MoveInGameService moveInGameService;

    private final HttpSession httpSession;

    /**
     * Inject service implementation.
     *  @param observerInGameService - Impl ObserverInGameService.
     * @param moveInGameService     - Impl MoveInGameService.
     * @param httpSession
     */
    public GameBoardController(final ObserverInGameService observerInGameService,
                               final MoveInGameService moveInGameService, HttpSession httpSession) {
        this.observerInGameService = observerInGameService;
        this.moveInGameService = moveInGameService;
        this.httpSession = httpSession;
    }

    /**
     * Api: get current player.
     *
     * @return PlayerTO current player.
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getCurrentPlayer() {
        return observerInGameService.getCurrentPlayer();
    }

    /**
     * Api: get player who create game.
     *
     * @return PlayerTO who create game.
     */
    @RequestMapping(value = "/creator", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerCreator() {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        return observerInGameService.getPlayerCreator(gameId);
    }

    /**
     * Api: who move now in game.
     *
     * @return PlayerTO player who move.
     */
    @RequestMapping(value = "/turn", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerTO getPlayerTurn() {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        return observerInGameService.getPlayerTurn(gameId,
                moveInGameService.getLastTurnPlayer(gameId));
    }

    /**
     * Api: get all moves in game.
     *
     * @return List<MoveTO> by game id.
     */
    @RequestMapping(value = "/moves", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MoveTO> getMoves() {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        return moveInGameService.getMoves(gameId);
    }

    /**
     * Api: status game.
     *
     * @return GameStatus by game id.
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatus getGameStatus() {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        return moveInGameService.getGameStatus(gameId);
    }

    /**
     * Api: List players in game.
     *
     * @return List<GamePlayerTO> by game id.
     */
    @RequestMapping(value = "/game_players", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GamePlayerTO> getGamePlayers() {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        return moveInGameService.getGamePlayers(gameId);
    }

    /**
     * Api: create player move.
     *
     * @param createMoveTO - player move from client.
     * @return this move.
     */
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public CreateMoveTO createMove(@RequestBody final CreateMoveTO createMoveTO) {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        moveInGameService.createMove(gameId, createMoveTO,
                observerInGameService.getLoggedPlayer());
        return createMoveTO;
    }

    /**
     * Api: set new status game. For example 'ERROR'.
     *
     * @param gameStatus - new status game.
     * @return this status.
     */
    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public GameStatus setGameStatus(@RequestBody final GameStatus gameStatus) {
        int gameId = (Integer) httpSession.getAttribute("gameId");
        moveInGameService.setGameStatus(gameId, gameStatus);
        return gameStatus;
    }
}

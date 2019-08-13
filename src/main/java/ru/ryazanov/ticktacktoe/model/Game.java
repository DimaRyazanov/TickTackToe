package ru.ryazanov.ticktacktoe.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

@Entity
public class Game {
    /**
     * Id field. Unique, autoincrement. Not null.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;

    /**
     * Field responsible for the creation date of the game. Not null.
     */
    @Column(name = "created", nullable = false)
    @NotNull
    private LocalDateTime created;

    /**
     * Link to Player entity. ManyToOne link. This player creator current game.
     * Not null.
     */
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @NotNull
    private Player playerCreator;

    /**
     * Enum status of game. REGISTRATION, PROGRESS, FINISH, CANCEL, ERROR.
     */
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    /**
     * Max players can be in game. Now only 2 players, add more later...
     */
    @Column(name = "count_players", nullable = false)
    @Min(2)
    private int countPlayers;

    @Column(name = "field_size", nullable = false)
    @Min(3)
    private int fieldSize;

    @Column(name = "win_length", nullable = false)
    @Min(3)
    private int winLength;
    /**
     * Default constructor. Need for Hibernate.
     */
    public Game() {
    }

    /**
     * Game constructor.
     *
     * @param created       - Datetime, when game created.
     * @param playerCreator - Who create game.
     * @param status        - Current status game. For example - FINISH.
     * @param countPlayers  - Max players in game.
     */
    public Game(final LocalDateTime created, final Player playerCreator,
                final GameStatus status, final int countPlayers,
                final int fieldSize, final int winLength) {
        this.created = created;
        this.playerCreator = playerCreator;
        this.status = status;
        this.countPlayers = countPlayers;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
    }

    /**
     * get game id.
     *
     * @return id integer
     */
    public int getId() {
        return id;
    }

    /**
     * get game date create.
     *
     * @return LocalDateTime created.
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * get player who create game (Player class).
     *
     * @return Player playerCreator
     */
    public Player getPlayerCreator() {
        return playerCreator;
    }

    /**
     * get max count players in game.
     *
     * @return int countPlayers
     */
    public int getCountPlayers() {
        return countPlayers;
    }

    /**
     * get enum status game.
     *
     * @return GameStatus status
     */
    public GameStatus getStatus() {
        return status;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getWinLength() {
        return winLength;
    }

    /**
     * set status game.
     * for example was PROGRESS became the FINISH
     *
     * @param status - new status game (enum)
     */
    public void setStatus(final GameStatus status) {
        this.status = status;
    }
}

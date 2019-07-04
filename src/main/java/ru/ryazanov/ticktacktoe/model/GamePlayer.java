package ru.ryazanov.ticktacktoe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class GamePlayer {
    /**
     * id GamePlayer entity. Unique, autoincrement, not null.
     * List players ing game.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;

    /**
     * Link to Game entity. Many to one.
     * In which game players plays. Not null.
     */
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @NotNull
    private Game game;

    /**
     * Link to Player entity. Many to one.
     * Who play in current game. Not null.
     */
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @NotNull
    private Player player;

    /**
     * Sequence of moves. Minimum 1.
     */
    @Column(name = "position", nullable = false)
    @Min(1)
    private int position;

    /**
     * Symbol, which player made move. For example X, O, etc.
     * Not null.
     */
    @Column(name = "symbol", nullable = false)
    @NotNull
    private char symbol;

    /**
     * Default constructor. Need for Hibernate.
     */
    public GamePlayer() {
    }

    /**
     * GamePlayer constructor.
     *
     * @param game     - game, where play.
     * @param player   - who play in game.
     * @param position - who takes turns
     * @param symbol   - symbol, which player made move. For example X, O, etc.
     */
    public GamePlayer(final Game game, final Player player,
                      final int position, final char symbol) {
        this.game = game;
        this.player = player;
        this.position = position;
        this.symbol = symbol;
    }

    /**
     * Get game in GamePlayer.
     *
     * @return Game game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Get player in GamePlayer.
     *
     * @return Player player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get position player.
     *
     * @return int position.
     */
    public int getPosition() {
        return position;
    }

    /**
     * get id GamePlayer.
     *
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * get symbol player in game.
     *
     * @return char symbol.
     */
    public char getSymbol() {
        return symbol;
    }
}

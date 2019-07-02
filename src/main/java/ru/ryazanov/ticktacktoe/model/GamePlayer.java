package ru.ryazanov.ticktacktoe.model;

import javax.persistence.*;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "symbol", nullable = false)
    private char symbol;

    public GamePlayer() {
    }

    public GamePlayer(Game game, Player player, int position, char symbol) {
        this.game = game;
        this.player = player;
        this.position = position;
        this.symbol = symbol;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }
}

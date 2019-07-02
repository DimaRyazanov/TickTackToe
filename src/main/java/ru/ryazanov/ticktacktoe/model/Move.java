package ru.ryazanov.ticktacktoe.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Move {
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

    @Column(name = "cell_row", nullable = false)
    private int cellRow;

    @Column(name = "cell_column", nullable = false)
    private int cellColumn;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    public Move() {
    }

    public Move(Game game, Player player, int cellRow, int cellColumn, LocalDateTime created) {
        this.game = game;
        this.player = player;
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
        this.created = created;
    }

    public Game getGame() {
        return game;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCellRow() {
        return cellRow;
    }

    public int getCellColumn() {
        return cellColumn;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}

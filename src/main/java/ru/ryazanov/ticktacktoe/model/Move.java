package ru.ryazanov.ticktacktoe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Move {
    /**
     * id Move entity. Unique, autoincrement, not null.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;

    /**
     * Link to Game entity. ManyToOne.
     * In which game is turn. Not null.
     */
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @NotNull
    private Game game;

    /**
     * Link to Player entity. ManyToOne.
     * Which player move in current game. Not null.
     */
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @NotNull
    private Player player;

    /**
     * Row (Y coord) move. Combine with column move.
     * Minimum 0.
     */
    @Column(name = "cell_row", nullable = false)
    @Min(0)
    private int cellRow;

    /**
     * Column (X coord) move. Combine with row move.
     * Minimum 0.
     */
    @Column(name = "cell_column", nullable = false)
    @Min(0)
    private int cellColumn;

    /**
     * Date move. Not null.
     */
    @Column(name = "created", nullable = false)
    @NotNull
    private LocalDateTime created;

    /**
     * Default constructor. Need for Hibernate.
     */
    public Move() {
    }

    /**
     * Move constructor.
     *
     * @param game       - In which game move.
     * @param player     - Which player move.
     * @param cellRow    - Select row cell move.
     * @param cellColumn - Select column cell move.
     * @param created    - Date move.
     */
    public Move(final Game game, final Player player, final int cellRow,
                final int cellColumn, final LocalDateTime created) {
        this.game = game;
        this.player = player;
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;
        this.created = created;
    }

    /**
     * get game, where move happen.
     *
     * @return Game game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * get id move.
     *
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * get player, who move.
     *
     * @return Player player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * get row cell move.
     *
     * @return int cellRow.
     */
    public int getCellRow() {
        return cellRow;
    }

    /**
     * get column cell move.
     *
     * @return int cellColumn.
     */
    public int getCellColumn() {
        return cellColumn;
    }

    /**
     * Date move.
     *
     * @return LocalDateTime created.
     */
    public LocalDateTime getCreated() {
        return created;
    }
}

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


import ru.ryazanov.ticktacktoe.model.modelenum.GameStatus;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player playerCreator;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Column(name = "count_players", nullable = false)
    private int countPlayers;

    public Game() {
    }

    public Game(LocalDateTime created, Player playerCreator, GameStatus status, int countPlayers) {
        this.created = created;
        this.playerCreator = playerCreator;
        this.status = status;
        this.countPlayers = countPlayers;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Player getPlayerCreator() {
        return playerCreator;
    }

    public int getCountPlayers() {
        return countPlayers;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}

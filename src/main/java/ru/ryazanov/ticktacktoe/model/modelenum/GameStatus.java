package ru.ryazanov.ticktacktoe.model.modelenum;

public enum GameStatus {
    /**
     * Waiting players in game.
     */
    REGISTRATION,
    /**
     * Playing game.
     */
    PROGRESS,
    /**
     * The end game.
     */
    FINISH,
    /**
     * Something wrong! Error in game.
     */
    ERROR,
    /**
     * Creator cancel game (leave).
     */
    CANCEL
}

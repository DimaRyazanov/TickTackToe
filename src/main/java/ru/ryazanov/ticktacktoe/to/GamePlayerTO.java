package ru.ryazanov.ticktacktoe.to;

public class GamePlayerTO {
    /**
     * player name who playing in game.
     */
    private String userName;

    /**
     * position in game.
     * determines the sequence of turn.
     */
    private int position;

    /**
     * player symbol in game.
     * for example 'X' or 'O'.
     */
    private char symbol;

    /**
     * Constructor GamePLayerTO.
     * @param userName - player name who playing in game.
     * @param position - position in game.
     * @param symbol - player symbol in game.
     */
    public GamePlayerTO(final String userName,
                        final int position, final char symbol) {
        this.userName = userName;
        this.position = position;
        this.symbol = symbol;
    }

    /**
     * get player name GamePlayerTO.
     * @return String userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * get player position in game.
     * @return int position.
     */
    public int getPosition() {
        return position;
    }

    /**
     * get player symbol in game.
     * @return char symbol.
     */
    public char getSymbol() {
        return symbol;
    }
}

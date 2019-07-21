package ru.ryazanov.ticktacktoe.to;

public class PlayerTO {
    /**
     * id player.
     */
    private int id;

    /**
     * player name.
     */
    private String userName;

    /**
     * Constructor PlayerTO.
     * @param id - id player.
     * @param userName - name player.
     */
    public PlayerTO(final int id, final String userName) {
        this.id = id;
        this.userName = userName;
    }

    /**
     * id PlayerTO.
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * name PlayerTO.
     * @return - String userName.
     */
    public String getUserName() {
        return userName;
    }
}

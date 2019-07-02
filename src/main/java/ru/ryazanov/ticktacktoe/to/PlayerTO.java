package ru.ryazanov.ticktacktoe.to;

public class PlayerTO {
    private int id;
    private String userName;

    public PlayerTO(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}

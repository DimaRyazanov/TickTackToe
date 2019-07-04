package ru.ryazanov.ticktacktoe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Player {
    /**
     * Id field. Unique, autoincrement. Not null.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;

    /**
     * Player name. Unique, not null
     */
    @Column(name = "user_name", nullable = false, unique = true)
    @NotNull
    private String userName;

    /**
     * Password player. Link with Spring security.
     * Not null
     */
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    /**
     * Default constructor. Need for Hibernate.
     */
    public Player() {
    }

    /**
     * Player constructor.
     *
     * @param userName - name player. login for auth.
     * @param password - password player. password for auth
     */
    public Player(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * get Player id.
     *
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * get Player name.
     *
     * @return String userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * get password player.
     *
     * @return String password.
     */
    public String getPassword() {
        return password;
    }
}

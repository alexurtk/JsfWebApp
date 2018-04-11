package ru.alexurtk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by alex on 11.04.2018.
 */
@Entity
public class UserEntity {

    @Id
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

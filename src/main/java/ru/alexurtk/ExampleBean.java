package ru.alexurtk;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by alex on 10.04.2018.
 */
@Named
@SessionScoped
public class ExampleBean implements Serializable {
    private String text = "CDI Example";
    private String login;
    private String password;
    private boolean logged;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void doLogin(){
        logged = true;
    }
}

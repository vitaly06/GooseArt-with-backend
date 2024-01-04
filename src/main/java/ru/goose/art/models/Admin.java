package ru.goose.art.models;

import jakarta.validation.constraints.NotEmpty;

public class Admin {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    public Admin() {
    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

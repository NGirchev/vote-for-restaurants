package ru.girchev.restaurant.core.domain;

import javax.persistence.Entity;

/**
 * Created by Girchev N.A. on 18.11.15.
 */
@Entity
public class User extends AbstractEntity{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

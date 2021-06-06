package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Utils.Observable;

public class User extends Observable {


    private String username;

    public User() {
        username = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

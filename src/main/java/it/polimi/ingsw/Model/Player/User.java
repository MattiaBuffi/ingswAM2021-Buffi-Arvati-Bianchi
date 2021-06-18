package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Utils.Observable;

public class User extends Observable {


    private final String username;



    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



}

package it.polimi.ingsw.ServerModel;

import it.polimi.ingsw.Utils.Observable;

public class User extends Observable {

    private String userId;
    private String username;

    public User(String userId) {
        this.userId = userId;
        username = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

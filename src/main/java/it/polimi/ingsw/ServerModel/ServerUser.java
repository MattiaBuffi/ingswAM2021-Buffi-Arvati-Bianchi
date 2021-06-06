package it.polimi.ingsw.ServerModel;

import it.polimi.ingsw.Model.Player.User;

public class ServerUser extends User {

    private final String id;

    public ServerUser(String id) {
        this.id = id;
    }

    public String getUserId() {
        return id;
    }


}

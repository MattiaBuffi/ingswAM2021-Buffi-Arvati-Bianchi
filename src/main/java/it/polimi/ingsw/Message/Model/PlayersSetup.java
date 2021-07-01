package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class PlayersSetup implements Message<ModelEventHandler> {

    private static final long serialVersionUID = 7838710779065645006L;
    private String username;
    private int playPosition;


    public PlayersSetup(String username, int playPosition) {
        this.username = username;
        this.playPosition = playPosition;
    }

    public String getUsername() {
        return username;
    }

    public int getPlayPosition() {
        return playPosition;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

}
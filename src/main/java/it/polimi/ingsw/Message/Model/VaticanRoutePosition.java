package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class VaticanRoutePosition implements Message<ModelEventHandler> {

    private static final long serialVersionUID = 8700434486446518302L;
    private String username;
    private int position;

    public VaticanRoutePosition(String username, int position) {
        this.username = username;
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

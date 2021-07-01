package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class UsernameSelected implements Message<ModelEventHandler> {

    private static final long serialVersionUID = -8678642791388859648L;

    private String username;

    public UsernameSelected(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}

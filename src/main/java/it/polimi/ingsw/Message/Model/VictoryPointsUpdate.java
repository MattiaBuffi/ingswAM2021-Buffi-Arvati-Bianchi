package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class VictoryPointsUpdate implements Message<ModelEventHandler> {

    private String username;
    private int victoryPoints;

    public VictoryPointsUpdate(String username, int victoryPoints) {
        this.username = username;
        this.victoryPoints = victoryPoints;
    }

    public String getUsername() {
        return username;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}

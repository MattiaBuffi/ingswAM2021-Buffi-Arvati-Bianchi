package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class LeaderCardProduction implements Message<ClientEventHandler> {

    private int cardId;
    private String out;

    public LeaderCardProduction(int cardId, String out) {
        this.cardId = cardId;
        this.out = out;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

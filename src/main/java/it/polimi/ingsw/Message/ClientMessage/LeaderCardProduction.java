package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class LeaderCardProduction implements Message<SingleClientEventHandler> {

    private int cardId;
    private String out;

    public LeaderCardProduction(int cardId, String out) {
        this.cardId = cardId;
        this.out = out;
    }

    @Override
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}

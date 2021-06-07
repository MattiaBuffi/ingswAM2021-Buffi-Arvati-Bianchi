package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class CardProduction implements Message<SingleClientEventHandler> {
    private int cardPosition;

    public CardProduction(int cardPosition) {
        this.cardPosition = cardPosition;
    }

    @Override
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}

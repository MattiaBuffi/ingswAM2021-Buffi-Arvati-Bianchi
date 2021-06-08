package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class CardProduction implements Message<ClientEventHandler> {
    private int cardPosition;

    public CardProduction(int cardPosition) {
        this.cardPosition = cardPosition;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }

}

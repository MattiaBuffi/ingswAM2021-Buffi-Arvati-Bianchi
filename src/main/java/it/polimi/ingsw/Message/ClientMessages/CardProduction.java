package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class CardProduction implements Message<ClientEventHandler> {

    private int cardPosition;

    public CardProduction(int cardPosition) {
        this.cardPosition = cardPosition;
    }

    public int getCardPosition() {
        return cardPosition;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }

}

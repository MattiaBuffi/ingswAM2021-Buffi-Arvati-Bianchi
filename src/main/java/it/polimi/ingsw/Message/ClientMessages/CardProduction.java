package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message to report the card production from the production tab. The field is the column of the production tab where the card
 * selected is placed.
 */
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

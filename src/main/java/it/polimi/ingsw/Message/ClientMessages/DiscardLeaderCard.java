package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message to report the discard of a leader card. The field is the id of the card discarded.
 */
public class DiscardLeaderCard implements Message<ClientEventHandler> {

    private final String cardId;

    public DiscardLeaderCard(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

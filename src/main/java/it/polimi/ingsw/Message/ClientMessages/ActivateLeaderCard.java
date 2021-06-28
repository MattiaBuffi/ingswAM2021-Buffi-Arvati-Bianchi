package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message to report the activation of a leader card by the user.
 */
public class ActivateLeaderCard implements Message<ClientEventHandler> {

    private final String cardId;

    public ActivateLeaderCard(String cardId) {
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

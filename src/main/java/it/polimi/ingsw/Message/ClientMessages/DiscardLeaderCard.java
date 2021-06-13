package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

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

package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class DiscardLeaderCard implements Message<ClientEventHandler> {

    private final String cardId;

    public DiscardLeaderCard(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

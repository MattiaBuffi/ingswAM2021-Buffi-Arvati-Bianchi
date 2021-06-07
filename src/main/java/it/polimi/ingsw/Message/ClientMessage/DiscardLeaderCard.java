package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class DiscardLeaderCard implements Message<SingleClientEventHandler> {

    private final String cardId;

    public DiscardLeaderCard(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void accept(SingleClientEventHandler handler) {

    }
}

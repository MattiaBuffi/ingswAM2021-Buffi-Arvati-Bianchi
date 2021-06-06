package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.SingleClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class ActivateLeaderCard implements Message<SingleClientEventHandler> {

    private final String cardId;

    public ActivateLeaderCard(String cardId) {
        this.cardId = cardId;
    }


    @Override
    public void accept(SingleClientEventHandler handler) {

    }



}

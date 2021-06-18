package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class ActivateLeaderCard implements Message<ClientEventHandler> {

    private final String cardId;

    public ActivateLeaderCard(String cardId) {
        this.cardId = cardId;
    }


    @Override
    public void accept(ClientEventHandler handler) {

    }



}

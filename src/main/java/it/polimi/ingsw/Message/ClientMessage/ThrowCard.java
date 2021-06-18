package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class ThrowCard implements Message<ClientEventHandler> {

    private final String cardId;


    public ThrowCard(String cardId) {
        this.cardId = cardId;
    }


    @Override
    public void accept(ClientEventHandler handler) {

    }



}

package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

public class LeaderCardProduction implements Message<ClientEventHandler> {

    private String cardId;
    private Marble.Color out;


    public LeaderCardProduction(String cardId, Marble.Color out) {
        this.cardId = cardId;
        this.out = out;
    }

    public String getCardId() {
        return cardId;
    }

    public Marble.Color getOut() {
        return out;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }



}
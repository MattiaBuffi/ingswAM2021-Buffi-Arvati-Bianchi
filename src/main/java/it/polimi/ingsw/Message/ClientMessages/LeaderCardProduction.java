package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

/**
 * Message for leader card production. The fields are the id of the leader card which has the power to produce and the
 * color of the resource the player want to receive.
 */
public class LeaderCardProduction implements Message<ClientEventHandler> {

    private static final long serialVersionUID = -5573996342291865085L;
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

package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

public class DepositResource implements Message<ClientEventHandler> {

    private final Marble.Color color;
    private int shelf;

    public DepositResource(Marble.Color color, int shelf) {
        this.color = color;
        this.shelf = shelf;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        //handler.handle(this); Need to add method in the interface!!
    }
}

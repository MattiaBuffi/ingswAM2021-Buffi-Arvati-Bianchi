package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

/**
 * Message for the deposit of the resource into the shelves. The fields are the color of the resource to the deposit and the
 * number of the shelf where the user want to place it. 0 to 2 for normal shelves and eventually 3 and 4 for leader card storage.
 */
public class DepositResource implements Message<ClientEventHandler> {

    private final Marble.Color color;
    private int shelf;

    public DepositResource(Marble.Color color, int shelf) {
        this.color = color;
        this.shelf = shelf;
    }

    public Marble.Color getColor() {
        return color;
    }

    public int getShelf() {
        return shelf;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

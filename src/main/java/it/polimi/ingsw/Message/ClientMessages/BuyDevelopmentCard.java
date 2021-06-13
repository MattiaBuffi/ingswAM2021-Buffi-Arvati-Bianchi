package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class BuyDevelopmentCard implements Message<ClientEventHandler> {
    private final int x;
    private final int y;
    private final int productionColumn;

    public BuyDevelopmentCard(int x, int y, int productionColumn) {
        this.x = x;
        this.y = y;
        this.productionColumn = productionColumn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getProductionColumn() {
        return productionColumn;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

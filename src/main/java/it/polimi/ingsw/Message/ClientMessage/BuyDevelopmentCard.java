package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class BuyDevelopmentCard implements Message<SingleClientEventHandler> {
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
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}

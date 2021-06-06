package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

public class ShelfUpdate implements Message<ModelEventHandler> {

    private int position;
    private int maxSize;
    private int size;
    private Marble.Color color;

    public ShelfUpdate(int position, int maxSize, int size, Marble.Color color) {
        this.position = position;
        this.maxSize = maxSize;
        this.size = size;
        this.color = color;
    }


    public int getPosition() {
        return position;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return size;
    }

    public Marble.Color getColor() {
        return color;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
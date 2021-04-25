package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;

public interface Shelf {

    MarbleColor getColor();
    int getSize();
    int getMaxSize();
    String getID();
    boolean isFull();

    void add(MarbleColor color);
    void add(MarbleColor color, int amount);
    void remove(int amount);


}

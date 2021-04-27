package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;

public interface Shelf {

    MarbleColor getColor();
    int getSize();
    int getMaxSize();
    String getId();
    boolean isFull();

    boolean add(MarbleColor color);
    boolean add(MarbleColor color, int amount);
    boolean remove(int amount);


}

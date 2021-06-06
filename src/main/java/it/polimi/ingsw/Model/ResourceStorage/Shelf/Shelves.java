package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;

public interface Shelves {

    List<Shelf> getShelves();

    Shelf getShelf(int Position);

    ResourceList getResources();

    boolean store(Marble.Color color, int position);

    boolean withdraw(ResourceList list);

    boolean move(int origin, int dest);

}

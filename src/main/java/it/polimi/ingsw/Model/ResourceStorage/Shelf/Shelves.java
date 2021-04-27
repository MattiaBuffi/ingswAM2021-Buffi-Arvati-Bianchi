package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelf;

import java.util.List;

public interface Shelves {
    List<Shelf> getShelves();

    Shelf getFromId(String id);

    ResourceList getResources();

    boolean store(MarbleColor color, String destId);

    boolean remove(ResourceList list);

    boolean move(String originId, String destId);
}

package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelves;

public abstract class PlayerStorage implements ResourceStorage {

    public abstract Shelves getShelves();
    public abstract void setShelves(Shelves shelves);



    public abstract boolean move(String origin, String dest);
    public abstract boolean store(MarbleColor color, String dest);

}

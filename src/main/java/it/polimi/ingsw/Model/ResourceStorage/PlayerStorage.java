package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;

public abstract class PlayerStorage implements ResourceStorage {

    protected abstract Shelf getShelfFromId(String Id);
    protected abstract void removeFromChest(ResourceList list);
    protected abstract void removeFromShelf(ResourceList list);


    public abstract void move(String origin, String dest);
    public abstract boolean take(MarbleColor color, String dest);
    public abstract void storeFromMarket(ResourceList selectableResources);
    public abstract void storeFromProduction(ResourceList list);
    public abstract void emptyMarketBuffer();
    public abstract void storeProductions();

}

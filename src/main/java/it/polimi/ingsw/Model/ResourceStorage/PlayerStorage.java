package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Message.Model.ChestUpdate;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelves;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelvesBase;


public class PlayerStorage implements ResourceStorage{

    private EventBroadcaster broadcaster;

    private ResourceList chest;
    private Shelves shelves;

    public PlayerStorage(EventBroadcaster broadcaster){
        shelves = new ShelvesBase(broadcaster);
        chest = new ResourceList();
        this.broadcaster = broadcaster;
    }


    public Shelves getShelves() {
        return shelves;
    }


    public void setShelves(Shelves shelves) {
        this.shelves = shelves;
    }


    public boolean move(int originId, int destId) {
        return shelves.move(originId, destId);
    }


    public boolean store(Marble.Color color, int dest) {
        return shelves.store(color, dest);
    }



    public boolean deposit(ResourceList resourceList) {

        if( resourceList == null){
            return false;
        }

        chest = chest.sum(resourceList);
        broadcaster.notifyAllPlayers(new ChestUpdate(chest));
        return true;
    }


    public boolean withdrawal(ResourceList resourceList) {
        if(!getResources().contains(resourceList)){
            broadcaster.notifyUser(new ErrorUpdate("0", "not enough resources"));
            return false;
        }

        ResourceList missingFromShelf = shelves.getResources().difference(resourceList);
        shelves.withdraw(resourceList.subtract(missingFromShelf));
        chest = chest.subtract(missingFromShelf);

        broadcaster.notifyAllPlayers(new ChestUpdate(chest));

        return true;
    }


    public ResourceList getResources() {
        return chest.sum(shelves.getResources());
    }


}




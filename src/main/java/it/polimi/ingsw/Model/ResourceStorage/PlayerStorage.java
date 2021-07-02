package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Message.Model.ChestUpdate;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleFactory;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelf;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelves;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelvesBase;

/**
 *  Represent the deposit of resources of a player. Contain the list of shelves and the chest
 */
public class PlayerStorage implements ResourceStorage{

    private EventBroadcaster broadcaster;

    private ResourceList chest;
    private Shelves shelves;



    public PlayerStorage(EventBroadcaster broadcaster){
        shelves = new ShelvesBase(broadcaster);
        chest = new ResourceList();
        this.broadcaster = broadcaster;
    }

    /**
     * Method used only to start the server in a "CHEAT MODE" in which all the players start the game with 99 resource
     * per type
     */
    public void CHEAT_RESOURCES(){
        chest.add(Marble.Color.YELLOW, 99);
        chest.add(Marble.Color.BLUE, 99);
        chest.add(Marble.Color.GREY, 99);
        chest.add(Marble.Color.PURPLE, 99);
        deposit(new ResourceList());
        //broadcaster.notifyAllPlayers(new ChestUpdate(chest));
    }

    public Shelves getShelves() {
        return shelves;
    }

    /**
     * Set the shelves and notify the update to the players
     * @see ShelfUpdate
     */
    public void setShelves(Shelves shelves) {

        for (Shelf s: shelves.getShelves()){
            broadcaster.notifyAllPlayers(new ShelfUpdate(s.getPosition(), s.getMaxSize(), s.getSize(), s.getColor()));
        }

        this.shelves = shelves;

    }

     /**
     * Move resources from a shelf to another
     * @param originId index of the shelf from where to move the resources
     * @param destId index of the shelf where to place the resources
     * @return true if it was possible to move the resources, false if not
     */
    public boolean move(int originId, int destId) {
        return shelves.move(originId, destId);
    }

    /**
     * Add a resource of the color specified to the shelf with index equal to the one in dest parameter
     * @param color color of the resource to add
     * @param dest index of the shelf where to add the resource
     * @return true if the resource could be added and false if not
     */
    public boolean store(Marble.Color color, int dest) {
        return shelves.store(color, dest);
    }

    @Override
    public boolean deposit(ResourceList resourceList) {

        if( resourceList == null){
            return false;
        }

        chest = chest.sum(resourceList);
        broadcaster.notifyAllPlayers(new ChestUpdate(chest));
        return true;
    }

    @Override
    public boolean withdrawal(ResourceList resourceList) {
        if(!getResources().contains(resourceList)){
            broadcaster.notifyUser(new ErrorUpdate( "not enough resources"));
            return false;
        }

        ResourceList missingFromShelf = shelves.getResources().difference(resourceList);
        shelves.withdraw(resourceList.subtract(missingFromShelf));
        chest = chest.subtract(missingFromShelf);

        broadcaster.notifyAllPlayers(new ChestUpdate(chest));

        return true;
    }

    /**
     * Return all the resources in the shelves and chest
     * @return all the resources of shelves and chest summed together
     */
    public ResourceList getResources() {
        return chest.sum(shelves.getResources());
    }


}




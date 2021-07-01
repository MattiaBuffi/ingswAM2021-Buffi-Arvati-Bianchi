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
 *  deposito di risorse
 */
public class PlayerStorage implements ResourceStorage{

    private EventBroadcaster broadcaster;

    private ResourceList chest;
    private Shelves shelves;



    public PlayerStorage(EventBroadcaster broadcaster){
        shelves = new ShelvesBase(broadcaster);
        chest = new ResourceList();
        this.broadcaster = broadcaster;

        //CHEAT_RESOURCES();

    }


    public void CHEAT_RESOURCES(){
        chest.add(Marble.Color.YELLOW, 99);
        chest.add(Marble.Color.BLUE, 99);
        chest.add(Marble.Color.GREY, 99);
        chest.add(Marble.Color.PURPLE, 99);
        deposit(new ResourceList());
        //broadcaster.notifyAllPlayers(new ChestUpdate(chest));
    }

    /**
     *  ritorna gli scaffali che contiene
     */
    public Shelves getShelves() {
        return shelves;
    }

    /**
     *  setta gli scaffali di this
     */
    public void setShelves(Shelves shelves) {

        for (Shelf s: shelves.getShelves()){
            broadcaster.notifyAllPlayers(new ShelfUpdate(s.getPosition(), s.getMaxSize(), s.getSize(), s.getColor()));
        }

        this.shelves = shelves;

    }

    /**
     * muove risorse tra due scaffali
     */
    public boolean move(int originId, int destId) {
        return shelves.move(originId, destId);
    }

    /**
     *  aggiunge una risorsa ad uno scaffale
     */
    public boolean store(Marble.Color color, int dest) {
        return shelves.store(color, dest);
    }


    /**
     * deposita risorse nella chest
     */
    public boolean deposit(ResourceList resourceList) {

        if( resourceList == null){
            return false;
        }

        chest = chest.sum(resourceList);
        broadcaster.notifyAllPlayers(new ChestUpdate(chest));
        return true;
    }

    /**
     * preleva risorse da this. con priorita scaffali>chest
     */
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
     *  ritorna il totale delle risorse contenute negli scaffali e nella chest
     */
    public ResourceList getResources() {
        return chest.sum(shelves.getResources());
    }


}




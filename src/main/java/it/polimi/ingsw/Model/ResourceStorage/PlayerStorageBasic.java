package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelves;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelvesBase;


public class PlayerStorageBasic extends PlayerStorage {

    private ResourceList chest;
    private Shelves shelves;

    public PlayerStorageBasic(){
        shelves = new ShelvesBase();
        chest = new ResourceList();
    }

    @Override
    public Shelves getShelves() {
        return shelves;
    }

    @Override
    public void setShelves(Shelves shelves) {
        this.shelves = shelves;
    }

    @Override
    public boolean move(String originId, String destId) {
        return shelves.move(originId, destId);
    }

    @Override
    public boolean store(MarbleColor color, String dest) {
        return shelves.store(color, dest);
    }


    @Override
    public void deposit(ResourceList resourceList) {
        chest = chest.sum(resourceList);
    }

    @Override
    public boolean withdrawal(ResourceList resourceList) {
        if(!getResources().contains(resourceList)){
            return false;
        }
        ResourceList missingFromShelf = shelves.getResources().difference(resourceList);
        shelves.remove(resourceList.subtract(missingFromShelf));
        chest = chest.subtract(missingFromShelf);
        return true;
    }

    @Override
    public ResourceList getResources() {
        return chest.sum(shelves.getResources());
    }


}




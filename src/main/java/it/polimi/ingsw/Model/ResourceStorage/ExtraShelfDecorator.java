package it.polimi.ingsw.Model.ResourceStorage;


import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;


public class ExtraShelfDecorator extends PlayerStorage {

    private PlayerStorage storage;
    private ShelfLeader shelf;

    @Override
    protected Shelf getShelfFromId(String Id) {
        if(Id == shelf.getID()){
            return shelf;
        } else {
            return storage.getShelfFromId(Id);
        }
    }

    @Override
    protected void removeFromChest(ResourceList list) {
        storage.removeFromChest(list);
    }

    @Override
    protected void removeFromShelf(ResourceList list) {
        storage.removeFromShelf(list);
    }

    @Override
    public void move(String originId, String destId) {
        if(originId == shelf.getID()){
            Shelf dest = storage.getShelfFromId(destId);
            if(dest.getColor() != shelf.getColor()){
                return;
            }
            while (!dest.isFull() && shelf.getSize()>0){
                dest.add(shelf.getColor());
            }
        } else if (destId == shelf.getID()){
            Shelf origin = storage.getShelfFromId(originId);
            if(origin.getColor() != shelf.getColor()){
                return;
            }
            while (!shelf.isFull() && origin.getSize()>0){
                shelf.add(shelf.getColor());
            }
        } else {
            storage.move(originId, destId);
        }
    }

    @Override
    public boolean take(MarbleColor color, String dest) {
        if (dest == shelf.getID()){
            shelf.add(color);
        } else {
            storage.take(color, dest);
        }
        return true;
    }




    @Override
    public void storeFromMarket(ResourceList selectableResources) {
        storage.storeFromMarket(selectableResources);
    }

    @Override
    public void storeFromProduction(ResourceList list) {
        storage.storeFromProduction(list);
    }

    @Override
    public void emptyMarketBuffer() {
        storage.emptyMarketBuffer();
    }

    @Override
    public void storeProductions() {
        storage.storeProductions();
    }

    @Override
    public void deposit(ResourceList resourceList) {
        storage.deposit(resourceList);
    }




    @Override
    public void withdrawal(ResourceList resourceList) {
        storage.removeFromShelf(resourceList);
        //shelf.remove();
        storage.removeFromChest(resourceList);
    }

    @Override
    public ResourceList getResources() {
        return null;
    }


}

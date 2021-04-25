package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerStorageBasic extends PlayerStorage {

    private class Shelves{

        private List<ShelfBasic> shelves;

        public Shelves() {
            shelves = new ArrayList<>();
            shelves.add(new ShelfBasic(1, UUID.randomUUID().toString()));
            shelves.add(new ShelfBasic(2, UUID.randomUUID().toString()));
            shelves.add(new ShelfBasic(3, UUID.randomUUID().toString()));
        }

        public ShelfBasic getFromId(String id){
            for(ShelfBasic s: shelves){
                if(id == s.getID()){
                    return s;
                }
            }
            return null;
        }

        public ResourceList getResources(){
            ResourceList content = new ResourceList();
            for(Shelf s: shelves){
                content.add(s.getColor(), s.getSize());
            }
            return content;
        }

        public void remove(ResourceList list){
            for(MarbleColor c: list.getColors()){
                for(Shelf s: shelves){
                    if(s.getColor() == c){
                        s.remove(list.getSize(c));
                        break;
                    }
                }
            }
        }

    }

    private ResourceList chest;
    private Shelves shelves;

    private ResourceList marketBuffer;
    private ResourceList productionBuffer;

    private PlayerStorageBasic(){
        shelves = new Shelves();
        chest = new ResourceList();
    }


    @Override
    protected Shelf getShelfFromId(String Id) {
        return shelves.getFromId(Id);
    }

    @Override
    protected void removeFromChest(ResourceList list) {

    }

    @Override
    protected void removeFromShelf(ResourceList list) {

    }

    @Override
    public void move(String originId, String destId) {
        ShelfBasic origin = shelves.getFromId(originId);
        ShelfBasic dest = shelves.getFromId(destId);

        int originMaxSize = origin.getMaxSize();
        int destMaxSize = dest.getMaxSize();

        if(origin.getSize() > destMaxSize){
            //error
            return;
        }
        if(dest.getSize() > originMaxSize){
            //error
            return;
        }

        origin.setMaxSize(destMaxSize);
        dest.setMaxSize(originMaxSize);

    }

    @Override
    public boolean take(MarbleColor color, String dest) {
        if(marketBuffer.getSize(color)==0){
            return false;
        }

        Shelf shelf = shelves.getFromId(dest);
        if(shelf.getColor() != color){
            return false;
        }
        if(shelf.isFull()){
            return false;
        }
        shelf.add(color);
        marketBuffer.pop(color);
        return true;
    }



    @Override
    public void storeFromMarket(ResourceList selectableResources) {
        marketBuffer = selectableResources;
    }

    @Override
    public void storeFromProduction(ResourceList list) {
        productionBuffer = list;
    }

    @Override
    public void emptyMarketBuffer() {
        marketBuffer = null;
    }

    @Override
    public void storeProductions() {
        chest = chest.sum(productionBuffer);
        productionBuffer = null;
    }




    @Override
    public void deposit(ResourceList resourceList) {
        chest = chest.sum(resourceList);
    }

    @Override
    public void withdrawal(ResourceList resourceList) {
        removeFromShelf(resourceList);
        removeFromChest(resourceList);
    }

    @Override
    public ResourceList getResources() {
        return chest.sum(shelves.getResources());
    }


}




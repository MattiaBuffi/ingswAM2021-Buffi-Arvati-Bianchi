package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;

public class ShelvesExtra implements Shelves {

    private EventBroadcaster broadcaster;

    private Shelves shelves;
    private ShelfLeader shelf;


    public ShelvesExtra(Shelves shelves, EventBroadcaster broadcaster, Marble.Color shelfColor) {
        this.shelves = shelves;
        this.shelf = new ShelfLeader(2, shelves.getShelves().size(), broadcaster ,shelfColor);
        this.broadcaster = broadcaster;
    }

    @Override
    public List<Shelf> getShelves() {
        List<Shelf> toReturn = shelves.getShelves();
        toReturn.add(shelf);
        return toReturn;
    }

    @Override
    public Shelf getShelf(int position) {

        if ( shelf.getPosition() < position ){
            throw  new IllegalArgumentException();
        }

        if( shelf.getPosition() == position ){
            return shelf;
        } else {
            return shelves.getShelf(position);
        }

    }

    @Override
    public ResourceList getResources() {
        ResourceList content = new ResourceList();
        content.add(shelf.getColor(), shelf.getSize());
        return content;
    }




    @Override
    public boolean store(Marble.Color color, int position){
        if( shelf.getPosition() != position){
            return shelves.store(color, position);
        }

        return shelf.add(color);

    }

    @Override
    public boolean withdraw(ResourceList list) {
        if(shelves.getResources().contains(list)){
            return shelves.withdraw(list);
        } else{
            ResourceList missing = shelves.getResources().difference(list);

            //check that all of the missing marble are of the correct color
            if( missing.getSize(shelf.getColor()) != missing.getSize()){
                broadcaster.notifyUser(new ErrorUpdate("not enough resources"));
                return false;
            }

            if(!shelf.remove(missing.getSize())){
                return false;
            }

            if(!shelves.withdraw(list.subtract(missing))){
                throw new IllegalStateException(); //if previous check are correct this will never occur
            }

            return true;

        }
    }

    private boolean fill(Shelf origin, Shelf dest){
        if(origin.getColor() != dest.getColor()){ // need to check if null
            broadcaster.notifyUser(new ErrorUpdate("illegal move"));
            return false;
        }
        while (!dest.isFull() && origin.getSize()>0){
            dest.add(origin.getColor());
            origin.remove(1);
        }

        broadcaster.notifyAllPlayers(new ShelfUpdate(origin.position, origin.maxSize, origin.size, origin.color)/*move successful*/);//shelf 1 update
        broadcaster.notifyAllPlayers(new ShelfUpdate(dest.position, dest.maxSize, dest.size, dest.color)/*move successful*/);//shelf 2 update
        return true;
    }

    @Override
    public boolean move(int originId, int destId) {
        if(shelf.getPosition() == originId ){               //the extra shelf is the origin
            return fill(shelf, shelves.getShelf(destId));
        } else if (shelf.getPosition() == destId){          //the extra shelf is the destination
            return fill(shelves.getShelf(originId), shelf);
        } else {
            return shelves.move(originId, destId);          //the extra shelf is neither
        }
    }


}

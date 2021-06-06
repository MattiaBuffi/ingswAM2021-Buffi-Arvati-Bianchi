package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.ArrayList;
import java.util.List;


public class ShelvesBase implements Shelves{

    private EventBroadcaster broadcaster;

    private List<ShelfBasic> shelves;

    public ShelvesBase(EventBroadcaster broadcaster) {
        this.shelves = new ArrayList<>();
        this.broadcaster = broadcaster;

        for (int i = 0; i < 3; i++) {
            shelves.add(new ShelfBasic(i+1, i, broadcaster));
        }

    }


    private void swap(int originPosition, int destPosition){

        ShelfBasic origin = getShelf(originPosition);
        ShelfBasic dest = getShelf(destPosition);

        int originMaxSize = origin.getMaxSize();
        int destMaxSize = dest.getMaxSize();

        origin.setMaxSize(destMaxSize);
        dest.setMaxSize(originMaxSize);

        shelves.set(originPosition, dest);
        shelves.set(destPosition, origin);
        origin.setPosition(destPosition);
        dest.setPosition(originPosition);

        broadcaster.notifyAllPlayers(new ShelfUpdate(origin.position, origin.maxSize, origin.size, origin.color)/*move successful*/);//shelf 1 update
        broadcaster.notifyAllPlayers(new ShelfUpdate(dest.position, dest.maxSize, dest.size, dest.color)/*move successful*/);//shelf 2 update

    }

    @Override
    public List<Shelf> getShelves() {
        return new ArrayList<>(shelves);
    }

    @Override
    public ShelfBasic getShelf(int position){
        return shelves.get(position);
    }

    @Override
    public ResourceList getResources(){
        ResourceList content = new ResourceList();
        for(Shelf s: shelves){
            content.add(s.getColor(), s.getSize());
        }
        return content;
    }


    @Override
    public boolean store(Marble.Color color, int position){
        ShelfBasic shelf = getShelf(position);

        if(shelf == null){
            broadcaster.notifyUser(new ErrorUpdate("0","shelf not found"));
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if(i == position){
                continue;
            }
            if(getShelf(i).getColor() == color){
                broadcaster.notifyUser(new ErrorUpdate("0","color already stored"));
                return false;
            }
        }

        return shelf.add(color);
    }

    @Override
    public boolean withdraw(ResourceList list){

        if(!getResources().contains(list)){
            broadcaster.notifyUser(new ErrorUpdate("0", "not enough resources"));
            return false;
        }

        for(Marble.Color c: list.getColors()){
            for(Shelf s: shelves){
                if(s.getColor() == c){
                    s.remove(list.getSize(c));
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean move(int originPosition, int destPosition){

        ShelfBasic origin = getShelf(originPosition);
        ShelfBasic dest = getShelf(destPosition);

        if(origin.getSize() > dest.getMaxSize()){
            broadcaster.notifyUser(new ErrorUpdate("0", "illegal move"));
            return false;
        }
        if(dest.getSize() > origin.getMaxSize()){
            broadcaster.notifyUser(new ErrorUpdate("0", "illegal move"));
            return false;
        }

        swap(originPosition, destPosition);


        return true;
    }



}
package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;

public class ShelfLeader extends Shelf {


    public ShelfLeader(int size, int position, EventBroadcaster broadcaster, Marble.Color color) {
        super(size, position, broadcaster);
        this.color = color;
    }

    @Override
    public boolean add(Marble.Color color) {
        return add(color,1);
    }



    @Override
    public boolean add(Marble.Color color, int amount) {

        if(this.color != color){
            broadcaster.notifyUser(new ErrorUpdate("wrong color"));
            return false;
        }

        if(isFull() || size+amount>maxSize){
            broadcaster.notifyUser(new ErrorUpdate("not enough space"));
            return false;
        }

        size += amount;
        broadcaster.notifyAllPlayers(new ShelfUpdate(position, maxSize, size, color)/*shelf new status*/);
        return true;
    }




    @Override
    public boolean remove(int amount) {
        if(size < amount){
            broadcaster.notifyUser(new ErrorUpdate( "not enough resources")/*not enough resources*/);
            return false;
        }
        size-= amount;
        broadcaster.notifyAllPlayers(new ShelfUpdate(position, maxSize, size, color)/*shelf new status*/);
        return true;
    }




}

package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;


public class ShelfBasic extends Shelf {

    private EventBroadcaster broadcaster;

    public ShelfBasic(int size, int position, EventBroadcaster broadcaster) {
        super(size, position, broadcaster);
        this.broadcaster = broadcaster;
    }


    @Override
    public boolean add(Marble.Color color) {
        return add(color,1);
    }

    @Override
    public boolean add(Marble.Color color, int amount) {

        if(isFull() || size+amount>maxSize){
            broadcaster.notifyUser(new ErrorUpdate("0", "not enough space"));
            return false;
        }

        if(size != 0){
            if(this.color != color){
                broadcaster.notifyUser(new ErrorUpdate("0", "wrong color"));
                return false;
            }
        }

        size+= amount;
        if( this.color == null){
            this.color = color;
        }

        broadcaster.notifyAllPlayers(new ShelfUpdate(position, maxSize, size, color));/*marble added*/

        return true;
    }

    @Override
    public boolean remove(int amount) {
        if(size < amount){
            broadcaster.notifyUser(new ErrorUpdate("0", "not enough resources"));
            return false;
        }

        size-= amount;

        if (size == 0){
            this.color = null;
        }

        broadcaster.notifyAllPlayers(new ShelfUpdate(position, maxSize, size, color));
        return true;
    }


}

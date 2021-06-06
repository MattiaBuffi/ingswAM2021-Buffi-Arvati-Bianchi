package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;

public abstract class Shelf {

    /*
    Marble.Color getColor();
    int getSize();
    int getMaxSize();
    boolean isFull();

    boolean add(Marble.Color color);
    boolean add(Marble.Color color, int amount);
    boolean remove(int amount);
*/


    protected EventBroadcaster broadcaster;

    protected int maxSize;
    protected Marble.Color color;
    protected int size;
    protected int position;

    public Shelf(int size, int position, EventBroadcaster broadcaster) {
        this.maxSize = size;
        this.position = position;
        this.broadcaster = broadcaster;
    }


    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Marble.Color getColor() {
        return color;
    }

    public boolean isFull() {
        if( size == maxSize){
            return true;
        } else {
            return false;
        }
    }


    public abstract boolean add(Marble.Color color);
    public abstract boolean add(Marble.Color color, int amount);
    public abstract boolean remove(int amount);



}

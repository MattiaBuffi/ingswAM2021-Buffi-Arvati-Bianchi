package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;

/**
 * Abstract class which represent a shelf of the storage
 */
public abstract class Shelf {

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

    /**
     * Return true or false if the shelf is full or not
     * @return true if the shelf is full or false if is not.
     */
    public boolean isFull() {
        if( size == maxSize){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add a resource of the specified color to the shelf
     * @param color color of the resource to add
     * @return true if the resource could be added and false if not
     */
    public abstract boolean add(Marble.Color color);

    /**
     * Add a number of resources equals to the input parameter and of the specified color to the shelf
     * @param color color of the resources to add
     * @param amount number of resources to add
     * @return true if the resource could be added and false if not
     */
    public abstract boolean add(Marble.Color color, int amount);

    /**
     * Remove a number of resource from the shelf
     * @param amount number of resource to remove
     * @return true if the resources could be removed and false if not
     */
    public abstract boolean remove(int amount);



}

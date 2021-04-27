package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;

import java.util.UUID;

public class ShelfLeader implements Shelf {

    private final int maxSize;
    private final String id;
    private final MarbleColor color;
    private int size;

    public ShelfLeader(MarbleColor color) {
        this.id = UUID.randomUUID().toString();
        this.maxSize = 2;
        this.color = color;
        this.size = 0;
    }

    @Override
    public MarbleColor getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getMaxSize(){
        return maxSize;
    }

    @Override
    public boolean isFull() {
        if( size == maxSize){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getId() {
        return id;
    }




    @Override
    public boolean add(MarbleColor color) {
        return add(color,1);
    }

    @Override
    public boolean add(MarbleColor color, int amount) {
        if(this.color != color){
            return false;
        }
        if(isFull()){
            return false;
        }
        if(size+amount>maxSize){
            return false;
        }
        size += amount;
        return true;
    }

    @Override
    public boolean remove(int amount) {
        if(size < amount){
            return false;
        }
        size-= amount;
        return true;
    }




}

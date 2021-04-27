package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;

import java.util.UUID;


public class ShelfBasic implements Shelf {


    private final String id;
    private int maxSize;
    private MarbleColor color;
    private int size;

    public ShelfBasic(int size) {
        this.maxSize = size;
        this.id = UUID.randomUUID().toString();
    }

    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }

    @Override
    public MarbleColor getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getMaxSize() {
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

        if(isFull()){
            return false;
        }
        if(size>0){
            if(this.color != color){
                return false;
            }
        }
        if(size+amount>maxSize){
            return false;
        }
        size+= amount;
        if( this.color == null){
            this.color = color;
        }
        return true;
    }

    @Override
    public boolean remove(int amount) {
        if(size < amount){
            return false;
        }
        size-= amount;
        if (size == 0){
            this.color = null;
        }
        return true;
    }

















}

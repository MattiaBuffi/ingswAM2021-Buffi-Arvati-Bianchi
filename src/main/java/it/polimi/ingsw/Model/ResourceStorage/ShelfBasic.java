package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;

public class ShelfBasic implements Shelf {


    private final String id;
    private int maxSize;
    private MarbleColor color;
    private int size;

    public ShelfBasic(int size, String id) {
        this.maxSize = size;
        this.id = id;
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
    public String getID() {
        return id;
    }




    @Override
    public void add(MarbleColor color) {
        if(isFull()){
            return;
        }
        if(size>0){
            if(this.color != color){
                return;
            }
        }
        size++;
        if( size == 0){
            this.color = color;
        }
    }

    @Override
    public void add(MarbleColor color, int amount) {
        for (int i = 0; i < amount; i++) {
            add(color);
        }
    }

    @Override
    public void remove(int amount) {
        if(size < amount){
            return;
        }
        size--;
        if (size == 0){
            this.color = null;
        }
    }

















}

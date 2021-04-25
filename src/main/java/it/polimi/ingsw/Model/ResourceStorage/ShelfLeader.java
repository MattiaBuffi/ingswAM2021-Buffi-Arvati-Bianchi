package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.MarbleColor;

public class ShelfLeader implements Shelf {

    private final int maxSize;
    private final String id;
    private final MarbleColor color;
    private int size;

    public ShelfLeader(String id, MarbleColor color) {
        this.id = id;
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
        return false;
    }

    @Override
    public String getID() {
        return id;
    }




    @Override
    public void add(MarbleColor color) {
        if(this.color != color){
            return;
        }
        if(isFull()){
            return;
        }
        size++;
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
        size-= amount;

    }




}

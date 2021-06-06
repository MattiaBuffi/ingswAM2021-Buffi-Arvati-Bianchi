package it.polimi.ingsw.View.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.Marble;

public class Shelf {

    private int position;
    private int maxSize;
    private int size;
    private Marble.Color color;

    public Shelf(int position, int maxSize, int size, Marble.Color color) {
        this.position = position;
        this.maxSize = maxSize;
        this.size = size;
        this.color = color;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public Marble.Color getColor() {
        return color;
    }

    public void setColor(Marble.Color color) {
        this.color = color;
    }

}

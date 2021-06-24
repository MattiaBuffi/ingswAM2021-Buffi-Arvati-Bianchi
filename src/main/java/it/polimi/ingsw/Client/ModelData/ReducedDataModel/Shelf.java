package it.polimi.ingsw.Client.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.Marble;

public class Shelf {

    public int position;
    public int maxSize;
    public int size;
    public Marble.Color color;

    public Shelf(int position, int maxSize, int size, Marble.Color color) {
        this.position = position;
        this.maxSize = maxSize;
        this.size = size;
        this.color = color;
    }

    public void update(int position, int maxSize, int size, Marble.Color color){
        this.position = position;
        this.maxSize = maxSize;
        this.size = size;
        this.color = color;
    }
}

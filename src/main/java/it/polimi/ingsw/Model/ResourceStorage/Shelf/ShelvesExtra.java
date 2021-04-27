package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;

public class ShelvesExtra implements Shelves {

    private Shelves shelves;
    private ShelfLeader shelf;


    public ShelvesExtra(Shelves shelves, ShelfLeader shelf) {
        this.shelves = shelves;
        this.shelf = shelf;
    }

    @Override
    public List<Shelf> getShelves() {
        List<Shelf> toReturn = shelves.getShelves();
        toReturn.add(shelf);
        return toReturn;
    }

    @Override
    public Shelf getFromId(String id) {
        if(shelf.getId() == id){
            return shelf;
        } else {
            return shelves.getFromId(id);
        }
    }

    @Override
    public ResourceList getResources() {
        ResourceList content = new ResourceList();
        content.add(shelf.getColor(), shelf.getSize());
        return content;
    }

    @Override
    public boolean store(MarbleColor color, String destId){
        if(this.shelf.getId() != destId){
            return shelves.store(color,destId);
        }
        return shelf.add(color);
    }

    @Override
    public boolean remove(ResourceList list) {
        if(shelves.getResources().contains(list)){
            return shelves.remove(list);
        } else{
            ResourceList missing = shelves.getResources().difference(list);

            //check that the missing marble are of the correct color
            if(missing.getSize(shelf.getColor()) != missing.getSize()){
                return false;
            }

            shelves.remove(list.subtract(missing));

            if(!shelf.remove(missing.getSize())){
                return false;
            }

            return true;
        }
    }

    private boolean fill(Shelf origin, Shelf dest){
        if(origin.getColor() != dest.getColor()){
            return false;
        }
        while (!dest.isFull() && origin.getSize()>0){
            dest.add(origin.getColor());
            origin.remove(1);
        }
        return true;
    }

    @Override
    public boolean move(String originId, String destId) {
        if(originId == shelf.getId()){
            return fill(shelf, shelves.getFromId(destId));
        } else if (destId == shelf.getId()){
            return fill(shelves.getFromId(originId), shelf);
        } else {
            return shelves.move(originId, destId);
        }
    }


}

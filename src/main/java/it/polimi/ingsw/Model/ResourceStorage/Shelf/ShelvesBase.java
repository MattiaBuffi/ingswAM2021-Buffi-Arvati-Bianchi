package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.ArrayList;
import java.util.List;


public class ShelvesBase implements Shelves{

    private List<ShelfBasic> shelves;

    public ShelvesBase() {
        shelves = new ArrayList<>();
        shelves.add(new ShelfBasic(1));
        shelves.add(new ShelfBasic(2));
        shelves.add(new ShelfBasic(3));
    }

    @Override
    public List<Shelf> getShelves() {
        List<Shelf> toReturn = new ArrayList<>();
        for (Shelf s: shelves){
            toReturn.add(s);
        }
        return toReturn;
    }

    @Override
    public ShelfBasic getFromId(String id){
        for(ShelfBasic s: shelves){
            if(id == s.getId()){
                return s;
            }
        }
        return null;
    }

    @Override
    public ResourceList getResources(){
        ResourceList content = new ResourceList();
        for(Shelf s: shelves){
            content.add(s.getColor(), s.getSize());
        }
        return content;
    }


    @Override
    public boolean store(MarbleColor color, String destId){
        for (Shelf s: shelves){
            if(s.getColor() == color && s.getId() != destId ){
                return false;
            }
        }
        return getFromId(destId).add(color);
    }

    @Override
    public boolean remove(ResourceList list){
        if(!getResources().contains(list)){
            return false;
        }
        for(MarbleColor c: list.getColors()){
            for(Shelf s: shelves){
                if(s.getColor() == c){
                    s.remove(list.getSize(c));
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean move(String originId, String destId){
        ShelfBasic origin = getFromId(originId);
        ShelfBasic dest = getFromId(destId);

        int originMaxSize = origin.getMaxSize();
        int destMaxSize = dest.getMaxSize();

        if(origin.getSize() > destMaxSize){
            //error
            return false;
        }
        if(dest.getSize() > originMaxSize){
            //error
            return false;
        }

        origin.setMaxSize(destMaxSize);
        dest.setMaxSize(originMaxSize);

        int originIndex = shelves.indexOf(origin);
        int destIndex = shelves.indexOf(dest);

        shelves.set(originIndex, dest);
        shelves.set(destIndex, origin);

        return true;
    }



}
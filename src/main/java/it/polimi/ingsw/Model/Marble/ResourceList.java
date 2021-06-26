package it.polimi.ingsw.Model.Marble;


import java.io.Serializable;
import java.util.*;

public class ResourceList implements Serializable {

    private Map<Marble.Color, Integer> marbles;
    private int size;

    public ResourceList(){
        this.marbles = new HashMap<>();
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public int getSize(Marble.Color color){
        Integer size = marbles.get(color);
        if(size != null){
            return size;
        } else {
            return 0;
        }
    }

    public Set<Marble.Color> getColorSet(){
        return marbles.keySet();
    }

    public void add(Marble marble){
        add(marble.getColor(), 1);
    }

    public void add(Marble.Color color){
        add(color, 1);
    }

    public void add(Marble.Color color, int num){
        marbles.put(color, getSize(color)+num);
        size+= num;
    }

    public void addAll(ResourceList list){
        addAll(list.getAll());
    }

    public void addAll(List<Marble> marbles){
        for (Marble m: marbles){
            this.add(m);
        }
    }

    public Marble pop(Marble.Color color){
        if(getSize(color)>0){
            marbles.put(color, getSize(color)-1 );
            size--;
            return MarbleFactory.getMarble(color);
        } else{
            return null;
        }
    }

    public List<Marble> getAll(){
        List<Marble> marbles = new ArrayList();
        for(Marble.Color color: this.marbles.keySet()){
            for(int i=0; i<this.marbles.get(color); i++){
                marbles.add(MarbleFactory.getMarble(color));
            }
        }
        return marbles;
    }

    public ResourceList sum(ResourceList list){
        ResourceList newList= new ResourceList();
        for(Marble.Color color: Marble.Color.values()){
            for (int i = 0; i < this.getSize(color)+list.getSize(color); i++) {
                newList.add(color);
            }
        }
        return newList;
    }


    public ResourceList sum(Marble.Color color){
        ResourceList newList= new ResourceList().sum(this);

        newList.add(color);

        return newList;
    }

    public ResourceList subtract(ResourceList list){

        ResourceList newList= new ResourceList().sum(this);

        for(Marble.Color color: list.getColorSet()){
            int size = this.getSize(color)-list.getSize(color);
            if( size > 0){
                newList.marbles.put(color, size);
            }
        }

        return newList;
    }


    public ResourceList subtract(Marble.Color color){

        ResourceList newList= new ResourceList().sum(this);

        newList.pop(color);

        return newList;
    }


    public ResourceList difference(ResourceList list){
        ResourceList newList= new ResourceList();

        for(Marble.Color color: list.getColorSet()){
            int size = list.getSize(color)-this.getSize(color);
            if( size > 0){
                newList.add(color, size);
            }
        }

        return newList;
    }

    public boolean contains(ResourceList list){
        for(Marble.Color color: list.getColorSet()){
            if(this.getSize(color) < list.getSize(color)){
                return false;
            }
        }
        return true;
    }

    public void clear(){
        this.size = 0;
        marbles.clear();
    }

}

package it.polimi.ingsw.Model.Marble;


import java.util.*;

public class ResourceList {

    private Map<MarbleColor, Integer> marbles;
    private int size;

    public ResourceList(){
        this.marbles = new HashMap<>();
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public int getSize(MarbleColor color){
        Integer size = marbles.get(color);
        if(size != null){
            return size;
        } else {
            return 0;
        }
    }

    public Set<MarbleColor> getColors(){
        return marbles.keySet();
    }

    public ResourceList add(Marble marble){
        return add(marble.getColor(), 1);

    }

    public ResourceList add(MarbleColor color){
        return add(color, 1);
    }

    public ResourceList add(MarbleColor color, int num){
        marbles.put(color, getSize(color)+num);
        size+= num;
        return this;
    }

    public Marble pop(MarbleColor color){
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
        for(MarbleColor color: this.marbles.keySet()){
            for(int i=0; i<this.marbles.get(color); i++){
                marbles.add(MarbleFactory.getMarble(color));
            }
        }
        return marbles;
    }

    public ResourceList sum(ResourceList list){
        ResourceList newList= new ResourceList();
        for(MarbleColor color: MarbleColor.values()){
            for (int i = 0; i < this.getSize(color)+list.getSize(color); i++) {
                newList.add(color);
            }
        }
        return newList;
    }

    public ResourceList subtract(ResourceList list){

        ResourceList newList= new ResourceList().sum(this);

        for(MarbleColor color: list.getColors()){
            int size = this.getSize(color)-list.getSize(color);
            if( size > 0){
                newList.marbles.put(color, size);
            }
        }

        return newList;
    }

    public ResourceList difference(ResourceList list){
        ResourceList newList= new ResourceList();

        for(MarbleColor color: list.getColors()){
            int size = list.getSize(color)-this.getSize(color);
            if( size > 0){
                newList.add(color, size);
            }
        }

        return newList;
    }

    public boolean contains(ResourceList list){
        for(MarbleColor color: list.getColors()){
            if(this.getSize(color) < list.getSize(color)){
                return false;
            }
        }
        return true;
    }


}

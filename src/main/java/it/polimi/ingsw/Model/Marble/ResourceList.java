package it.polimi.ingsw.Model.Marble;


import java.io.Serializable;
import java.util.*;
/**
 * un lista di biglie
 */
public class ResourceList implements Serializable {

    private Map<Marble.Color, Integer> marbles;
    private int size;

    public ResourceList(){
        this.marbles = new HashMap<>();
        this.size = 0;
    }

    /**
     *  ritorna il numero di biglie che contiene
     */
    public int getSize(){
        return size;
    }


    /**
     *  ritorna il numero di biglie di un certo colore che contiene
     */
    public int getSize(Marble.Color color){
        Integer size = marbles.get(color);
        if(size != null){
            return size;
        } else {
            return 0;
        }
    }


    /**
     *  ritorna che colori sono conservati nella lista
     */
    public Set<Marble.Color> getColorSet(){
        return marbles.keySet();
    }


    /**
     *  aggiunge una biglia alla lista
     */
    public void add(Marble marble){
        add(marble.getColor(), 1);
    }


    /**
     *  aggiunge una biglia del colore specificato alla lista
     */
    public void add(Marble.Color color){
        add(color, 1);
    }


    /**
     *  aggiunge num biglie del colore specificto alla lista
     */
    public void add(Marble.Color color, int num){
        marbles.put(color, getSize(color)+num);
        size+= num;
    }


    /**
     *  aggiunge tutte le biglie contenute nella list a parametro all'interno di this list
     */
    public void addAll(ResourceList list){
        addAll(list.getAllMarble());
    }

    /**
     *  aggiunge tutte le biglie contenute nella list a parametro all'interno di this list
     */
    public void addAll(List<Marble> marbles){
        for (Marble m: marbles){
            this.add(m);
        }
    }

    /**
     *  rimuove una biglia del colore specificato dalla lista
     */
    public Marble pop(Marble.Color color){
        if(getSize(color)>0){
            marbles.put(color, getSize(color)-1 );
            size--;
            return MarbleFactory.getMarble(color);
        } else{
            return null;
        }
    }


    /**
     *  ritorna una lista di tutte le biglie contenute all'interno di this list
     */
    public List<Marble> getAllMarble(){
        List<Marble> marbles = new ArrayList();
        for(Marble.Color color: this.marbles.keySet()){
            for(int i=0; i<this.marbles.get(color); i++){
                marbles.add(MarbleFactory.getMarble(color));
            }
        }
        return marbles;
    }

    /**
     *  ritorna una lista di marble color, uno per ogni marble conservata in this.list
     */
    public static List<Marble.Color> getAllColor(List<Marble> marbles){
        List<Marble.Color> colors = new ArrayList();
        for(Marble marble: marbles){
            colors.add(marble.getColor());
        }
        return colors;
    }


    /**
     *  ritorna una nuova lista di marble , che contiene sia le biglie di this che del parametro list
     */
    public ResourceList sum(ResourceList list){
        ResourceList newList= new ResourceList();
        for(Marble.Color color: Marble.Color.values()){
            for (int i = 0; i < this.getSize(color)+list.getSize(color); i++) {
                newList.add(color);
            }
        }
        return newList;
    }

    /**
     *  ritorna una nuova lista di marble , che contiene le biglie piu una biglia del colore specificato
     */
    public ResourceList sum(Marble.Color color){
        ResourceList newList= new ResourceList().sum(this);

        newList.add(color);

        return newList;
    }


    /**
     *  ritorna una nuova lista che contiene le biglie contenute di this meno le biglie nel parametro list, non ammette valori minori di 0
     */
    public ResourceList subtract(ResourceList list){

        ResourceList newList= new ResourceList().sum(this);

        for(Marble.Color color: list.getColorSet()){
            int size = this.getSize(color)-list.getSize(color);
            if( size >= 0){
                newList.marbles.put(color, size);
            }
        }

        return newList;
    }

    /**
     *  ritorna una nuova lista che contiene le biglie contenute di this meno le biglie la biglia del color specificato, non ammette valori minori di 0
     */
    public ResourceList subtract(Marble.Color color){

        ResourceList newList= new ResourceList().sum(this);

        newList.pop(color);

        return newList;
    }

    /**
     *  ritorna una nuova lista che contiene le biglie che il parametro list ha in più rispetto a this, non ammette valori minori di 0
     */
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

    /**
     *  ritorna una true se this contiene tutte le biglie di list
     */
    public boolean contains(ResourceList list){
        for(Marble.Color color: list.getColorSet()){
            if(this.getSize(color) < list.getSize(color)){
                return false;
            }
        }
        return true;
    }

    /**
     *  svuota la lista
     */
    public void clear(){
        this.size = 0;
        marbles.clear();
    }

}

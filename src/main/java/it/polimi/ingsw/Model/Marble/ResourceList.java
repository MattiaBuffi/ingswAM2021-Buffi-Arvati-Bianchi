package it.polimi.ingsw.Model.Marble;

import java.io.Serializable;
import java.util.*;

/**
 * Represent a list of marble
 */
public class ResourceList implements Serializable {

    private static final long serialVersionUID = 2817649402926923847L;
    private Map<Marble.Color, Integer> marbles;
    private int size;

    public ResourceList(){
        this.marbles = new HashMap<>();
        this.size = 0;
    }

    /**
     * Return the number of marble contained in the list
     * @return number of marble
     */
    public int getSize(){
        return size;
    }



    /**
     * Returns the number of marble of the selected color
     * @param color Color of which you want to know the number of marble
     * @return number of marble of the specified color
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
     * Return the colors contained in the list
     * @return Set of color contained in the list
     */
    public Set<Marble.Color> getColorSet(){
        return marbles.keySet();
    }

    /**
     * Add a marble to the list
     * @param marble marble to add to the list
     */
    public void add(Marble marble){
        add(marble.getColor(), 1);
    }


    /**
     * Add a marble to the list
     * @param color color of the marble to add to the list
     */
    public void add(Marble.Color color){
        add(color, 1);
    }


    /**
     * Add a number of marbles of the color specified equal to the num in input
     * @param color color of the marble to add to the list
     * @param num number of marble to add
     */
    public void add(Marble.Color color, int num){
        marbles.put(color, getSize(color)+num);
        size+= num;
    }

    /**
     * Add all the marbles of the ResourceList as parameter to the marbles of this
     * @param list ResourceList of the marble to add to this
     */
    public void addAll(ResourceList list){
        addAll(list.getAllMarble());
    }


    /**
     * Add all the marbles of the list as parameter to the marbles of this
     * @param marbles list of the marbles to add to this
     */
    public void addAll(List<Marble> marbles){
        for (Marble m: marbles){
            this.add(m);
        }
    }


    /**
     * Remove a marble of the specified color and return that marble
     * @param color color of the marble to remove
     * @return the marble removed from the list
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
     * Return a list of all the marbles of this list
     * @return all the marble of the list
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
     * Return the list of colors in the list passed as parameter (one for each marble).
     * @param marbles list of marbles from which extract the list of color
     * @return list of colors in the list
     */
    public static List<Marble.Color> getAllColor(List<Marble> marbles){
        List<Marble.Color> colors = new ArrayList();
        for(Marble marble: marbles){
            colors.add(marble.getColor());
        }
        return colors;
    }


    /**
     * Return a list that is the sum of this and list
     * @param list list to add to this
     * @return the result of the sum of the two lists
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
     * Return a list that is the sum of this and the marble passed as parameter
     * @param color color of the marble to add to the list
     * @return the updated list of marbles
     */
    public ResourceList sum(Marble.Color color){
        ResourceList newList= new ResourceList().sum(this);

        newList.add(color);

        return newList;
    }


    /**
     * Return a new list which is the result of the subtraction between this and the list
     * @param list ResourceList to subtract to this
     * @return result of subtraction between the two lists
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
     * Return a new list which is equal to this minus a marble of the color specified as parameter
     * @param color color of the marble to remove
     * @return this with a marble of the specified color less
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
     * Return a boolean to indicate if this contains list
     * @param list list for which to check if it is contained in this
     * @return true if the list is contained, false if not
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
     *  Empty the list
     */
    public void clear(){
        this.size = 0;
        marbles.clear();
    }

}

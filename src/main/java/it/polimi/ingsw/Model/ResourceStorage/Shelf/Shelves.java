package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;

/**
 * Represent a list of Shelf
 */
public interface Shelves {

    List<Shelf> getShelves();

    Shelf getShelf(int Position);

    ResourceList getResources();

    /**
     * Add a resource to the specified shelf
     * @param color color of the resource to add
     * @param position position of the shelf where to add the resource
     * @return true if the resource could be added and false if not
     */
    boolean store(Marble.Color color, int position);

    /**
     * Remove a list of resource from the shelves
     * @param list list of resource to remove
     * @return true if it was successful, false otherwise
     */
    boolean withdraw(ResourceList list);

    /**
     * Move resources from a shelf to another
     * @param origin index of the shelf from where to move the resources
     * @param dest index of the shelf where to place the resources
     * @return true if it was possible to move the resources, false if not
     */
    boolean move(int origin, int dest);

}

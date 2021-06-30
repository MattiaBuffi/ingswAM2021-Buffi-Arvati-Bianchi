package it.polimi.ingsw.Model.VaticanRoute;

/**
 * Interface implemented by tokens which can move in the VaticanRoute
 */
public interface FaithHandler {

    void advance(int amount);

    void give(int amount);

}

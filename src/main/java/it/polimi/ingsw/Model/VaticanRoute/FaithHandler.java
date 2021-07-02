package it.polimi.ingsw.Model.VaticanRoute;

/**
 * Interface implemented by tokens which can move in the VaticanRoute
 */
public interface FaithHandler {

    /**
     * Advance this token by the number of position specified by the parameter
     * @param amount number of position to add to the token's position
     */
    void advance(int amount);

    /**give faith point to all the other VaticanToken over the same VaticanRoute
     * @param amount number of point to give to other VaticanToken
     */
    void give(int amount);

}

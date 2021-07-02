package it.polimi.ingsw.Model.Player.ResourceMarket;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleHandler;

import java.util.List;
import java.util.Set;

public interface ResourceMarketHandler extends MarbleHandler{

    /**
     * Return the size of the buffer
     * @return size of the buffer
     */
    int size();

    /**
     * Return the Set (no duplicate elements) of color in the resource buffer
     * @return Set of color in the buffer
     */
    Set<Marble.Color> getColors();

    /**
     * Remove a marble of the specified color from the buffer and return it
     * @param color color of the marble to take
     * @return a marble of the specified color
     */
    boolean take(Marble.Color color);

    /**
     * Remove all the elements form the buffer
     */
    void empty();

    /**
     * Deposit the list of marble in the buffer
     * @param marbles list of marble to manage
     */
    void handleMarbles(List<Marble> marbles);

}

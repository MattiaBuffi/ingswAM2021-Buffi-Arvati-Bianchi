package it.polimi.ingsw.Model.Player.ResourceMarket;

import it.polimi.ingsw.Model.Marble.*;

import java.util.List;
import java.util.Set;

/**
 * Decorator of ResourceMarketHandler. Used to handle white marbles and convert them in SelectableMarble
 */
public class WhiteMarbleConversion implements ResourceMarketHandler{

    private final ResourceMarketHandler baseHandler;
    private final Marble.Color color;


    public WhiteMarbleConversion(ResourceMarketHandler baseHandler, Marble.Color color) {
        this.baseHandler = baseHandler;
        this.color = color;
    }

    @Override
    public int size() {
        return baseHandler.size();
    }

    @Override
    public Set<Marble.Color> getColors() {
        return baseHandler.getColors();
    }

    @Override
    public boolean take(Marble.Color color) {
        return baseHandler.take(color);
    }

    @Override
    public void empty() {
        baseHandler.empty();
    }

    @Override
    public void handleMarbles(List<Marble> marbles) {
        for (Marble m: marbles){
            m.accept(this);
        }
    }

    /**
     * @see ResourceBuffer
     */
    @Override
    public void handle(RedMarble marble) {
        baseHandler.handle(marble);
    }

    /**
     * Generate a new SelectableMarble of the color specified as field which has to be handled
     * @see ResourceBuffer
     */
    @Override
    public void handle(WhiteMarble marble) {
        baseHandler.handle(new SelectableMarble(color));
    }

    /**
     * @see ResourceBuffer
     */
    @Override
    public void handle(ResourceMarble marble) {
        baseHandler.handle(marble);
    }

    /**
     * Add a color to the list of colors of the SelectableMarble
     * @see ResourceBuffer
     */
    @Override
    public void handle(SelectableMarble marble) {
        marble.addColor(color);
        baseHandler.handle(marble);
    }

}

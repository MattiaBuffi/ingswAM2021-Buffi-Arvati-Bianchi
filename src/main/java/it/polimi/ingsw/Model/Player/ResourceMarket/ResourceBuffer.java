package it.polimi.ingsw.Model.Player.ResourceMarket;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.MarketResourceAvailable;
import it.polimi.ingsw.Message.Model.MarketResourceTaken;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;


/**
 * Resource buffer for resources took from the market
 */
public class ResourceBuffer implements ResourceMarketHandler{


    private final FaithHandler faithHandler;
    private final EventBroadcaster broadcaster;

    private List<ResourceMarble> availableMarbles;
    private List<SelectableMarble> selectableMarbles;


    public ResourceBuffer(FaithHandler faithHandler, EventBroadcaster broadcaster) {
        this.faithHandler = faithHandler;
        this.broadcaster = broadcaster;
        this.availableMarbles = new ArrayList<>();
        this.selectableMarbles = new ArrayList<>();
    }

    /**
     * Comparator between two ResourceMarble
     * @param marble first marble to compare
     * @param color color of the second marble
     * @return true if the color of the two marbles is the same, false if not.
     */
    public Boolean normalMarbleComparator(ResourceMarble marble, Marble.Color color){
        return marble.getColor() == color;
    }

    /**
     * Comparator between a SelectableMarble and a ResourceMarble
     * @param marble SelectableMarble to compare
     * @param color color of the ResourceMarble to compare
     * @return true if the SelectableMarble contains the color of the ResourceMarble, false if not.
     */
    public Boolean selectableMarbleComparator(SelectableMarble marble, Marble.Color color){
        for(Marble.Color m: marble.getSelectableColors()){
            if(m == color){
                return true;
            }
        }
        return false;
    }


    /**
     * Remove a marble from the list based on the comparator criteria
     * @param marbleList list from which to remove the marble
     * @param color color of the marble to remove
     * @param comparator comparator used to decide what marble has to be removed
     * @param <M> Generic class which extends Marble
     * @return true if the marble is removed, false if not
     */
    private<M extends Marble> boolean remove(List<M> marbleList, Marble.Color color, BiFunction<M, Marble.Color, Boolean> comparator){
        for(M m: marbleList){
            if(comparator.apply(m,color)){
                marbleList.remove(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return availableMarbles.size()+selectableMarbles.size();
    }

    @Override
    public Set<Marble.Color> getColors() {
        Set<Marble.Color> colors = new HashSet<>();

        for (ResourceMarble rm: availableMarbles){
            colors.add(rm.getColor());
        }
        for (SelectableMarble sm: selectableMarbles){
            colors.addAll(sm.getSelectableColors());
        }
        return colors;
    }

    @Override
    public boolean take(Marble.Color color){
        if(remove(availableMarbles, color, this::normalMarbleComparator)){
            broadcaster.notifyAllPlayers(new MarketResourceTaken(color));
            return true;
        }
        if(remove(selectableMarbles, color, this::selectableMarbleComparator)){
            broadcaster.notifyAllPlayers(new MarketResourceTaken(color));
            return true;
        }
        broadcaster.notifyUser(new ErrorUpdate("marble not found"));
        return false;
    }

    @Override
    public void empty(){
        faithHandler.give(availableMarbles.size()+selectableMarbles.size() );
        availableMarbles.clear();
        selectableMarbles.clear();
    }

    @Override
    public void handleMarbles(List<Marble> marbles) {
        for (Marble m: marbles){
            m.accept(this);
        }
    }

    /**
     * Manage a RedMarble by adding a faithPoints
     * @param marble marble to handle
     */
    @Override
    public void handle(RedMarble marble) {
        this.faithHandler.advance(1);
    }

    /**
     * Manage a WhiteMarble by doing nothing
     * @param marble marble to handle
     */
    @Override
    public void handle(WhiteMarble marble) {
    }

    /**
     * Manage the marble by adding it to the buffer
     * @param marble marble to handle
     */
    @Override
    public void handle(ResourceMarble marble) {
        availableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }

    /**
     * Manage the marble by adding it to the buffer
     * @param marble marble to handle
     */
    @Override
    public void handle(SelectableMarble marble) {
        selectableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }


}

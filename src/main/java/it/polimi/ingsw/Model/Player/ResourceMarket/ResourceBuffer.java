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


public class ResourceBuffer implements ResourceMarketHandler, MarbleHandler{

    /*
    private static final BiFunction<SelectableMarble, Marble.Color, Boolean> selectableComparator = (m1, m2)->{
        for(Marble.Color m: m1.getSelectableColors()){
            if(m == m2){
                return true;
            }
        }
        return false;
    };
    private static final BiFunction<ResourceMarble, Marble.Color, Boolean> normalComparator = (m1, m2)-> m1.getColor() == m2;
*/

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


    public Boolean normalMarbleComparator(ResourceMarble marble, Marble.Color color){
        return marble.getColor() == color;
    }

    public Boolean selectableMarbleComparator(SelectableMarble marble, Marble.Color color){
        for(Marble.Color m: marble.getSelectableColors()){
            if(m == color){
                return true;
            }
        }
        return false;
    }


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
        broadcaster.notifyUser(new ErrorUpdate("0", "marble not found"));
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




    @Override
    public void handle(RedMarble marble) {
        this.faithHandler.advance(1);
    }

    @Override
    public void handle(WhiteMarble marble) {
       return;
    }

    @Override
    public void handle(ResourceMarble marble) {
        availableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }

    @Override
    public void handle(SelectableMarble marble) {
        selectableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }


}

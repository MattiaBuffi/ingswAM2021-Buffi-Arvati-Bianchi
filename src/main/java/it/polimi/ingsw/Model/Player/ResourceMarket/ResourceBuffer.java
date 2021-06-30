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
 *  buffer delle risorse che un player compra al mercato
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
     *  comparatore tra due Resource marble. ritorna true se il colore delle due biglie è uguale
     */
    public Boolean normalMarbleComparator(ResourceMarble marble, Marble.Color color){
        return marble.getColor() == color;
    }

    /**
     *  comparatore tra una Selectable marble e un Marble color. ritorna true se la selectable marble contiene il colore
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
     *  rimuove una marble da una lista di marble a seconda di un comparatore
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


    /**
     *  ritorna la dimensione del buffer
     */
    @Override
    public int size() {
        return availableMarbles.size()+selectableMarbles.size();
    }

    /**
     *  ritorna il set dei colori contenuti nel buffer
     */
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


    /**
     *  rimuove una biglia del colore specificato dal buffer e la ritorna al chiamante
     */
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

    /**
     *  svuota il buffer
     */
    @Override
    public void empty(){
        faithHandler.give(availableMarbles.size()+selectableMarbles.size() );
        availableMarbles.clear();
        selectableMarbles.clear();
    }

    /**
     *  deposita una lista di marble all'interno del buffer
     */
    @Override
    public void handleMarbles(List<Marble> marbles) {
        for (Marble m: marbles){
            m.accept(this);
        }
    }



    /**
     *  gestisce il deposito delle RedMarble nel buffer
     */
    @Override
    public void handle(RedMarble marble) {
        this.faithHandler.advance(1);
    }


    /**
     *  gestisce il deposito delle WhiteMarble nel buffer
     */
    @Override
    public void handle(WhiteMarble marble) {
       return;
    }


    /**
     *  gestisce il deposito delle ResourceMarble nel buffer
     */
    @Override
    public void handle(ResourceMarble marble) {
        availableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }


    /**
     *  gestisce il deposito delle SelectableMarble nel buffer
     */
    @Override
    public void handle(SelectableMarble marble) {
        selectableMarbles.add(marble);
        broadcaster.notifyAllPlayers(new MarketResourceAvailable(marble));
    }


}

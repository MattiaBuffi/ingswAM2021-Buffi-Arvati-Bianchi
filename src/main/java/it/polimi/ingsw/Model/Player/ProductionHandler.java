package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ProductionBufferUpdate;
import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * buffer delle risorse prodotte durante un turno di un player
 */
public class ProductionHandler {

    private ResourceList produced;
    private List<String> usedCard;
    private handler marbleHandler;


    private CardStorage cardStorage;
    private ResourceStorage resourceStorage;
    private FaithHandler faithHandler;
    private boolean ready;

    private final EventBroadcaster broadcaster;


    public ProductionHandler(EventBroadcaster broadcaster){
        produced = new ResourceList();
        usedCard = new ArrayList<>();
        marbleHandler = new handler();
        ready = false;

        this.broadcaster = broadcaster;
    }

    /**
     *  reinizilaizza il buffer e lo rende disponibile ad accettare risorse
     */
    public void setupProductionHandler(CardStorage cardStorage, ResourceStorage resourceStorage, FaithHandler faithHandler){
        if(ready){
            return;
        }
        this.cardStorage = cardStorage;
        this.resourceStorage = resourceStorage;
        this.faithHandler = faithHandler;
        ready = true;
    }

    /**
     *  Ottiene una productionCard grazie al productionSelector e prova ad eseguirla
     */
    public boolean produce(ProductionSelector selector){

        if(!ready){
            throw new IllegalStateException("production handler need to be initialized ");
        }

        ProductionCard card = cardStorage.getCard(selector);

        if(card == null){
            return false;
        }

        if(usedCard.contains(card.getId())){
            broadcaster.notifyUser(new ErrorUpdate( "production already used"));
            return false;
        }

        List<Marble> production = card.make(resourceStorage);

        if(production == null){
            broadcaster.notifyUser(new ErrorUpdate( "production unavailable"));
            return false;
        }

        for(Marble m: production){
            m.accept(marbleHandler);
        }

        usedCard.add(card.getId());

        broadcaster.notifyAllPlayers(new ProductionBufferUpdate(card.getId(), production));

        return true;

    }

    /**
     *  svuota il buffer di produzioni
     */
    public void empty(){
        resourceStorage.deposit(produced);
        produced.clear();
        usedCard.clear();
        this.cardStorage = null;
        this.resourceStorage = null;
        this.faithHandler = null;
        this.ready = false;
    }

    /**
     *  gestisce le marble prodotte
     */
    private class handler implements MarbleHandler{

        @Override
        public void handle(RedMarble marble) {
            faithHandler.advance(1);
        }

        @Override
        public void handle(WhiteMarble marble) {
            throw new IllegalStateException("white marble during production not supported");
        }

        @Override
        public void handle(ResourceMarble marble) {
            produced.add(marble);
        }

        @Override
        public void handle(SelectableMarble marble) {
            throw new IllegalStateException("selectable marble during production not supported");
        }
    }



}

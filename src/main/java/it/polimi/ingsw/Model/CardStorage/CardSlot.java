package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Message.Model.DevelopmentCardBuyUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

/**
 *  pila di Development card, la carta in cima puo essere usata per produrre
 */
public class CardSlot {

    private EventBroadcaster broadcaster;
    private final int position;

    private DevelopmentCard activeCard;
    private List<DevelopmentCard> inactiveCard;


    public CardSlot(int position, EventBroadcaster broadcaster) {
        this.position = position;
        this.inactiveCard = new ArrayList<>();
        this.broadcaster = broadcaster;
    }


    public int getPosition() {
        return position;
    }

    /**
     *  ritorna la carta in cima alla pila
     */
    public DevelopmentCard getActiveCard(){
        return activeCard;
    }

    /**
     *  ritorna tutte le carte conservate all'interno della pila
     */
    public List<DevelopmentCard> getCards(){
        List<DevelopmentCard> list = new ArrayList<>();
        if(activeCard != null){
            list.add(activeCard);
        }
        list.addAll(inactiveCard);
        return list;
    }

    /**
     *  aggiunge una carta in cima alla pila
     */
    public void setCard(DevelopmentCard card){
        if(activeCard!= null){
            inactiveCard.add(activeCard);
        }
        activeCard = card;
        broadcaster.notifyAllPlayers(new DevelopmentCardBuyUpdate(position, card.getId(), card.getVictoryPoint(), card.getRequire(), card.getProduce(), card.getColor()));
    }


    /**
     *  ritorna il livello della carta in cima alla pila
     */
    public int getLevel(){
        if(activeCard == null){
            return 0;
        } else {
            return activeCard.getLevel();
        }
    }

}

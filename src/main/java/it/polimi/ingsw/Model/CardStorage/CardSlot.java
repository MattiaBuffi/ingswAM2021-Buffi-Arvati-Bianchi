package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Message.Model.DevelopmentCardBuyUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

/**
 *  Stack of development cards. The card on top could be used to produce.
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
     * Return the card on top of the stack
     * @return The card on top
     */
    public DevelopmentCard getActiveCard(){
        return activeCard;
    }

    /**
     * Return all the card in the stack
     * @return A list of all development cards in the stack
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
     * Add a card on the top of the stack
     * @param card Card to add to the stack
     */
    public void setCard(DevelopmentCard card){
        if(activeCard!= null){
            inactiveCard.add(activeCard);
        }
        activeCard = card;
        broadcaster.notifyAllPlayers(new DevelopmentCardBuyUpdate(position, card.getId(), card.getVictoryPoint(), card.getRequire(), card.getProduce(), card.getColor()));
    }

    /**
     * Return the level of the card on top of the stack
     * @return The level of the card
     */
    public int getLevel(){
        if(activeCard == null){
            return 0;
        } else {
            return activeCard.getLevel();
        }
    }

}

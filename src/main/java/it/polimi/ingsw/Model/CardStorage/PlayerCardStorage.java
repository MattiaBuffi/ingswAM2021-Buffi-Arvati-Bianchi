package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of development card storage
 */
public class PlayerCardStorage implements CardStorage, ProductionVisitor {

    private EventBroadcaster broadcaster;
    private List<CardSlot> cardSlots;

    public PlayerCardStorage(EventBroadcaster broadcaster){

        this.cardSlots = new ArrayList<>();
        this.broadcaster = broadcaster;


        for (int i = 0; i < 3; i++) {
            cardSlots.add(new CardSlot(i, broadcaster));
        }

    }

    /**
     * Return the CardSlot in the specified position. The position allowed are from 0 to 2.
     * @param position Position of the cardSlot to return
     * @return The CardSlot in the specified position
     * @throws IllegalArgumentException If the position specified if greater than 2
     */
    private CardSlot getSlotByPosition(int position) throws IllegalArgumentException{

        if(position > 2){
            throw new IllegalArgumentException();
        }

        return cardSlots.get(position);
    }


    /**
     * Buy a development card
     * @param card Card to buy
     * @param position CardSlot where to store the card in the storage
     * @param storage Where to store the card bought
     * @return True if the purchase was successful
     */
    @Override
    public boolean buyCard(PurchasableCard card, int position, ResourceStorage storage) {

        CardSlot slot = getSlotByPosition(position);

        if (slot.getLevel() != card.getLevel()-1){
            broadcaster.notifyUser(new ErrorUpdate("illegal position"));
            return false;
        }
        if(!storage.withdrawal(card.getCost())){
            return false;
        }

        slot.setCard(card.getCard());
        card.buy();

        return true;
    }


    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return selector.getCard(this);
    }


    @Override
    public List<DevelopmentCard> getCards() {
        List<DevelopmentCard> list = new ArrayList<>();
        for(CardSlot s: cardSlots){

            if(!s.getCards().isEmpty()){
                list.addAll(s.getCards());
            }

        }
        return list;
    }



    @Override
    public ProductionCard visit(SelectBasic selector) {
        if( selector.getRequires().getSize() != 2 ){
            broadcaster.notifyUser(new ErrorUpdate( "wrong input size"));
            return null;
        }
        if( selector.getRequires().getSize(Marble.Color.RED) != 0){
            broadcaster.notifyUser(new ErrorUpdate( "illegal color"));
            return null;
        }
        if( selector.getProduce().getSize() != 1){
            broadcaster.notifyUser(new ErrorUpdate( "wrong output size"));
            return null;
        }
        if(selector.getProduce().getSize(Marble.Color.RED) != 0){
            broadcaster.notifyUser(new ErrorUpdate( "illegal color"));
            return null;
        }
        return new ProductionCard(selector.getId(), selector.getRequires(), selector.getProduce());
    }


    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {
        ProductionCard card = getSlotByPosition(selector.getPosition()).getActiveCard();
        if(card == null){
            broadcaster.notifyUser(new ErrorUpdate( "card not found"));
        }
        return card;
    }

    @Override
    public ProductionCard visit(SelectLeader selector) {
        broadcaster.notifyUser(new ErrorUpdate("card not found"));
        return null;
    }

    /**
     * TODO: PARTIAL DOCUMENTATION -> ASK FOR SELECTOR AND VISITOR
     */


}

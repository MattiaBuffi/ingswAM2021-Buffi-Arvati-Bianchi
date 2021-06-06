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

    private CardSlot getSlotByPosition(int position) throws IllegalArgumentException{

        if(position > 2){
            throw new IllegalArgumentException();
        }

        return cardSlots.get(position);
    }


    @Override
    public boolean buyCard(PurchasableCard card, int position, ResourceStorage storage) {

        CardSlot slot = getSlotByPosition(position);

        if (slot.getLevel() != card.getLevel()-1){
            broadcaster.notifyUser(new ErrorUpdate("0", "illegal position"));
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
            broadcaster.notifyUser(new ErrorUpdate("0", "wrong input size"));
            return null;
        }
        if( selector.getRequires().getSize(Marble.Color.RED) != 0){
            broadcaster.notifyUser(new ErrorUpdate("0", "illegal color"));
            return null;
        }
        if( selector.getProduce().getSize() != 1){
            broadcaster.notifyUser(new ErrorUpdate("0", "wrong output size"));
            return null;
        }
        if(selector.getProduce().getSize(Marble.Color.RED) != 0){
            broadcaster.notifyUser(new ErrorUpdate("0", "illegal color"));
            return null;
        }
        return new ProductionCard(selector.getId(), selector.getRequires(), selector.getProduce());
    }


    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {
        ProductionCard card = getSlotByPosition(selector.getPosition()).getActiveCard();
        if(card == null){
            broadcaster.notifyUser(new ErrorUpdate("0", "card not found"));
        }
        return card;
    }

    @Override
    public ProductionCard visit(SelectLeader selector) {
        broadcaster.notifyUser(new ErrorUpdate("0", "card not found"));
        return null;
    }



}

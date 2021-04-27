package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardStorage implements CardStorage, ProductionVisitor {

    private List<CardSlot> cardSlots;


    private CardSlot getSlotById(String id){
        for (CardSlot s: cardSlots){
            if(s.getCard().getId() == id){
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean buyCard(PurchasableCard card, String destId, ResourceStorage storage) {
        card.buy(getSlotById(destId), storage);
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
            //need to check null
            list.add(s.getCard());
        }
        return list;
    }



    @Override
    public ProductionCard visit(SelectBasic selector) {
        if( selector.getRequires().getSize() != 2 ){
            return null;
        }
        if( selector.getRequires().getSize(MarbleColor.RED) != 0){
            return null;
        }
        if( selector.getProduce().getSize() != 1){
            return null;
        }
        if(selector.getProduce().getSize(MarbleColor.RED) != 0){
            return null;
        }
        return new ProductionCard(selector.getId(), selector.getProduce(), selector.getProduce());
    }

    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {
        return getSlotById(selector.getId()).getCard();
    }

    @Override
    public ProductionCard visit(SelectLeader selector) {
        return null;
    }
}

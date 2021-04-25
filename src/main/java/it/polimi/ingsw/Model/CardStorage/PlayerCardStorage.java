package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

public class PlayerCardStorage implements CardStorage, ProductionVisitor {

    private List<CardSlot> cardSlots;


    private CardSlot getSlotById(String id){
        for (CardSlot s: cardSlots){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    @Override
    public void buyCard(MarketCard card, String destId, ResourceStorage storage) {
        card.buy(getSlotById(destId), storage);
    }

    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return selector.getCard(this);
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
        return new ProductionCard(selector.getProduce(),selector.getProduce());
    }

    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {
        return null;
    }

    @Override
    public ProductionCard visit(SelectLeader selector) {
        return null;
    }
}

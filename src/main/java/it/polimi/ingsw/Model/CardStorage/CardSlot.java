package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

import java.util.List;

public class CardSlot {

    private DevelopmentCard activeCard;
    private List<DevelopmentCard> inactiveCard;

    public CardSlot() {

    }

    public DevelopmentCard getCard(){
        return activeCard;
    }

    public void setCard(DevelopmentCard card){
        inactiveCard.add(activeCard);
        activeCard = card;
    }

    public int getLevel(){
        return activeCard.getLevel();
    }



}

package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

import java.util.List;

public class CardSlot {

    private DevelopmentCard activeCard;
    private List<DevelopmentCard> inactiveCard;
    private final String id;


    public CardSlot(String id) {
        this.id = id;
    }

    public ProductionCard getCard(){
        return activeCard;
    }

    public void setCard(DevelopmentCard card){
        inactiveCard.add(activeCard);
        activeCard = card;
    }

    public int getLevel(){
        return activeCard.getLevel();
    }

    public String getId() {
        return id;
    }

}

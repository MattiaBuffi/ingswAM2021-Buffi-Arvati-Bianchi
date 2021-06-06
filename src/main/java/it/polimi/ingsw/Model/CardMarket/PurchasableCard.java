package it.polimi.ingsw.Model.CardMarket;


import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.CardStorage.CardSlot;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public class PurchasableCard {

    private CardRemover remover;
    private DevelopmentCard card;
    private ResourceList cost;

    public PurchasableCard(CardRemover remover, ResourceList cost, DevelopmentCard card) {
        this.remover = remover;
        this.cost = cost;
        this.card = card;

    }

    public String getId(){
        return card.getId();
    }

    public int getLevel() {
        return card.getLevel();
    }

    public DevelopmentCard.Color getColor() {
        return card.getColor();
    }

    public DevelopmentCard getCard() {
        return card;
    }

    public ResourceList getCost() {
        return cost;
    }

    public void buy(){
        remover.removeCard(getColor(), getLevel());
    }


}

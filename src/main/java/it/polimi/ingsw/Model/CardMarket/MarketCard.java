package it.polimi.ingsw.Model.CardMarket;


import it.polimi.ingsw.Model.CardStorage.CardSlot;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public class MarketCard {

    private CardMarket market;
    private DevelopmentCard card;
    private ResourceList cost;

    public MarketCard(CardMarket market, ResourceList cost) {
        this.market = market;
        this.cost = cost;
    }

    public void buy(CardSlot cardSlot, ResourceStorage storage){

        if (cardSlot.getLevel() != card.getLevel()-1){
           //error
        }
        if(!storage.getResources().contains(cost)){
            //error
        }
        storage.withdrawal(cost);
        cardSlot.setCard(card);
        market.newCard(this);

    }

}

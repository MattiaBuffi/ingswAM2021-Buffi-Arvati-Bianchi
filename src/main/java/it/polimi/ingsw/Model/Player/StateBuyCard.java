package it.polimi.ingsw.Model.Player;



import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.CardStorage;


public class StateBuyCard extends PlayerState {

    @Override
    protected void invalidAction() {
        //return error
    }

    @Override
    protected void buyCard(MarketCard card, String destinationId) {
        CardStorage storage = context.getCardStorage();
        storage.buyCard(card, destinationId, context.getResourceStorage());
    }


}
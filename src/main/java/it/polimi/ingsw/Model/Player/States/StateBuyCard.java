package it.polimi.ingsw.Model.Player.States;



import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.Player.PlayerState;


public class StateBuyCard extends PlayerState {

    @Override
    protected void invalidAction() {
        //return error
    }

    @Override
    protected void buyCard(PurchasableCard card, String destinationId) {
        context.buyCardStrategy.execute(context, card, destinationId);
    }

    @Override
    protected void endTurn(){

    }


}
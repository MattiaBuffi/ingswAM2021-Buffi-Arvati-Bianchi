package it.polimi.ingsw.Model.Player.States;



import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;


public class StateBuyCard extends PlayerState {

    private static StateBuyCard instance;

    private StateBuyCard(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean buyCard(Player context, PurchasableCard card, int destinationId) {

        if(!context.getCardStorage().buyCard(card, destinationId, context.getResourceStorage())){
            return false;
        }

        context.setState(StateWait.get());

        if(context.getCardStorage().getCards().size() < 7){
            context.getTurnHandler().endTurn();
        } else {
            context.getGameTerminator().endGame();
        }

        return true;

    }


    public static StateBuyCard get(){
        if(instance == null){
            instance = new StateBuyCard(Name.BUY_CARD);
        }
        return instance;
    }



}
package it.polimi.ingsw.Model.Player.States;



import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.GameTerminator;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;
import it.polimi.ingsw.Model.TurnHandler;


public class StateBuyCard extends PlayerState {

    private static StateBuyCard instance;

    private StateBuyCard(Name stateName){
        super(stateName);
    }



    public boolean buyCard(CardStorage cardStorage, ResourceStorage resourceStorage, PurchasableCard card, int destinationId){

        if(!cardStorage.buyCard(card, destinationId, resourceStorage)){
            return false;
        }

        return true;
    }


    public void changeState(PlayerState.Context context, CardStorage cardStorage, TurnHandler turnHandler, GameTerminator terminator){
        context.setState(StateWait.get());

        if(cardStorage.getCards().size() < 7){
            turnHandler.endTurn();
        } else {
            terminator.endGame();
        }
    }


    @Override
    protected boolean buyCard(Player context, PurchasableCard card, int destinationId) {

        if(buyCard(context.getCardStorage(), context.getResourceStorage(), card, destinationId)){
            changeState(context, context.getCardStorage(), context.getTurnHandler(), context.getGameTerminator());
            return true;
        }

        return false;

    }


    public static StateBuyCard get(){
        if(instance == null){
            instance = new StateBuyCard(Name.BUY_CARD);
        }
        return instance;
    }



}
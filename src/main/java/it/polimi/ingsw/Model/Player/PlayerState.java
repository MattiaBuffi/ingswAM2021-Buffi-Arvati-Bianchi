package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;


import java.util.List;

/**
 * Abstract class which represent a player state. Every phase of the game/turn has been implemented with a state pattern.
 * All the methods of the class call athe invalidAction methods which notify the player that the move done is illegal. Each
 * of the state which extends this class will override the methods allowed in that state.
 */
public abstract class PlayerState {

    public interface Context {
        void setState(PlayerState state);
    }

    /**
     * Enumeration with names of the possible player states
     */
    public enum Name {
        SETUP_CARD,
        SETUP_RESOURCES,
        NEW_TURN,
        WAIT,
        BUY_RESOURCE,
        STORE_RESOURCE,
        BUY_CARD,
        PRODUCTION,
        END_GAME;
    }


    private final Name name;

    public PlayerState(Name name){
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    /**
     * Notify the player that the action just done is not allowed
     * @param player player who has to be notified of the invalid action done
     * @return always false
     */
    protected boolean invalidAction(Player player){
        player.notifyUser(new ErrorUpdate( "illegal action"));
        return false;
    }

    /**
     * Set the player player as active player
     * @param context player to set as active
     */
    protected boolean setActive(Player context){
        return invalidAction(context);
    }

    /**
     * Buy the specified card from the market and place it to the chosen position in the player production board
     * @param context player who buy the card
     * @param card card bought
     * @param destinationId where to place the card
     * @return true if the action is successful, false if not
     */
    protected boolean buyCard(Player context, PurchasableCard card, int destinationId){
        return invalidAction(context);
    }

    /**
     * Execute the production of a card
     * @param context player who activate the production
     * @param selector
     * @return true if the action is successful, false if not
     */
    protected boolean production(Player context, ProductionSelector selector){
        return invalidAction(context);
    }

    /**
     * Store a resource in the storage
     * @param context player who want to store the resource
     * @param color color of the resource to store
     * @param storageId index of the storage
     * @return true if the action is successful, false if not
     */
    protected boolean storeResource(Player context, Marble.Color color, int storageId){
        return invalidAction(context);
    }

    /**
     * Buy resource from the market
     * @param context player who take the resources
     * @param resources list of marbles took from the market
     * @return true if the action is successful, false if not
     */
    protected boolean buyResources(Player context, List<Marble> resources){
        return invalidAction(context);
    }

    /**
     * Move resources from one shelf to another
     * @param context player who want to move the resources
     * @param originId index of shelf from where to take the resources
     * @param destinationId index of shelf where to place the resources
     * @return true if the action is successful, false if not
     */
    protected boolean moveResources(Player context, int originId, int destinationId){
        return invalidAction(context);
    }

    /**
     * Discard the leader card specified
     * @param context player who discard the card
     * @param leaderId id of the card discarded
     * @return true if the action is successful, false if not
     */
    protected boolean discardLeader(Player context, String leaderId){
        return invalidAction(context);
    }

    /**
     * Activate the leader card specified
     * @param context player who activate the card
     * @param leaderId id of the card activated
     * @return true if the action is successful, false if not
     */
    protected boolean activateLeader(Player context, String leaderId){
        return invalidAction(context);
    }

    /**
     * End the turn of the active player
     * @param context active player who end its turn
     * @return true if the action is successful, false if not
     */
    protected boolean endTurn(Player context){
        return invalidAction(context);
    }

    /**
     * Update the victory points of the player
     * @param context player to whom to update the victory points
     * @return updated victory points
     */
    protected int getVictoryPoints(Player context){
        int points = 0;

        //vatican route points
        points += context.getVaticanToken().getVictoryPoints();
        //deleted leader card point
        points += 2 - context.getLeaderCards().size();
        //active leader card point
        for (LeaderCard lc: context.getLeaderCards()){
            if(lc.isActive()){
                points += lc.getVictoryPoints();
            }
        }
        //production card point
        for(DevelopmentCard c: context.getCardStorage().getCards()){
            points += c.getVictoryPoint();
        }
        return points;

    }


}

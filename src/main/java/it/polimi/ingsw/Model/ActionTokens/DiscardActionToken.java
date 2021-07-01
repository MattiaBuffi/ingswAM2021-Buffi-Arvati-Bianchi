
package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Message.Model.ActionTokenPlayed;
import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;


/**
 * Represent an action token that remove cards of a certain color from the card market
 */
public class DiscardActionToken implements ActionToken{

    private DevelopmentCard.Color color;
    private CardRemover remover;
    private GameHandler terminator;
    private EventBroadcaster broadcaster;

    public DiscardActionToken(CardRemover remover, DevelopmentCard.Color color, GameHandler terminator, EventBroadcaster broadcaster) {
        this.color = color;
        this.remover = remover;
        this.terminator = terminator;
        this.broadcaster = broadcaster;
    }

    /**
     * Remove a card of the color specified as field. Following the rule of the game, if the remover return false the method
     * started the end of the game.
     * @see ActionTokenPlayed The method also generate an ActionTokenPlayed message and notify it to all the player
     */
    @Override
    public void activate() {
        if(!remover.removeCard(color)){
            terminator.endGame();
            return;
        }
        if(!remover.removeCard(color)){
            terminator.endGame();
            return;
        }
        broadcaster.notifyAllPlayers(new ActionTokenPlayed(color+" card token played"));
    }

}

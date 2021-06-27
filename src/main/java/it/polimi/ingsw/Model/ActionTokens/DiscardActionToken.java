
package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Message.Model.ActionTokenPlayed;
import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;


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

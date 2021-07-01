
package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;


import java.util.*;


public class ActionDeck implements Shuffler {

    private List<ActionToken> actionTokens;
    private int currentToken;
    private EventBroadcaster broadcaster;

    public ActionDeck(CardRemover remover, FaithHandler faithToken, GameHandler gameHandler, EventBroadcaster broadcaster) {

        this.broadcaster = broadcaster;

        actionTokens = new ArrayList<>();
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.BLUE, gameHandler, broadcaster));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.GREEN, gameHandler, broadcaster));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.PURPLE, gameHandler, broadcaster));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.YELLOW, gameHandler, broadcaster));
        actionTokens.add(new BlackCrossActionToken(1, faithToken,this, broadcaster));
        actionTokens.add(new BlackCrossActionToken(1, faithToken,this, broadcaster));
        actionTokens.add(new BlackCrossActionToken(2, faithToken,this, broadcaster));


        shuffle();
    }

    /**
     *  Activate the token on currentToken position of the list
     */
    public void playToken(){
        currentToken += 1;
        actionTokens.get(currentToken).activate();
    }


    /**
     * Shuffle the list of token
     */
    @Override
    public void shuffle() {
        Collections.shuffle(actionTokens);
        currentToken = -1;
    }


}

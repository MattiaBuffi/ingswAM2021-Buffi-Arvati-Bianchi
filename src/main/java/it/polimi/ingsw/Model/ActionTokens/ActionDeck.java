
package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.GameTerminator;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;


import java.util.*;


public class ActionDeck implements Shuffler {

    private List<ActionToken> actionTokens;
    private int currentToken;


    public ActionDeck(CardRemover remover, FaithHandler faithToken, GameTerminator terminator) {

        actionTokens = new ArrayList<>();
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.BLUE, terminator));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.GREEN, terminator));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.PURPLE, terminator));
        actionTokens.add(new DiscardActionToken(remover, DevelopmentCard.Color.YELLOW, terminator));
        actionTokens.add(new BlackCrossActionToken(this,faithToken,1));
        actionTokens.add(new BlackCrossActionToken(this,faithToken,1));
        actionTokens.add(new BlackCrossActionToken(this,faithToken,2));

        shuffle();

    }


    public void playToken(){
        currentToken += 1;
        actionTokens.get(currentToken).activate();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(actionTokens);
        currentToken = -1;
    }


}

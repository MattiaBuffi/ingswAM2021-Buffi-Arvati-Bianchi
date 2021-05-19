
package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCardColor;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActionDeck {
    private final static int DIM = 7; 
    private final static ActionToken[] TOKENS_LIST = {new DiscardActionToken(DevelopmentCardColor.BLUE),
                                                        new DiscardActionToken(DevelopmentCardColor.GREEN),
                                                        new DiscardActionToken(DevelopmentCardColor.PURPLE),
                                                        new DiscardActionToken(DevelopmentCardColor.YELLOW),
                                                        new BlackCrossActionToken(1),
                                                        new BlackCrossActionToken(1),
                                                        new BlackCrossActionToken(2),
    };
    private ActionToken[] actionTokenArray = new ActionToken[DIM];
    private Game game;

    public ActionDeck(Game game) {
        this.game = game;

        List<ActionToken> tmpList = Arrays.asList(TOKENS_LIST);
        Collections.shuffle(tmpList);
        
        tmpList.toArray(actionTokenArray);
    }
    
    public void activateActionToken(){
        boolean deckShuffle = actionTokenArray[0].activate(game);
        
        if(deckShuffle){
            shuffle();
        }else{
            putAtTheEndOfDeck();
        }
    }

    private void putAtTheEndOfDeck() {
        ActionToken tmpToken = actionTokenArray[0];
        
        for(int i=0; i < (DIM-1); i++){
            actionTokenArray[i] = actionTokenArray[i+1];
        }
        actionTokenArray[DIM-1] = tmpToken;
    }
    
    private void shuffle(){
        List<ActionToken> tmpList = Arrays.asList(actionTokenArray);
        Collections.shuffle(tmpList);
        
        tmpList.toArray(actionTokenArray);
    }

    public ActionToken[] getActionTokenArray() {
        return actionTokenArray;
    }
}

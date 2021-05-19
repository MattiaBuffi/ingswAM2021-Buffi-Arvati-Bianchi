package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCardColor;
import it.polimi.ingsw.Model.VaticanRoute.BlackCrossToken;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ActionDeckTest {

    @Test
    @DisplayName("Deck random initialization")
    public void initTest(){
        final ActionToken[] TOKENS_LIST = {new DiscardActionToken(DevelopmentCardColor.BLUE),
                new DiscardActionToken(DevelopmentCardColor.GREEN),
                new DiscardActionToken(DevelopmentCardColor.PURPLE),
                new DiscardActionToken(DevelopmentCardColor.YELLOW),
                new BlackCrossActionToken(1),
                new BlackCrossActionToken(1),
                new BlackCrossActionToken(2),
        };
        Game game = new Game(1);
        ActionDeck actionDeck = new ActionDeck(game);

        assertFalse(Arrays.equals(TOKENS_LIST, actionDeck.getActionTokenArray()));
    }

    @Test
    @DisplayName("Token at the end of deck after activation")
    public void endOfDeckTest(){
        Game game = new Game(1);
        ActionDeck actionDeck = new ActionDeck(game);
        ActionToken tmp = actionDeck.getActionTokenArray()[0];

        actionDeck.activateActionToken();

        if(tmp instanceof DiscardActionToken){
            assertEquals(tmp, actionDeck.getActionTokenArray()[6]);
        }
    }

    @Test
    @DisplayName("BlackCross token activation")
    public void blackCrossTokenTest(){
        Game game = new Game(1);
        BlackCrossActionToken token1 = new BlackCrossActionToken(1);
        BlackCrossActionToken token2 = new BlackCrossActionToken(2);

        assertTrue(token1.activate(game));//shuffle
        assertFalse(token2.activate(game));//Do not shuffle
    }

    @Test
    @DisplayName("BlackCross advancement")
    public void blackCrossAdvancementTest(){
        Game game = new Game(1);
        BlackCrossActionToken token1 = new BlackCrossActionToken(1);
        BlackCrossActionToken token2 = new BlackCrossActionToken(2);

        assertEquals(0, game.getVaticanRoute().getTokenList().get(1).getFaithPoints());

        token1.activate(game);

        assertEquals(1, game.getVaticanRoute().getTokenList().get(1).getFaithPoints());

        token2.activate(game);

        assertEquals(3, game.getVaticanRoute().getTokenList().get(1).getFaithPoints());
    }
}
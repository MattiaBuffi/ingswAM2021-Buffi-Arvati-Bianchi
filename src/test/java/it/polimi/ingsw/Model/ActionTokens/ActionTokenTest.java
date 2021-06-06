package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;
import it.polimi.ingsw.Model.TestData.testTerminator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ActionTokenTest {



    @ParameterizedTest
    @ValueSource(ints={1,2})
    void testBlackActionToken(int index) {
        testFaithHandler faithHandler = new testFaithHandler();
        testShuffler shuffler = new testShuffler();
        BlackCrossActionToken actionToken = new BlackCrossActionToken(shuffler,faithHandler, index);

        actionToken.activate();

        assertEquals(index, faithHandler.amount);

        if (index == 1){
            assertTrue(shuffler.shuffled);
        } else {
            assertFalse(shuffler.shuffled);
        }

    }


    @ParameterizedTest
    @ValueSource(booleans={true,false})
    void testDiscardCardToken(boolean canRemove) {

        testTerminator terminator = new testTerminator();
        testRemover remover = new testRemover(canRemove);

        DiscardActionToken actionToken = new DiscardActionToken(remover, DevelopmentCard.Color.BLUE, terminator);

        actionToken.activate();
        assertEquals(DevelopmentCard.Color.BLUE, remover.color);

        int removed= (canRemove)? 2 : 1;

        assertEquals(removed, remover.removed);
        assertEquals(canRemove, !terminator.ended);

    }

    private class testShuffler implements Shuffler{

        public boolean shuffled;


        public testShuffler() {
            this.shuffled = false;
        }

        @Override
        public void shuffle() {
            this.shuffled = true;
        }
    }

    private class testFaithHandler implements FaithHandler{

        public int amount;
        public int give;

        public testFaithHandler(){
            amount = 0;
            give = 0;
        }


        @Override
        public void advance(int amount) {
            this.amount = amount;
        }

        @Override
        public void give(int amount) {
            this.amount = amount;
        }
    }


    private class testRemover implements CardRemover{

        public boolean canRemove;
        public int removed;
        public DevelopmentCard.Color color;

        public testRemover(boolean canRemove){
            removed = 0;
            color = null;
            this.canRemove = canRemove;
        }

        @Override
        public boolean removeCard(DevelopmentCard.Color color, int level) {
            removed = level;
            this.color = color;
            return canRemove;
        }

        @Override
        public boolean removeCard(DevelopmentCard.Color color) {
            removed +=1;
            this.color = color;
            return canRemove;
        }

    }


}
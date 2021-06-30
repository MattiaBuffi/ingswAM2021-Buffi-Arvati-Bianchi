package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleFactory;
import it.polimi.ingsw.Model.ResourceMarket.ResourceMarket;
import it.polimi.ingsw.Model.TestData.TestBroadcaster;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ResourceMarketTest {

    @Test
    @DisplayName("Market random initialization")
    public void initTest(){
        ResourceMarket market = new ResourceMarket(new TestBroadcaster());
        Marble[] stdArray = new Marble[ResourceMarket.MARKET_SIZE];
        Marble[] marketArray = new Marble[ResourceMarket.MARKET_SIZE];

        for (int i=0; i<ResourceMarket.MARKET_SIZE; i++){
            stdArray[i] = MarbleFactory.getMarble(ResourceMarket.MARBLES_COLORS[i]);
        }

        for(int i = 0; i < ResourceMarket.ROW_SIZE; i++){
            for(int j = 0; j < ResourceMarket.COLUMN_SIZE; j++){
                marketArray[(i * 3) + j] = market.getMarblesGrid()[i][j];
            }
        }
        marketArray[12] = market.getBonusMarble();

        assertFalse(Arrays.equals(stdArray, marketArray));

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    public void getRowTest(int pos){

        ResourceMarket market = new ResourceMarket(new TestBroadcaster());
        ArrayList<Marble> tmpArray = new ArrayList<>();

        for(int i = 0; i<ResourceMarket.ROW_SIZE; i++){
            tmpArray.add(market.getMarblesGrid()[i][pos]);
        }

        for (int i = 0; i<ResourceMarket.ROW_SIZE; i++){
            assertEquals(tmpArray.get(i).getColor(), market.get(pos+4).get(i).getColor());
        }

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    public void getColumnTest(int pos){
        ResourceMarket market = new ResourceMarket(new TestBroadcaster());
        ArrayList<Marble> tmpArray = new ArrayList<>();

        for(int i = 0; i<ResourceMarket.COLUMN_SIZE; i++){
            tmpArray.add(market.getMarblesGrid()[pos][i]);
        }

        for (int i = 0; i<ResourceMarket.COLUMN_SIZE; i++){
            assertEquals(tmpArray.get(i).getColor(), market.get(pos).get(i).getColor());
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    public void insertRowTest(int pos){
        ResourceMarket market = new ResourceMarket(new TestBroadcaster());
        Marble oldBonusMarble = market.getBonusMarble();
        Marble newBonusMarble = market.getMarblesGrid()[0][pos];

        market.insertExtra(pos+4);

        assertEquals(newBonusMarble, market.getBonusMarble());
        assertEquals(oldBonusMarble, market.getMarblesGrid()[market.ROW_SIZE -1][pos]);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    public void insertColumnTest(int pos){
        ResourceMarket market = new ResourceMarket(new TestBroadcaster());
        Marble oldBonusMarble = market.getBonusMarble();
        Marble newBonusMarble = market.getMarblesGrid()[pos][0];

        market.insertExtra(pos);

        assertEquals(newBonusMarble, market.getBonusMarble());
        assertEquals(oldBonusMarble, market.getMarblesGrid()[pos][market.COLUMN_SIZE -1]);
    }

}
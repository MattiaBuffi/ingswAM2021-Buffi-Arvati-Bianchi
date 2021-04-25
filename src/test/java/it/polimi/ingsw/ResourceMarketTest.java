package it.polimi.ingsw;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleFactory;
import it.polimi.ingsw.Model.ResourceMarket;
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
        ResourceMarket market = new ResourceMarket();
        Marble[] stdArray = new Marble[ResourceMarket.MARKET_SIZE];
        Marble[] marketArray = new Marble[ResourceMarket.MARKET_SIZE];

        for (int i=0; i<ResourceMarket.MARKET_SIZE; i++){
            stdArray[i] = MarbleFactory.getMarble(ResourceMarket.MARBLES_COLORS[i]);
        }

        for(int i = 0; i < ResourceMarket.MARKET_ROWS; i++){
            for(int j = 0; j < ResourceMarket.MARKET_COLUMN; j++){
                marketArray[(i * 4) + j] = market.getMarblesGrid()[i][j];
            }
        }
        marketArray[12] = market.getBonusMarble();

        assertFalse(Arrays.equals(stdArray, marketArray));

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    public void getRowTest(int pos){
        ResourceMarket market = new ResourceMarket();
        ArrayList<Marble> tmpArray = new ArrayList<>();

        for(int i=0;i<ResourceMarket.MARKET_COLUMN;i++){
            tmpArray.add(market.getMarblesGrid()[pos][i]);
        }

        assertEquals(tmpArray, market.getRow(pos));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    public void insertRowTest(int pos){
        ResourceMarket market = new ResourceMarket();
        Marble oldBonusMarble = market.getBonusMarble();
        Marble newBonusMarble = market.getMarblesGrid()[pos][0];

        market.getRow(pos);

        assertEquals(newBonusMarble, market.getBonusMarble());
        assertEquals(oldBonusMarble, market.getMarblesGrid()[pos][3]);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    public void getColumnTest(int pos){
        ResourceMarket market = new ResourceMarket();
        ArrayList<Marble> tmpArray = new ArrayList<>();

        for(int i=0;i<ResourceMarket.MARKET_ROWS;i++){
            tmpArray.add(market.getMarblesGrid()[i][pos]);
        }

        assertEquals(tmpArray, market.getColumn(pos));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    public void insertColumnTest(int pos){
        ResourceMarket market = new ResourceMarket();
        Marble oldBonusMarble = market.getBonusMarble();
        Marble newBonusMarble = market.getMarblesGrid()[0][pos];

        market.getColumn(pos);

        assertEquals(newBonusMarble, market.getBonusMarble());
        assertEquals(oldBonusMarble, market.getMarblesGrid()[2][pos]);
    }

}
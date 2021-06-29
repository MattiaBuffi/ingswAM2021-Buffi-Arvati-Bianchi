package it.polimi.ingsw.Model.VaticanRoute;

import it.polimi.ingsw.Model.TestData.TestBroadcaster;
import it.polimi.ingsw.Model.TestData.testHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaticanRouteTest {


    @Test
    void testVaticanReport(){
        VaticanRoute route = new VaticanRoute( new TestBroadcaster(), new testHandler());
        List<VaticanToken> tokens = new ArrayList<>();
        tokens.add(new VaticanToken(route, 1,"one"));
        tokens.add(new VaticanToken(route, "two"));

        route.applyVaticanReport(tokens, 1, 5);
        assertEquals(5, tokens.get(0).getVictoryPoints());
        assertEquals(0, tokens.get(1).getVictoryPoints());

    }


    @ParameterizedTest
    @ValueSource(ints={0,1,2,3})
    void testAdvance(int testNum){

        int index = 0;
        int position = 0;

        if(testNum == 3){
            index = 0;
            position = 0;
        } else {
            index = testNum;
            position = VaticanRoute.POPE_SPACES_LOWER_LIMITS[index];
        }

        testHandler terminator = new testHandler();
        VaticanRoute route = new VaticanRoute(new TestBroadcaster(),terminator, index);
        List<VaticanToken> tokens = new ArrayList<>();

        tokens.add(new VaticanToken(route, position,"one"));
        tokens.add(new VaticanToken(route, position,"two"));
        route.addPlayer(tokens.get(0));
        route.addPlayer(tokens.get(1));

        tokens.get(0).advance(5);


        assertEquals(position+5, tokens.get(0).getPosition());
        assertEquals(position, tokens.get(1).getPosition());

        if(testNum == 3){
            assertEquals(0, tokens.get(0).getVictoryPoints());
            assertEquals(0, tokens.get(1).getVictoryPoints());
        } else if(testNum == 2){
            assertEquals(VaticanRoute.LAST_POSITION, tokens.get(0).getPosition());
            assertEquals(VaticanRoute.POPES_FAVOR_VICTORY_POINTS[index], tokens.get(0).getVictoryPoints());
            assertEquals(VaticanRoute.POPES_FAVOR_VICTORY_POINTS[index], tokens.get(1).getVictoryPoints());
            assertTrue(terminator.ended);
        } else {
            assertEquals(VaticanRoute.POPES_FAVOR_VICTORY_POINTS[index], tokens.get(0).getVictoryPoints());
            assertEquals(VaticanRoute.POPES_FAVOR_VICTORY_POINTS[index], tokens.get(1).getVictoryPoints());
        }

    }


}
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


    /**
     * this test try to increment a token position, the new position is inside the allowed limits
     */
    @Test
    void changeTokenPositionOk(){

        testHandler terminator = new testHandler();
        VaticanRoute route = new VaticanRoute(new TestBroadcaster(),terminator);

        VaticanToken token = new VaticanToken(route, "test");

        assertEquals(0, token.getPosition());

        route.setTokenPosition(token, 10);

        assertEquals(10, token.getPosition());
        assertFalse(terminator.ended);

    }


    /**
     * this test try to increment a token position, the new position is inside the allowed limits
     */
    @Test
    void changeTokenPositionToOverflow(){

        testHandler terminator = new testHandler();
        VaticanRoute route = new VaticanRoute(new TestBroadcaster(),terminator);

        VaticanToken token = new VaticanToken(route, "test");

        assertEquals(0, token.getPosition());

        route.setTokenPosition(token, 25);

        assertEquals(24, token.getPosition());
        assertTrue(terminator.ended);

    }




    @Test
    void addRoutePositionVictoryPoints(){
        testHandler terminator = new testHandler();
        VaticanRoute route = new VaticanRoute(new TestBroadcaster(),terminator);

        VaticanToken token = new VaticanToken(route, "test");

        token.setLastPointPosition(1);


    }


    @Test
    void canStartVaticanReport(){
        testHandler terminator = new testHandler();
        VaticanRoute route = new VaticanRoute(new TestBroadcaster(),terminator);

        assertTrue(route.canStartVaticanReport(1, 10, 8));
        assertFalse(route.canStartVaticanReport(1, 0, 8));
        assertFalse(route.canStartVaticanReport(3, 10, 8));

    }










}
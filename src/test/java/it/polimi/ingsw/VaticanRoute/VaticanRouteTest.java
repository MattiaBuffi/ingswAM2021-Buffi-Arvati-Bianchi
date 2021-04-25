package it.polimi.ingsw.VaticanRoute;

import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaticanRouteTest {

    @Test
    public void advanceTest(){
        VaticanRoute route = new VaticanRoute(3);

        assertEquals(0, route.getTokenList().get(0).getFaithPoints());
        assertEquals(0, route.getTokenList().get(1).getFaithPoints());
        assertEquals(0, route.getTokenList().get(2).getFaithPoints());

        route.getTokenList().get(0).advance();

        assertEquals(1, route.getTokenList().get(0).getFaithPoints());

        route.getTokenList().get(0).advance();
        route.getTokenList().get(1).advance();
        route.getTokenList().get(2).advance();

        assertEquals(2, route.getTokenList().get(0).getFaithPoints());
        assertEquals(1, route.getTokenList().get(1).getFaithPoints());
        assertEquals(1, route.getTokenList().get(2).getFaithPoints());
    }

    @Test
    public void vaticanReportTest(){
        VaticanRoute route = new VaticanRoute(3);

        route.getTokenList().get(0).setFaithPoints(7);
        route.getTokenList().get(1).setFaithPoints(5);
        route.getTokenList().get(2).setFaithPoints(2);

        route.getTokenList().get(0).advance();

        assertAll(  () -> assertEquals(2, route.getTokenList().get(0).getVictoryPoints()),
                    () -> assertEquals(2, route.getTokenList().get(1).getVictoryPoints()),
                    () -> assertEquals(0, route.getTokenList().get(2).getVictoryPoints()),
                    () -> assertTrue(VaticanRoute.popeSpaceReached[0])
        );

        route.getTokenList().get(0).setFaithPoints(13);
        route.getTokenList().get(1).setFaithPoints(10);
        route.getTokenList().get(2).setFaithPoints(15);

        route.getTokenList().get(2).advance();

        assertAll(  () -> assertEquals(5, route.getTokenList().get(0).getVictoryPoints()),
                () -> assertEquals(2, route.getTokenList().get(1).getVictoryPoints()),
                () -> assertEquals(3, route.getTokenList().get(2).getVictoryPoints()),
                () -> assertTrue(VaticanRoute.popeSpaceReached[1])
        );
    }

    @Test
    public void endGameVictoryPoints(){
        VaticanRoute route = new VaticanRoute(3);

        route.getTokenList().get(0).setFaithPoints(24);
        route.getTokenList().get(1).setFaithPoints(19);
        route.getTokenList().get(2).setFaithPoints(17);

        route.assignRouteVictoryPoints();

        assertAll(  () -> assertEquals(20, route.getTokenList().get(0).getVictoryPoints()),
                    () -> assertEquals(12, route.getTokenList().get(1).getVictoryPoints()),
                    () -> assertEquals(9, route.getTokenList().get(2).getVictoryPoints())
        );
    }

}
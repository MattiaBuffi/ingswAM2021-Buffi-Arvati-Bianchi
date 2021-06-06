package it.polimi.ingsw.Model.VaticanRoute;

import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameTerminator;

import java.util.ArrayList;
import java.util.List;

public class VaticanRoute {

    public static final int LAST_POSITION = 24;
    public static final int[] POPE_SPACES = {8, 16, 24};
    public static final int[] POPES_FAVOR_VICTORY_POINTS = {2, 3, 4};
    public static final int[] POPE_SPACES_LOWER_LIMITS = {5, 12, 19};
    protected static final int VICTORY_POINTS_STEP = 3;
    protected static final int[] ROUTE_VICTORY_POINTS = {1, 2, 4, 6, 9, 12, 16, 20};

    public int popeSpaceReached;

    private ArrayList<VaticanToken> tokenList;
    private GameTerminator gameTerminator;


    public VaticanRoute(EventBroadcaster broadcaster, GameTerminator gameTerminator) {
        this.tokenList = new ArrayList<>();
        this.gameTerminator = gameTerminator;
        this.popeSpaceReached = 0;
    }

    public VaticanRoute(EventBroadcaster broadcaster, GameTerminator gameTerminator, int popeSpaceReached) {
        this.tokenList = new ArrayList<>();
        this.gameTerminator = gameTerminator;
        this.popeSpaceReached = popeSpaceReached;
    }



    public boolean addPlayer(VaticanToken token){
        if(token.getPosition() >= POPE_SPACES[popeSpaceReached]){
            return false;
        } else {
            if(tokenList.size() >= 2){
                token.setPosition(1);
            }
            return this.tokenList.add(token);
        }
    }

    public ArrayList<VaticanToken> getTokenList() {
        return new ArrayList<>(tokenList);
    }

    public int getPopeSpaceReached() {
        return popeSpaceReached;
    }



    protected void advance(VaticanToken token, int points){

        int newPosition = token.getPosition() + points;

        if(newPosition >= POPE_SPACES[popeSpaceReached]){
            vaticanReport( tokenList, POPE_SPACES_LOWER_LIMITS[popeSpaceReached], POPES_FAVOR_VICTORY_POINTS[popeSpaceReached]);
            popeSpaceReached +=1;
        }

        if(newPosition >= LAST_POSITION){
            token.setPosition(LAST_POSITION);
            gameTerminator.endGame();
        } else {
            token.setPosition(newPosition);
        }

    }


    public void vaticanReport(List<VaticanToken> tokens, int lowerLimit, int popesFavorVictoryPoints){
        for(VaticanToken t: tokens){
            if(t.getPosition() >= lowerLimit){
                t.setVictoryPoint( t.getVictoryPoints()+popesFavorVictoryPoints );
            }
        }
    }


    public void assignRouteVictoryPoints(){
        for(VaticanToken t: tokenList){
            int n = t.getPosition()/VICTORY_POINTS_STEP;
            t.setPosition( t.getPosition() + ROUTE_VICTORY_POINTS[n-1] );
        }
    }



}

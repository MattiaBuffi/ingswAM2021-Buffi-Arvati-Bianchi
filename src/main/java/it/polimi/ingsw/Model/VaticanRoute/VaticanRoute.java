package it.polimi.ingsw.Model.VaticanRoute;

import it.polimi.ingsw.Message.Model.VaticanReport;
import it.polimi.ingsw.Message.Model.VaticanRoutePosition;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class VaticanRoute {

    public static final int LAST_POSITION = 24;
    public static final int[] POPE_SPACES = {8, 16, 24};
    public static final int[] POPES_FAVOR_VICTORY_POINTS = {2, 3, 4};
    public static final int[] POPE_SPACES_LOWER_LIMITS = {5, 12, 19};
    protected static final int VICTORY_POINTS_STEP = 3;
    protected static final int[] ROUTE_VICTORY_POINTS = {1, 2, 4, 6, 9, 12, 16, 20};

    public int popeSpaceReached;

    private ArrayList<VaticanToken> tokenList;
    private GameHandler gameHandler;
    private EventBroadcaster broadcaster;


    public VaticanRoute(EventBroadcaster broadcaster, GameHandler gameHandler) {
        this.tokenList = new ArrayList<>();
        this.gameHandler = gameHandler;
        this.broadcaster = broadcaster;
        this.popeSpaceReached = 0;
    }

    public VaticanRoute(EventBroadcaster broadcaster, GameHandler gameHandler, int popeSpaceReached) {
        this.tokenList = new ArrayList<>();
        this.gameHandler = gameHandler;
        this.broadcaster = broadcaster;
        this.popeSpaceReached = popeSpaceReached;
    }

    public int getInitialPosition(int playOrder){
        if(playOrder < 3){
            return 0;
        } else {
            return 1;
        }
    }

    public VaticanToken addPlayer(String username){
        VaticanToken token = new VaticanToken(this, username);
        tokenList.add(token);
        token.setPosition(getInitialPosition(tokenList.size()));
        broadcaster.notifyAllPlayers(new VaticanRoutePosition(username, token.getPosition()));
        return token;
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



    public boolean vaticanReport(List<VaticanToken> tokens, int newPosition, int triggerPosition, int lowerLimit, int victoryPoints){
        if(newPosition < triggerPosition){
            return false;
        }

        applyVaticanReport( tokens, lowerLimit, victoryPoints);
        return true;
    }


    protected void advance(VaticanToken token, int points){

        int newPosition = token.getPosition() + points;

        if(vaticanReport(tokenList, newPosition, POPE_SPACES[popeSpaceReached], POPE_SPACES_LOWER_LIMITS[popeSpaceReached],POPES_FAVOR_VICTORY_POINTS[popeSpaceReached])){
            popeSpaceReached +=1;
        }

        if(newPosition >= LAST_POSITION){
            token.setPosition(LAST_POSITION);
            broadcaster.notifyAllPlayers(new VaticanRoutePosition(token.getOwner(), LAST_POSITION));
            gameHandler.endGame();
        } else {
            token.setPosition(newPosition);
            broadcaster.notifyAllPlayers(new VaticanRoutePosition(token.getOwner(), newPosition));
        }

    }



    public void applyVaticanReport(List<VaticanToken> tokens, int lowerLimit, int popesFavorVictoryPoints){

        List<String> userList = new ArrayList<>();

        for(VaticanToken t: tokens){
            if(t.getPosition() >= lowerLimit){
                t.setVictoryPoint( t.getVictoryPoints()+popesFavorVictoryPoints );
                userList.add(t.getOwner());
            }
        }

        broadcaster.notifyAllPlayers(new VaticanReport(popeSpaceReached+1, userList));
    }


    public void assignRouteVictoryPoints(){
        for(VaticanToken t: tokenList){
            int n = t.getPosition()/VICTORY_POINTS_STEP;
            t.setPosition( t.getPosition() + ROUTE_VICTORY_POINTS[n-1] );
        }
    }



}

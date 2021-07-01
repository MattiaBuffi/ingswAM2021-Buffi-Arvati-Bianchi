package it.polimi.ingsw.Model.VaticanRoute;

import it.polimi.ingsw.Message.Model.VaticanReport;
import it.polimi.ingsw.Message.Model.VaticanRoutePosition;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Represent the vatican route where player token could moves during the game
 */
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

    /**
     * Get initial position of the tokens based on players order are in the game
     * @param playOrder number of player in the game
     * @return starting position
     */
    public int getInitialPosition(int playOrder){
        if(playOrder < 3){
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Add a token to the route with the username equals to the one specified as parameter
     * @param username username of the player to add to the route
     * @return a new token with the username specified
     */
    public VaticanToken addPlayer(String username){
        VaticanToken token = new VaticanToken(this, username);
        tokenList.add(token);
        token.setPosition(getInitialPosition(tokenList.size()));
        broadcaster.notifyAllPlayers(new VaticanRoutePosition(username, token.getPosition()));
        return token;
    }

    /**
     * Add the token as parameter to the to the token list
     * @param token token to add to the vatican route
     * @return true if the token is correctly added, false if the token has a position greater than the last vatican
     * report reached
     */
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

    /**
     * Methods who check the activation of a vatican report
     * @param tokens list of tokens of the route
     * @param newPosition position to check
     * @param triggerPosition position of the vatican report
     * @param lowerLimit lower limit to activate the vatican report
     * @param victoryPoints victory points assigned by the vatican report
     * @return true if the report is activate, otherwise false
     */
    public boolean vaticanReport(List<VaticanToken> tokens, int newPosition, int triggerPosition, int lowerLimit, int victoryPoints){
        if(newPosition < triggerPosition){
            return false;
        }

        applyVaticanReport( tokens, lowerLimit, victoryPoints);
        return true;
    }

    /**
     * Advance the position of the selected token by the number of points specified as parameter.
     * @param token token which to advance its position
     * @param points points to add to the older position
     * @see VaticanRoutePosition the method generate a message with the information of the token's new position
     */
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

    /**
     * Apply the vatican report to the tokens of the list that have a position greater or equals than the lower limit
     * @param tokens list of the token in the route
     * @param lowerLimit limit to check to receive the vatican report
     * @param popesFavorVictoryPoints points to assign if the vatican report is activated
     * @see VaticanReport the method generate a message with the list of player who activated the vatican report
     */
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

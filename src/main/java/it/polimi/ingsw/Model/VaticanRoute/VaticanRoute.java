package it.polimi.ingsw.Model.VaticanRoute;

import java.util.ArrayList;

public class VaticanRoute {
    protected static final int[] POPE_SPACES = {8, 16, 24};
    protected static final int[] POPES_FAVOR_VICTORY_POINTS = {2, 3, 4};
    protected static final int[] POPE_SPACES_LOWER_LIMITS = {5, 12, 19};
    protected static final int VICTORY_POINTS_STEP = 3;
    protected static final int[] ROUTE_VICTORY_POINTS = {1, 2, 4, 6, 9, 12, 16, 20};
    public static boolean[] popeSpaceReached = {false, false, false};

    private ArrayList<FaithToken> tokenList = new ArrayList<>();
    private int numPlayers;

    public VaticanRoute(int numPlayers) {
        this.numPlayers = numPlayers;
        if(numPlayers == 1){
            addPlayerToken();
            addBlackCrossToken();
        } else {
            for (int i = 0; i < numPlayers; i++) {
                addPlayerToken();
            }
        }
    }

    protected void addPlayerToken(){
        tokenList.add(new PlayerToken(this));
    }

    protected void addBlackCrossToken(){
        tokenList.add(new BlackCrossToken(this));
    }

    public ArrayList<FaithToken> getTokenList() {
        return tokenList;
    }

    protected void checkPopeSpace(FaithToken token){
        if(token.getFaithPoints() == POPE_SPACES[0] && !popeSpaceReached[0]){
            vaticanReport(POPE_SPACES_LOWER_LIMITS[0], POPES_FAVOR_VICTORY_POINTS[0]);
            popeSpaceReached[0] = true;
        } else if (token.getFaithPoints() == POPE_SPACES[1] && !popeSpaceReached[1]){
            vaticanReport(POPE_SPACES_LOWER_LIMITS[1], POPES_FAVOR_VICTORY_POINTS[1]);
            popeSpaceReached[1] = true;
        } else if (token.getFaithPoints() == POPE_SPACES[2] && !popeSpaceReached[2]) {
            vaticanReport(POPE_SPACES_LOWER_LIMITS[2], POPES_FAVOR_VICTORY_POINTS[2]);
            popeSpaceReached[2] = true;
            if(numPlayers == 1){
                if(token.playerWin()){
                    //PLAYER WIN
                }else{
                    //BLACK CROSS WIN
                }
            }
        }
    }

    private void vaticanReport(int lowerLimit, int popesFavorVictoryPoints){
        for(FaithToken t: tokenList){
            if(t.getFaithPoints() >= lowerLimit){
                t.addVictoryPoints(popesFavorVictoryPoints);
            }
        }
    }

    public void assignRouteVictoryPoints(){
        for(FaithToken t: tokenList){
            int n = t.getFaithPoints()/VICTORY_POINTS_STEP;
            t.addVictoryPoints(ROUTE_VICTORY_POINTS[n-1]);
        }
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}

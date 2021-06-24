package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Message.Model.ActionTokenPlayed;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;


public class BlackCrossActionToken implements ActionToken{

    private FaithHandler token;
    private int level;
    private Shuffler shuffler;
    private EventBroadcaster broadcaster;

    public BlackCrossActionToken(int level, FaithHandler token, Shuffler shuffler, EventBroadcaster broadcaster) {
        this.token = token;
        this.level = level;
        this.shuffler = shuffler;
        this.broadcaster = broadcaster;
    }

    @Override
    public void activate() {
        this.token.advance(level);
        if(level == 1){
            shuffler.shuffle();
        }
        broadcaster.notifyAllPlayers(new ActionTokenPlayed());
    }


}

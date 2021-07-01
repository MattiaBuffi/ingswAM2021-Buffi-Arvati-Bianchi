package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Message.Model.ActionTokenPlayed;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;


/**
 * Represent an action token that move the position of the black cross (cpu token) on the Vatican route
 */
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

    /**
     * Advances the black cross on the vatican route based on the level of the token. If the token is level 1 the method
     * also shuffle the action tokens list.
     * @see ActionTokenPlayed The method also generate an ActionTokenPlayed message and notify it to all the player
     */
    @Override
    public void activate() {
        this.token.advance(level);
        if(level == 1){
            shuffler.shuffle();
        }
        broadcaster.notifyAllPlayers(new ActionTokenPlayed("Black cross token lv "+level+" played"));
    }


}

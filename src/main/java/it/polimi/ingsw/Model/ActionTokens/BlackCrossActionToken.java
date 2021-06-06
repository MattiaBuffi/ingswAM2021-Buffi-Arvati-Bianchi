package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Model.VaticanRoute.FaithHandler;


public class BlackCrossActionToken implements ActionToken{

    private FaithHandler token;
    private int level;
    private Shuffler shuffler;

    public BlackCrossActionToken(Shuffler shuffler, FaithHandler token, int level) {
        this.token = token;
        this.level = level;
        this.shuffler = shuffler;
    }

    @Override
    public void activate() {
        this.token.advance(level);
        if(level == 1){
            shuffler.shuffle();
        }
    }


}

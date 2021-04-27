package it.polimi.ingsw.Model.Player.MarketBuffer;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.VaticanRoute.FaithToken;

public class RedAction extends MarbleAction {


    public void advanceToken(FaithToken token){
        token.advance();
    }


    @Override
    public void applyAction(Player player) {
        advanceToken(player.faithToken);
    }


}

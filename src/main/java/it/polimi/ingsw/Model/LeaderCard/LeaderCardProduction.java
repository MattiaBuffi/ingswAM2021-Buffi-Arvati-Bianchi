package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Player.Player;

public class LeaderCardProduction extends LeaderCard {

    private LeaderCard card;

    public LeaderCardProduction(LeaderCard card){
        this.card = card;
    }

    @Override
    public void activate(Player player) {

    }
}

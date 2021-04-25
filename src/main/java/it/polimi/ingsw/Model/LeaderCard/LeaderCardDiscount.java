package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.Player.Player;

public class LeaderCardDiscount extends LeaderCard {

    private ResourceList discount;

    public LeaderCardDiscount(ResourceList discount){
        this.discount = discount;
    }

    @Override
    public void activate(Player player) {

    }


}

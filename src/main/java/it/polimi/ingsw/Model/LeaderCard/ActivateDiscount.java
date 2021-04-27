package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.ActionStrategy.BuyCardDiscounted;

public class ActivateDiscount implements ActivationStrategy {

    private ResourceList discount;

    public ActivateDiscount(ResourceList discount){
        this.discount = discount;
    }

    @Override
    public void execute(Player player) {
        player.buyCardStrategy = new BuyCardDiscounted(player.buyCardStrategy, discount);
    }

}

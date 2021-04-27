package it.polimi.ingsw.Model.Player.ActionStrategy;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardMarket.PurchasableCardDiscounted;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.Player.Player;

public class BuyCardDiscounted implements BuyCardStrategy {

    private BuyCardStrategy strategy;
    private ResourceList discount;

    public BuyCardDiscounted(BuyCardStrategy strategy, ResourceList discount) {
        this.strategy = strategy;
        this.discount = discount;
    }

    @Override
    public boolean execute(Player player, PurchasableCard card, String destinationId) {
        strategy.execute(player, new PurchasableCardDiscounted(card, discount), destinationId);
        return true;
    }

}

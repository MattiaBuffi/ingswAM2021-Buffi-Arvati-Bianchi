package it.polimi.ingsw.Model.Player.ActionStrategy;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.Player.Player;


public class BuyCardBase implements BuyCardStrategy {

    @Override
    public boolean execute(Player player, PurchasableCard card, String destId) {
        player.cardStorage.buyCard(card, destId, player.resourceStorage);
        return true;
    }

}

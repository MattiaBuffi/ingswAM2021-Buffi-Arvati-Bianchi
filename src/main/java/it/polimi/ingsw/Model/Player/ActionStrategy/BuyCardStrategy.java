package it.polimi.ingsw.Model.Player.ActionStrategy;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.Player.Player;


public interface BuyCardStrategy {
    boolean execute(Player player, PurchasableCard card, String destinationId);
}

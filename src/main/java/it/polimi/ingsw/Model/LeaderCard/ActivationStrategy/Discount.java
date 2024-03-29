package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.CardStorage.CardDiscountDecorator;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;


/**
 * Represent a Discount Leader Card
 */


public class Discount implements ActivationStrategy{

    private final Marble.Color color;

    public Discount(Marble.Color color) {
        this.color = color;
    }



    @Override
    public Marble.Color getColor() {
        return color;
    }

    @Override
    public Type getType() {
        return Type.DISCOUNT;
    }

    @Override
    public boolean activate(Player player, LeaderCard card) {
        player.setCardStorage(new CardDiscountDecorator(player.getCardStorage(), color));
        return true;
    }

}

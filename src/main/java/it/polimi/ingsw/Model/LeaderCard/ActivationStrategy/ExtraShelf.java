package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelvesExtra;

/**
 * Represent a Extra Shelf Leader Card
 */


public class ExtraShelf  implements ActivationStrategy {

    private final Marble.Color color;

    public ExtraShelf(Marble.Color color) {
        this.color = color;
    }

    @Override
    public Type getType() {
        return Type.EXTRA_SHELF;
    }

    @Override
    public Marble.Color getColor() {
        return color;
    }

    @Override
    public boolean activate(Player player, LeaderCard card) {
        player.getResourceStorage().setShelves(new ShelvesExtra(player.getResourceStorage().getShelves(), player, color));
        return true;
    }

}

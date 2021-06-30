package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.CardStorage.ExtraProductionDecorator;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ProductionCard.LeaderProduction;

public class ExtraProduction implements ActivationStrategy {

    private final Marble.Color color;

    public ExtraProduction(Marble.Color color) {
        this.color = color;
    }

    @Override
    public Type getType() {
        return Type.EXTRA_PRODUCTION;
    }

    @Override
    public Marble.Color getColor() {
        return color;
    }

    @Override
    public boolean activate(Player player, LeaderCard card) {
        player.setCardStorage(new ExtraProductionDecorator(player.getCardStorage(), new LeaderProduction(card.getId(), color)));
        return true;
    }
}

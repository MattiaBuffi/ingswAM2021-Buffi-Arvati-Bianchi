package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.CardStorage.ExtraProductionDecorator;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ProductionCard.LeaderProduction;



public class ActivateProduction implements ActivationStrategy {

    private LeaderProduction card;

    public ActivateProduction(LeaderProduction card){
        this.card = card;
    }

    @Override
    public void execute(Player player) {
        player.cardStorage = new ExtraProductionDecorator(player.cardStorage, card);



    }


}

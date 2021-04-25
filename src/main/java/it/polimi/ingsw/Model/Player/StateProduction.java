package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

import java.util.List;

public class StateProduction extends PlayerState {


    @Override
    protected void invalidAction() {

    }

    @Override
    protected void production(ProductionSelector selector) {
        CardStorage storage = context.getCardStorage();
        ProductionCard card = storage.getCard(selector);
        List<Marble> production = card.make(context.getResourceStorage());
        //handleProduction??
    }



}
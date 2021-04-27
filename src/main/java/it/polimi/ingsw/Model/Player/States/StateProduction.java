package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

import java.util.List;

public class StateProduction extends PlayerState {


    private List<String> usedProduction;

    @Override
    protected void invalidAction() {

    }


    @Override
    protected void production(ProductionSelector selector) {
        CardStorage storage = context.cardStorage;
        ProductionCard card = storage.getCard(selector);
        if(usedProduction.contains(card.getId())){
            return;
        }
        usedProduction.add(card.getId());
        List<Marble> production = card.make(context.resourceStorage);
        context.productionBuffer.fill(production);

    }


    @Override
    protected void endTurn(){
        usedProduction.clear();
        context.productionBuffer.empty();
    }



}
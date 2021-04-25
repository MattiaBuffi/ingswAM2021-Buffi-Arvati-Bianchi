package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public interface CardStorage {

    void buyCard(MarketCard card, String destId, ResourceStorage storage);
    ProductionCard getCard(ProductionSelector selector);


}

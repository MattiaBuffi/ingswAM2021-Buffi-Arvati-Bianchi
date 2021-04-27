package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.CardStorage.CardSlot;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public interface PurchasableCard {
    boolean buy(CardSlot cardSlot, ResourceStorage storage);
}

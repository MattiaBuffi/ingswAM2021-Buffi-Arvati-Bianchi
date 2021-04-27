package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.CardStorage.CardSlot;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public class PurchasableCardDiscounted implements PurchasableCard {


    private final PurchasableCard card;
    private final ResourceList discount;

    private class DecoratorDiscount implements ResourceStorage {

        private final ResourceList discount;
        private ResourceStorage storage;

        public DecoratorDiscount(ResourceStorage storage, ResourceList discount) {
            this.storage = storage;
            this.discount = discount;
        }


        @Override
        public void deposit(ResourceList resourceList) {
            storage.deposit(resourceList);
        }

        @Override
        public boolean withdrawal(ResourceList resourceList) {
            return storage.withdrawal(resourceList.subtract(discount));
        }

        @Override
        public ResourceList getResources() {
            return storage.getResources().sum(discount);
        }


    }

    public PurchasableCardDiscounted(PurchasableCard card, ResourceList discount) {
        this.card = card;
        this.discount = discount;
    }


    public boolean buy(CardSlot cardSlot, ResourceStorage storage) {
        return card.buy(cardSlot, new DecoratorDiscount(storage, discount));
    }

}

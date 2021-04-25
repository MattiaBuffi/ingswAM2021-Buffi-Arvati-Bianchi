package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.ResourceList;

public class DiscountDecorator implements ResourceStorage {

    private final ResourceList discount;
    private PlayerStorage storage;

    public DiscountDecorator(PlayerStorage storage,ResourceList discount) {
        this.storage = storage;
        this.discount = discount;
    }


    @Override
    public void deposit(ResourceList resourceList) {
        storage.deposit(resourceList);
    }

    @Override
    public void withdrawal(ResourceList resourceList) {
        //need to check subtract behavior
        storage.withdrawal(resourceList.subtract(discount));
    }

    @Override
    public ResourceList getResources() {
        return storage.getResources().sum(discount);
    }


}

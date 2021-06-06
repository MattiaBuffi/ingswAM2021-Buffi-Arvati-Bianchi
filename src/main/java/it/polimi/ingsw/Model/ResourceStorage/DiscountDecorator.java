package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

public class DiscountDecorator implements ResourceStorage {

    private final Marble.Color discount;
    private ResourceStorage storage;


    public DiscountDecorator(ResourceStorage storage, Marble.Color discount) {
        this.storage = storage;
        this.discount = discount;
    }



    @Override
    public boolean deposit(ResourceList resourceList) {
        return storage.deposit(resourceList);
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
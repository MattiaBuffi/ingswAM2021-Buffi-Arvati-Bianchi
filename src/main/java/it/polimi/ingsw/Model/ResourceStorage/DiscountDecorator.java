package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

/**
 * Decorator of PlayerStorage. Take the request of withdrawal and reduce it by the discount
 */
public class DiscountDecorator implements ResourceStorage {

    private final Marble.Color discount;
    private ResourceStorage storage;


    public DiscountDecorator(ResourceStorage storage, Marble.Color discount) {
        this.storage = storage;
        this.discount = discount;
    }

    /**
     * Forward the deposit request to the decorated storage
     * {@inheritDoc}
     */
    @Override
    public boolean deposit(ResourceList resourceList) {
        return storage.deposit(resourceList);
    }

    /**
     * Forward the withdraw request to the decorated storage with the resource list reduced by the discount
     * {@inheritDoc}
     */
    @Override
    public boolean withdrawal(ResourceList resourceList) {
        return storage.withdrawal(resourceList.subtract(discount));
    }


    /**
     * Return the list of resources in the storage with the discount resource added
     * @return resources in the storage summed with the discount
     */
    @Override
    public ResourceList getResources() {
        return storage.getResources().sum(discount);
    }


}
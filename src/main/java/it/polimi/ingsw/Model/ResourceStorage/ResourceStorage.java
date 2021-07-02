package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.ResourceList;

public interface ResourceStorage {


    /**
     * Deposit the resources into the chest
     * @param resourceList list of resources to deposit
     * @return true if the resource could be added and false if not
     */
    boolean deposit(ResourceList resourceList);


    /**
     * Withdraw resources from the storage with shelves priority over chest
     * @param resourceList list of resources to deposit
     * @return true if the resource could be added and false if not
     */
    boolean withdrawal(ResourceList resourceList);

    ResourceList getResources();
}

package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.ResourceList;

public interface ResourceStorage {

    void deposit(ResourceList resourceList);
    void withdrawal(ResourceList resourceList);
    ResourceList getResources();
}

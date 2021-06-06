package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.ResourceList;

public interface ResourceStorage {

    boolean deposit(ResourceList resourceList);
    boolean withdrawal(ResourceList resourceList);
    ResourceList getResources();
}

package it.polimi.ingsw.Model.ResourceStorage;


import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerStorageBasicTest {



    @Test
    public void deposit() {
        PlayerStorageBasic storageBasic = new PlayerStorageBasic();
        ResourceList list = new ResourceList();
        list.add(MarbleColor.GREY);

        storageBasic.deposit(list);

        assertEquals(0, storageBasic.getShelves().getResources().getSize());
        assertEquals(1, storageBasic.getResources().getSize());
    }

    @Test
    public void withdrawal() {
        PlayerStorageBasic storageBasic = new PlayerStorageBasic();
        ResourceList withdrawnList = new ResourceList();
        withdrawnList.add(MarbleColor.GREY,4);
        withdrawnList.add(MarbleColor.BLUE,2);

        ResourceList deposit = new ResourceList();
        deposit.add(MarbleColor.GREY,4);
        deposit.add(MarbleColor.BLUE,4);

        storageBasic.store(MarbleColor.BLUE, storageBasic.getShelves().getShelves().get(1).getId());
        storageBasic.store(MarbleColor.GREY, storageBasic.getShelves().getShelves().get(0).getId());
        storageBasic.deposit(deposit);

        assertTrue(storageBasic.withdrawal(withdrawnList));
        assertEquals(0, storageBasic.getShelves().getResources().getSize());
        assertEquals(4, storageBasic.getResources().getSize());

    }




}
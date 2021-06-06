package it.polimi.ingsw.Model.ResourceStorage;


import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.TestData.TestBroadcaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerStorageTest {



    @Test
    public void deposit() {
        TestBroadcaster broadcaster = new TestBroadcaster();
        PlayerStorage storageBasic = new PlayerStorage(broadcaster);
        ResourceList list = new ResourceList();
        list.add(Marble.Color.GREY);

        storageBasic.deposit(list);

        assertEquals(0, storageBasic.getShelves().getResources().getSize());
        assertEquals(1, storageBasic.getResources().getSize());
    }

    @Test
    public void withdrawal() {
        TestBroadcaster broadcaster = new TestBroadcaster();
        PlayerStorage storageBasic = new PlayerStorage(broadcaster);
        ResourceList withdrawnList = new ResourceList();
        withdrawnList.add(Marble.Color.GREY,4);
        withdrawnList.add(Marble.Color.BLUE,2);

        ResourceList deposit = new ResourceList();
        deposit.add(Marble.Color.GREY,4);
        deposit.add(Marble.Color.BLUE,4);

        storageBasic.store(Marble.Color.BLUE, 1);
        storageBasic.store(Marble.Color.GREY, 0);
        storageBasic.deposit(deposit);

        assertTrue(storageBasic.withdrawal(withdrawnList));
        assertEquals(0, storageBasic.getShelves().getResources().getSize());
        assertEquals(4, storageBasic.getResources().getSize());

    }




}
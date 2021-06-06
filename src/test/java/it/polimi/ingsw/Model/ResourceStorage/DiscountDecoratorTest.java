package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDecoratorTest {

    private class StorageTest implements ResourceStorage{

        public ResourceList withdrawalRequest;
        public ResourceList fakeResources;

        public ResourceList getWithdrawalRequest() {
            return withdrawalRequest;
        }

        public ResourceList getFakeResources() {
            return fakeResources;
        }

        public void setWithdrawalRequest(ResourceList withdrawalRequest) {
            this.withdrawalRequest = withdrawalRequest;
        }

        public void setFakeResources(ResourceList fakeResources) {
            this.fakeResources = fakeResources;
        }

        @Override
        public boolean deposit(ResourceList resourceList) {
            //nothing
            return true;
        }

        @Override
        public boolean withdrawal(ResourceList resourceList) {
            withdrawalRequest = resourceList;
            return true;
        }

        @Override
        public ResourceList getResources() {
            return fakeResources;
        }
    }



    @Test
    void getResource() {
        StorageTest storageTest = new StorageTest();
        ResourceList testList = new ResourceList();
        testList.add(Marble.Color.GREY, 3);
        storageTest.setFakeResources(testList);

        DiscountDecorator decorator = new DiscountDecorator(storageTest, Marble.Color.GREY);

        ResourceList resources = decorator.getResources();
        assertEquals(4, resources.getSize(Marble.Color.GREY));
    }


    @Test
    void withdrawal() {
        StorageTest storageTest = new StorageTest();
        ResourceList testList = new ResourceList();
        testList.add(Marble.Color.GREY, 3);

        DiscountDecorator decorator = new DiscountDecorator(storageTest, Marble.Color.GREY);

        decorator.withdrawal(testList);
        assertEquals(2,  storageTest.withdrawalRequest.getSize(Marble.Color.GREY));
    }

}
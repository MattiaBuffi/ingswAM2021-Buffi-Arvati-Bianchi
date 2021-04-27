package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ShelvesBaseTest {

    private ShelvesBase shelves;

    private Shelf getShelfFromIndex(int index){
        return shelves.getShelves().get(index);
    }



    @BeforeEach
    public void init(){
        shelves = new ShelvesBase();
    }

    @Test
    public void initialization(){
        for (int i = 0; i < shelves.getShelves().size(); i++) {
            assertEquals(i+1, getShelfFromIndex(i).getMaxSize());
            assertEquals(0, getShelfFromIndex(i).getSize());
            assertNull(getShelfFromIndex(i).getColor());
        }
    }

    @ParameterizedTest
    @ValueSource(ints={0,1,2})
    public void add(int index){
        for (int i = 0; i < getShelfFromIndex(index).getMaxSize(); i++) {
            boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(index).getId());
            assertTrue(result);
            assertEquals(i+1,  getShelfFromIndex(index).getSize());
            assertEquals(MarbleColor.BLUE, getShelfFromIndex(index).getColor());
        }
    }

    /**try to add a marble to a shelf when it's full
     */
    @Test
    public void addToFull(){
        getShelfFromIndex(1).add(MarbleColor.BLUE,2);
        assertTrue(getShelfFromIndex(1).isFull());
        //test
        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(1).getId());
        assertFalse(result);
        assertEquals(2,  getShelfFromIndex(1).getSize());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(1).getColor());
    }

    /**try to add a marble of certain color to an empty shelf when marbles of the same color are already stored in another shelf
     */
    @Test
    public void addPresentColor(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);

        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(1).getId());
        assertFalse(result);
        assertEquals(0,  getShelfFromIndex(1).getSize());
        assertNull(getShelfFromIndex(1).getColor());
    }

    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.BLUE,1);
        toRemove.add(MarbleColor.GREY,2);
        toRemove.add(MarbleColor.PURPLE,3);

        //test
        boolean result = shelves.remove(toRemove);
        assertTrue(result);
        for (int i = 0; i < 3; i++){
            assertEquals(0, getShelfFromIndex(i).getSize());
            assertNull(getShelfFromIndex(i).getColor());
        }
    }

    /**try to remove some marbles
     */
    @Test
    public void removeSome(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.GREY,1);
        toRemove.add(MarbleColor.PURPLE,2);

        boolean remove = shelves.remove(toRemove);
        assertTrue(remove);

        assertEquals(1, getShelfFromIndex(1).getSize());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());

        assertEquals(1, getShelfFromIndex(2).getSize());
        assertEquals(MarbleColor.PURPLE, getShelfFromIndex(2).getColor());


    }

    /**try to remove marbles that aren't stored
     */
    @Test
    public void removeNotPresent(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.PURPLE,2);
        toRemove.add(MarbleColor.BLUE,2);

        //test
        boolean result = shelves.remove(toRemove);
        assertFalse(result);
        //check that the first shelf isn't changed
        assertEquals(1, getShelfFromIndex(0).getSize());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(0).getColor());
        //check that the second shelf isn't changed
        assertEquals(2, getShelfFromIndex(1).getSize());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());


    }


    @Test
    public void moveCorrect(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,1);
        String firstId = getShelfFromIndex(0).getId();
        String secondId = getShelfFromIndex(1).getId();


        //test
        boolean result = shelves.move(getShelfFromIndex(0).getId(), getShelfFromIndex(1).getId());
        assertTrue(result);

        assertEquals(firstId, getShelfFromIndex(1).getId());
        assertEquals(secondId, getShelfFromIndex(0).getId());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(1).getColor());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(0).getColor());
    }


    @Test
    public void moveWrong(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        String firstId = getShelfFromIndex(0).getId();
        String secondId = getShelfFromIndex(1).getId();


        //test
        boolean result = shelves.move(getShelfFromIndex(0).getId(), getShelfFromIndex(1).getId());
        assertFalse(result);

        assertEquals(firstId, getShelfFromIndex(0).getId());
        assertEquals(secondId, getShelfFromIndex(1).getId());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(0).getColor());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());
    }



}
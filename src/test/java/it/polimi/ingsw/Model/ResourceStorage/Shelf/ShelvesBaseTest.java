package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.TestData.TestBroadcaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class ShelvesBaseTest {

    private static int FIRST = 0;
    private static int SECOND = 1;
    private static int THIRD = 2;


    private ShelvesBase shelves;
    private TestBroadcaster broadcaster;


    @BeforeEach
    public void init(){
        broadcaster = new TestBroadcaster();
        shelves = new ShelvesBase(broadcaster);
    }

    @Test
    public void initialization(){
        for (int i = 0; i < shelves.getShelves().size(); i++) {
            assertEquals(i+1, shelves.getShelf(i).getMaxSize());
            assertEquals(0, shelves.getShelf(i).getSize());
            assertNull(shelves.getShelf(i).getColor());
        }
    }

    @ParameterizedTest
    @ValueSource(ints={0,1,2})
    public void add(int index){
        for (int i = 0; i < shelves.getShelf(index).getMaxSize(); i++) {
            boolean result = shelves.store(Marble.Color.BLUE, index);
            assertTrue(result);
            assertEquals(i+1,  shelves.getShelf(index).getSize());
            assertEquals(Marble.Color.BLUE, shelves.getShelf(index).getColor());
        }
    }

    /**try to add a marble to a shelf when it's full
     */
    @Test
    public void addToFull(){
        shelves.getShelf(SECOND).add(Marble.Color.BLUE,2);
        assertTrue(shelves.getShelf(SECOND).isFull());
        //test
        boolean result = shelves.store(Marble.Color.BLUE, SECOND);
        assertFalse(result);
        assertEquals(2,  shelves.getShelf(SECOND).getSize());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(SECOND).getColor());
    }

    /**try to add a marble of certain color to an empty shelf when marbles of the same color are already stored in another shelf
     */
    @Test
    public void addPresentColor(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);

        boolean result = shelves.store(Marble.Color.BLUE, SECOND);
        assertFalse(result);
        assertEquals(0,  shelves.getShelf(SECOND).getSize());
        assertNull(shelves.getShelf(SECOND).getColor());
    }

    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.BLUE,1);
        toRemove.add(Marble.Color.GREY,2);
        toRemove.add(Marble.Color.PURPLE,3);

        //test
        boolean result = shelves.withdraw(toRemove);
        assertTrue(result);
        for (int i = 0; i < 3; i++){
            assertEquals(0, shelves.getShelf(i).getSize());
            assertNull(shelves.getShelf(i).getColor());
        }
    }

    /**try to remove some marbles
     */
    @Test
    public void removeSome(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.GREY,1);
        toRemove.add(Marble.Color.PURPLE,2);

        boolean remove = shelves.withdraw(toRemove);
        assertTrue(remove);

        assertEquals(1, shelves.getShelf(SECOND).getSize());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());

        assertEquals(1, shelves.getShelf(THIRD).getSize());
        assertEquals(Marble.Color.PURPLE, shelves.getShelf(THIRD).getColor());


    }

    /**try to remove marbles that aren't stored
     */
    @Test
    public void removeNotPresent(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.PURPLE,2);
        toRemove.add(Marble.Color.BLUE,2);

        //test
        boolean result = shelves.withdraw(toRemove);
        assertFalse(result);
        //check that the first shelf isn't changed
        assertEquals(1, shelves.getShelf(FIRST).getSize());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(FIRST).getColor());
        //check that the second shelf isn't changed
        assertEquals(2, shelves.getShelf(SECOND).getSize());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());


    }


    @Test
    public void moveCorrect(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,1);


        //test
        boolean result = shelves.move(FIRST, SECOND);
        assertTrue(result);


        assertEquals(Marble.Color.BLUE, shelves.getShelf(SECOND).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(FIRST).getColor());

    }


    @Test
    public void moveWrong(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);

        //test
        boolean result = shelves.move(FIRST, SECOND);
        assertFalse(result);

        assertEquals(Marble.Color.BLUE, shelves.getShelf(FIRST).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());
    }



}
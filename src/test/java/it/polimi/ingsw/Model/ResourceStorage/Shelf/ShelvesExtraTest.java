package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.TestData.TestBroadcaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ShelvesExtraTest {

    private static int FIRST = 1;
    private static int SECOND = 2;
    private static int THIRD = 3;
    private static int EXTRA = 4;



    private ShelvesBase base;
    private ShelvesExtra shelves;
    private static final Marble.Color color = Marble.Color.BLUE;
    private TestBroadcaster broadcaster;





    @BeforeEach
    public void init(){
        broadcaster = new TestBroadcaster();
        base = new ShelvesBase(broadcaster);
        shelves = new ShelvesExtra(base, broadcaster, color);
    }

    @Test
    public void initialization(){
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(i+1, shelves.getShelf(i+1).getMaxSize());
            assertEquals(0, shelves.getShelf(i+1).getSize());
            assertNull(shelves.getShelf(i+1).getColor());
        }

        assertEquals(2, shelves.getShelf(i+1).getMaxSize());
        assertEquals(0, shelves.getShelf(i+1).getSize());
        assertEquals(color, shelves.getShelf(i+1).getColor());
    }


    @ParameterizedTest
    @ValueSource(ints={0,1,2,3})
    public void add(int index){
        for (int i = 0; i < shelves.getShelf(index+1).getMaxSize(); i++) {
            boolean result = shelves.store(Marble.Color.BLUE, index+1);
            assertTrue(result);
            assertEquals(i+1,  shelves.getShelf(index+1).getSize());
            assertEquals(Marble.Color.BLUE, shelves.getShelf(index+1).getColor());
        }
    }

    /**try to add a marble to a single shelf when it's full
     */
    @Test
    public void addToFullLeader(){
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,2);
        assertTrue(shelves.getShelf(EXTRA).isFull());
        //test
        boolean result = shelves.store(Marble.Color.BLUE, EXTRA);
        assertFalse(result);
        assertEquals(2,  shelves.getShelf(EXTRA).getSize());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(EXTRA).getColor());
    }


    /**try to add a marble to a single shelf when it's full
     */
    @Test
    public void addToFullBase(){
        shelves.getShelf(SECOND).add(Marble.Color.BLUE,2);
        assertTrue(shelves.getShelf(SECOND).isFull());
        //test
        boolean result = shelves.store(Marble.Color.BLUE, SECOND);
        assertFalse(result);
        assertEquals(2,  shelves.getShelf(SECOND).getSize());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(SECOND).getColor());
    }


    /**try to add a marble of certain color to an empty base shelf when marbles of the same color are already
     * stored in another base shelf
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

    /**try to add a marble of certain color to the extra shelf when marbles of the same color are already stored in another shelf
     */
    @Test
    public void addPresentColorToExtra(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);

        boolean result = shelves.store(Marble.Color.BLUE, EXTRA);
        assertTrue(result);
        assertEquals(1,  shelves.getShelf(EXTRA).getSize());
        assertEquals(color, shelves.getShelf(EXTRA).getColor());
    }

    /**try to add a marble of certain color to the base shelf when marbles of the same color are already stored
     * in the extra shelf
     */
    @Test
    public void addPresentColorToBase(){
        //setup
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,1);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);

        boolean result = shelves.store(Marble.Color.BLUE, SECOND);
        assertTrue(result);
        assertEquals(1,  shelves.getShelf(SECOND).getSize());
        assertEquals(color, shelves.getShelf(SECOND).getColor());
    }




    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,2);
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.BLUE,1);
        toRemove.add(Marble.Color.GREY,2);
        toRemove.add(Marble.Color.PURPLE,3);
        toRemove.add(Marble.Color.BLUE,2);

        //test
        boolean result = shelves.withdraw(toRemove);
        assertTrue(result);
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(0, shelves.getShelf(i+1).getSize());
            assertNull(shelves.getShelf(i+1).getColor());
        }
        assertEquals(0, shelves.getShelf(i+1).getSize());
        assertEquals(color, shelves.getShelf(i+1).getColor());



    }

    /**try to remove all marbles from base
     */
    @Test
    public void removeAllBase(){
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
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(0, shelves.getShelf(i+1).getSize());
            assertNull(shelves.getShelf(i+1).getColor());
        }
        assertEquals(0, shelves.getShelf(i+1).getSize());
        assertEquals(color, shelves.getShelf(i+1).getColor());



    }

    /**try to remove all marbles from extra
     */
    @Test
    public void removeAllExtra(){
        //setup
        shelves.getShelf(SECOND).add(Marble.Color.BLUE,1);
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.BLUE,1);


        //test
        boolean result = shelves.withdraw(toRemove);
        assertTrue(result);
        assertEquals(0, shelves.getShelf(EXTRA).getSize());
        assertEquals(color, shelves.getShelf(EXTRA).getColor());



    }


    /**try to remove some marbles from base shelf
     */
    @Test
    public void removeSomeBase(){
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


    /**try to remove some marbles from base shelf
     */
    @Test
    public void removeSomeExtra(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);
        shelves.getShelf(THIRD).add(Marble.Color.PURPLE,3);
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,2);

        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.BLUE,2);
        toRemove.add(Marble.Color.GREY,1);




        boolean remove = shelves.withdraw(toRemove);
        assertTrue(remove);

        assertEquals(0, shelves.getShelf(FIRST).getSize());
        assertNull(shelves.getShelf(FIRST).getColor());

        assertEquals(1, shelves.getShelf(SECOND).getSize());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());

        assertEquals(1, shelves.getShelf(EXTRA).getSize());
        assertEquals(color , shelves.getShelf(EXTRA).getColor());


    }






    /**try to remove marbles that aren't stored
     */
    @Test
    public void removeNotPresent(){
        //setup
        ResourceList toRemove = new ResourceList();
        toRemove.add(Marble.Color.PURPLE,2);
        toRemove.add(Marble.Color.BLUE,2);

        //test
        boolean result = shelves.withdraw(toRemove);
        assertFalse(result);

    }
    

    /** try to move resources between basics shelf
     */
    @Test
    public void moveCorrectBase(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,1);


        //test
        boolean result = shelves.move(FIRST, SECOND);
        assertTrue(result);

        assertEquals(Marble.Color.BLUE, shelves.getShelf(SECOND).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(FIRST).getColor());
    }


    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongBase(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);


        //test
        boolean result = shelves.move(FIRST, SECOND);
        assertFalse(result);

        assertEquals(Marble.Color.BLUE, shelves.getShelf(FIRST).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());
    }



    /** try to move resources between extra shelf to Basic
     */
    @Test
    public void moveCorrectFromExtra(){
        //setup
        shelves.getShelf(FIRST).add(Marble.Color.BLUE,0);
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,2);


        //test
        boolean result = shelves.move(EXTRA, FIRST);
        assertTrue(result);

        assertEquals(1, shelves.getShelf(FIRST).getSize());
        assertEquals(1, shelves.getShelf(EXTRA).getSize());

        assertEquals(Marble.Color.BLUE, shelves.getShelf(FIRST).getColor());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(EXTRA).getColor());
    }


    /** try to move resources between extra shelf to Basic
     */
    @Test
    public void moveCorrectToExtra(){
        //setup
        shelves.getShelf(THIRD).add(Marble.Color.BLUE,3);



        //test
        boolean result = shelves.move(THIRD, EXTRA);
        assertTrue(result);

        assertEquals(1, shelves.getShelf(THIRD).getSize());
        assertEquals(2, shelves.getShelf(EXTRA).getSize());

        assertEquals(Marble.Color.BLUE, shelves.getShelf(THIRD).getColor());
        assertEquals(Marble.Color.BLUE, shelves.getShelf(EXTRA).getColor());
    }




    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongFromExtra(){
        //setup
        shelves.getShelf(3).add(Marble.Color.BLUE,2);
        shelves.getShelf(1).add(Marble.Color.GREY,1);

        //test
        boolean result = shelves.move(3, 1);
        assertFalse(result);

        assertEquals(1, shelves.getShelf(1).getSize());
        assertEquals(2, shelves.getShelf(3).getSize());

        assertEquals(Marble.Color.BLUE, shelves.getShelf(3).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(1).getColor());
    }



    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongToExtra(){
        //setup
        shelves.getShelf(EXTRA).add(Marble.Color.BLUE,1);
        shelves.getShelf(SECOND).add(Marble.Color.GREY,2);

        //test
        boolean result = shelves.move(SECOND, EXTRA);
        assertFalse(result);

        assertEquals(2, shelves.getShelf(SECOND).getSize());
        assertEquals(1, shelves.getShelf(EXTRA).getSize());

        assertEquals(Marble.Color.BLUE, shelves.getShelf(EXTRA).getColor());
        assertEquals(Marble.Color.GREY, shelves.getShelf(SECOND).getColor());
    }






}
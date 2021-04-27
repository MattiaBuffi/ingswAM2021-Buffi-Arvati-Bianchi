package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ShelvesExtraTest {
    private ShelvesBase base;
    private ShelvesExtra shelves;
    private static final MarbleColor color = MarbleColor.BLUE;

    private Shelf getShelfFromIndex(int index){
        return shelves.getShelves().get(index);
    }



    @BeforeEach
    public void init(){
        base = new ShelvesBase();
        shelves = new ShelvesExtra(base, new ShelfLeader(color));
    }

    @Test
    public void initialization(){
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(i+1, getShelfFromIndex(i).getMaxSize());
            assertEquals(0, getShelfFromIndex(i).getSize());
            assertNull(getShelfFromIndex(i).getColor());
        }

        assertEquals(2, getShelfFromIndex(i).getMaxSize());
        assertEquals(0, getShelfFromIndex(i).getSize());
        assertEquals(color, getShelfFromIndex(i).getColor());
    }


    @ParameterizedTest
    @ValueSource(ints={0,1,2,3})
    public void add(int index){
        for (int i = 0; i < getShelfFromIndex(index).getMaxSize(); i++) {
            boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(index).getId());
            assertTrue(result);
            assertEquals(i+1,  getShelfFromIndex(index).getSize());
            assertEquals(MarbleColor.BLUE, getShelfFromIndex(index).getColor());
        }
    }

    /**try to add a marble to a single shelf when it's full
     */
    @Test
    public void addToFullLeader(){
        getShelfFromIndex(3).add(MarbleColor.BLUE,2);
        assertTrue(getShelfFromIndex(3).isFull());
        //test
        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(3).getId());
        assertFalse(result);
        assertEquals(2,  getShelfFromIndex(3).getSize());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(3).getColor());
    }

    /**try to add a marble to a single shelf when it's full
     */
    @Test
    public void addToFullBase(){
        getShelfFromIndex(1).add(MarbleColor.BLUE,2);
        assertTrue(getShelfFromIndex(1).isFull());
        //test
        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(1).getId());
        assertFalse(result);
        assertEquals(2,  getShelfFromIndex(1).getSize());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(1).getColor());
    }


    /**try to add a marble of certain color to an empty base shelf when marbles of the same color are already
     * stored in another base shelf
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

    /**try to add a marble of certain color to the extra shelf when marbles of the same color are already stored in another shelf
     */
    @Test
    public void addPresentColorToExtra(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);

        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(3).getId());
        assertTrue(result);
        assertEquals(1,  getShelfFromIndex(3).getSize());
        assertEquals(color, getShelfFromIndex(3).getColor());
    }

    /**try to add a marble of certain color to the base shelf when marbles of the same color are already stored
     * in the extra shelf
     */
    @Test
    public void addPresentColorToBase(){
        //setup
        getShelfFromIndex(3).add(MarbleColor.BLUE,1);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);

        boolean result = shelves.store(MarbleColor.BLUE, getShelfFromIndex(1).getId());
        assertTrue(result);
        assertEquals(1,  getShelfFromIndex(1).getSize());
        assertEquals(color, getShelfFromIndex(1).getColor());
    }




    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);
        getShelfFromIndex(3).add(MarbleColor.BLUE,2);
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.BLUE,1);
        toRemove.add(MarbleColor.GREY,2);
        toRemove.add(MarbleColor.PURPLE,3);
        toRemove.add(MarbleColor.BLUE,2);

        //test
        boolean result = shelves.remove(toRemove);
        assertTrue(result);
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(0, getShelfFromIndex(i).getSize());
            assertNull(getShelfFromIndex(i).getColor());
        }
        assertEquals(0, getShelfFromIndex(i).getSize());
        assertEquals(color, getShelfFromIndex(i).getColor());



    }

    /**try to remove all marbles from base
     */
    @Test
    public void removeAllBase(){
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
        int i;
        for (i = 0; i < shelves.getShelves().size()-1; i++) {
            assertEquals(0, getShelfFromIndex(i).getSize());
            assertNull(getShelfFromIndex(i).getColor());
        }
        assertEquals(0, getShelfFromIndex(i).getSize());
        assertEquals(color, getShelfFromIndex(i).getColor());



    }

    /**try to remove all marbles from extra
     */
    @Test
    public void removeAllExtra(){
        //setup
        getShelfFromIndex(1).add(MarbleColor.BLUE,1);
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.BLUE,1);


        //test
        boolean result = shelves.remove(toRemove);
        assertTrue(result);
        assertEquals(0, getShelfFromIndex(3).getSize());
        assertEquals(color, getShelfFromIndex(3).getColor());



    }


    /**try to remove some marbles from base shelf
     */
    @Test
    public void removeSomeBase(){
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


    /**try to remove some marbles from base shelf
     */
    @Test
    public void removeSomeExtra(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);
        getShelfFromIndex(2).add(MarbleColor.PURPLE,3);
        getShelfFromIndex(3).add(MarbleColor.BLUE,2);

        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.BLUE,2);
        toRemove.add(MarbleColor.GREY,1);




        boolean remove = shelves.remove(toRemove);
        assertTrue(remove);

        assertEquals(0, getShelfFromIndex(0).getSize());
        assertNull(getShelfFromIndex(0).getColor());

        assertEquals(1, getShelfFromIndex(1).getSize());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());

        assertEquals(1, getShelfFromIndex(3).getSize());
        assertEquals(color , getShelfFromIndex(3).getColor());


    }






    /**try to remove marbles that aren't stored
     */
    @Test
    public void removeNotPresent(){
        //setup
        ResourceList toRemove = new ResourceList();
        toRemove.add(MarbleColor.PURPLE,2);
        toRemove.add(MarbleColor.BLUE,2);

        //test
        boolean result = shelves.remove(toRemove);
        assertFalse(result);

    }
    

    /** try to move resources between basics shelf
     */
    @Test
    public void moveCorrectBase(){
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


    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongBase(){
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



    /** try to move resources between extra shelf to Basic
     */
    @Test
    public void moveCorrectFromExtra(){
        //setup
        getShelfFromIndex(0).add(MarbleColor.BLUE,0);
        getShelfFromIndex(3).add(MarbleColor.BLUE,2);


        //test
        boolean result = shelves.move(getShelfFromIndex(3).getId(), getShelfFromIndex(0).getId());
        assertTrue(result);

        assertEquals(1, getShelfFromIndex(0).getSize());
        assertEquals(1, getShelfFromIndex(3).getSize());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(0).getColor());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(3).getColor());
    }


    /** try to move resources between extra shelf to Basic
     */
    @Test
    public void moveCorrectToExtra(){
        //setup
        getShelfFromIndex(2).add(MarbleColor.BLUE,3);



        //test
        boolean result = shelves.move(getShelfFromIndex(2).getId(), getShelfFromIndex(3).getId());
        assertTrue(result);

        assertEquals(1, getShelfFromIndex(2).getSize());
        assertEquals(2, getShelfFromIndex(3).getSize());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(2).getColor());
        assertEquals(MarbleColor.BLUE, getShelfFromIndex(3).getColor());
    }




    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongFromExtra(){
        //setup
        getShelfFromIndex(3).add(MarbleColor.BLUE,2);
        getShelfFromIndex(1).add(MarbleColor.GREY,1);

        //test
        boolean result = shelves.move(getShelfFromIndex(3).getId(), getShelfFromIndex(1).getId());
        assertFalse(result);

        assertEquals(1, getShelfFromIndex(1).getSize());
        assertEquals(2, getShelfFromIndex(3).getSize());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(3).getColor());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());
    }



    /** try to move resources between basics shelf
     */
    @Test
    public void moveWrongToExtra(){
        //setup
        getShelfFromIndex(3).add(MarbleColor.BLUE,1);
        getShelfFromIndex(1).add(MarbleColor.GREY,2);

        //test
        boolean result = shelves.move(getShelfFromIndex(1).getId(), getShelfFromIndex(3).getId());
        assertFalse(result);

        assertEquals(2, getShelfFromIndex(1).getSize());
        assertEquals(1, getShelfFromIndex(3).getSize());

        assertEquals(MarbleColor.BLUE, getShelfFromIndex(3).getColor());
        assertEquals(MarbleColor.GREY, getShelfFromIndex(1).getColor());
    }






}
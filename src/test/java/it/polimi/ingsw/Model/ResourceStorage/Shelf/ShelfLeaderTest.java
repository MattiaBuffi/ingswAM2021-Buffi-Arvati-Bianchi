package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShelfLeaderTest {


    /**check the initialization of the shelf
     */
    @Test
    public void initialization(){
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        assertEquals(0, basic.getSize());
        assertNotNull(basic.getId());
        assertEquals( MarbleColor.RED, basic.getColor());
        assertEquals(2, basic.getMaxSize());
    }

    /**try to add some marble to an empty shelf
     */
    @Test
    public void addToEmpty() {
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);

        //test
        boolean result = basic.add(MarbleColor.RED,2);
        assertTrue(result);
        assertEquals(2, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }

    /**try to add a marble to a shelf not empty
     */
    @Test
    public void addNormal(){
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,1);

        //test
        boolean result = basic.add(MarbleColor.RED,1);
        assertTrue(result);
        assertEquals(2, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }

    /**try to add even if the shelf is full
     */
    @Test
    public void addToFull() {
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.add(MarbleColor.RED,2);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }

    /**try to add a marble of a different color
     */
    @Test
    public void addWrongColor(){
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,1);

        //test
        boolean result = basic.add(MarbleColor.WHITE, 1);
        assertFalse(result);
        assertEquals(1, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }




    @Test
    public void removeNormal() {
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.remove(1);
        assertTrue(result);
        assertEquals(1, basic.getSize());
        assertEquals(MarbleColor.RED, basic.getColor());
    }


    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.remove(2);
        assertTrue(result);
        assertEquals(0, basic.getSize());
        assertEquals(MarbleColor.RED, basic.getColor());
    }

    /**try to remove more marbles that are stored
     */
    @Test
    public void removeOverMax(){
        //setup
        ShelfLeader basic = new ShelfLeader(MarbleColor.RED);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.remove(5);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals(MarbleColor.RED, basic.getColor());
    }



}
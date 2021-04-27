package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShelfBasicTest {

    /**check the initialization of the shelf
     */
    @Test
    public void initialization(){
        ShelfBasic basic = new ShelfBasic(3);
        assertEquals(0, basic.getSize());
        assertNotNull(basic.getId());
        assertNull(basic.getColor());
        assertEquals(3, basic.getMaxSize());
    }

    /**try to add some marble to an empty shelf
     */
    @Test
    public void addToEmpty() {
        //setup
        ShelfBasic basic = new ShelfBasic(3);

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
        ShelfBasic basic = new ShelfBasic(3);
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
        ShelfBasic basic = new ShelfBasic(3);
        basic.add(MarbleColor.RED,3);

        //test
        boolean result = basic.add(MarbleColor.RED,2);
        assertFalse(result);
        assertEquals(3, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }

    /**try to add a marble of a different color
     */
    @Test
    public void addWrongColor(){
        //setup
        ShelfBasic basic = new ShelfBasic(3);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.add(MarbleColor.WHITE, 1);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals( MarbleColor.RED, basic.getColor());
    }




    @Test
    public void removeNormal() {
        //setup
        ShelfBasic basic = new ShelfBasic(3);
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
        ShelfBasic basic = new ShelfBasic(3);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.remove(2);
        assertTrue(result);
        assertEquals(0, basic.getSize());
        assertNull(basic.getColor());
    }

    /**try to remove more marbles that are stored
     */
    @Test
    public void removeOverMax(){
        //setup
        ShelfBasic basic = new ShelfBasic(3);
        basic.add(MarbleColor.RED,2);

        //test
        boolean result = basic.remove(5);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals(MarbleColor.RED, basic.getColor());
    }




}
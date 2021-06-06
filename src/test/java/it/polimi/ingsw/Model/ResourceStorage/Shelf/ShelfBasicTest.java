package it.polimi.ingsw.Model.ResourceStorage.Shelf;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.TestData.TestBroadcaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShelfBasicTest {


    private static TestBroadcaster broadcaster;


    @BeforeEach
    public void init(){
        broadcaster = new TestBroadcaster();
    }

    /**check the initialization of the shelf
     */
    @Test
    public void initialization(){
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        assertEquals(0, basic.getSize());
        assertNull(basic.getColor());
        assertEquals(3, basic.getMaxSize());
    }

    /**try to add some marble to an empty shelf
     */
    @Test
    public void addToEmpty() {
        //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);

        //test
        boolean result = basic.add(Marble.Color.RED,2);
        assertTrue(result);
        assertEquals(2, basic.getSize());
        assertEquals( Marble.Color.RED, basic.getColor());
    }

    /**try to add a marble to a shelf not empty
     */
    @Test
    public void addNormal(){
    //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,1);

        //test
        boolean result = basic.add(Marble.Color.RED,1);
        assertTrue(result);
        assertEquals(2, basic.getSize());
        assertEquals( Marble.Color.RED, basic.getColor());
    }

    /**try to add even if the shelf is full
     */
    @Test
    public void addToFull() {
        //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,3);

        //test
        boolean result = basic.add(Marble.Color.RED,2);
        assertFalse(result);
        assertEquals(3, basic.getSize());
        assertEquals( Marble.Color.RED, basic.getColor());
    }

    /**try to add a marble of a different color
     */
    @Test
    public void addWrongColor(){
        //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,2);

        //test
        boolean result = basic.add(Marble.Color.WHITE, 1);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals( Marble.Color.RED, basic.getColor());
    }




    @Test
    public void removeNormal() {
        //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,2);

        //test
        boolean result = basic.remove(1);
        assertTrue(result);
        assertEquals(1, basic.getSize());
        assertEquals(Marble.Color.RED, basic.getColor());
    }


    /**try to remove all marbles
     */
    @Test
    public void removeAll(){
        //setup
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,2);

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
        ShelfBasic basic = new ShelfBasic(3,1, broadcaster);
        basic.add(Marble.Color.RED,2);

        //test
        boolean result = basic.remove(5);
        assertFalse(result);
        assertEquals(2, basic.getSize());
        assertEquals(Marble.Color.RED, basic.getColor());
    }




}
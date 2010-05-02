/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import iett.Time;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emre
 */
public class TimeListTest {

    private TimeList list;
    public TimeListTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void emptyConstructor(){
        list = new TimeList();
        assertEquals(0, list.size());
    }

    @Test
    public void sizeConstructor(){
        list = new TimeList(3);
        Time t1=new Time("9:15");
        Time t2=new Time("10:20");
        Time t3 = new Time("15:35");
        list.add(t1);
        list.add(t2);
        list.add(t3);
        assertEquals(3, list.size());
        Time t4 = new Time("19:30");

        boolean threwException = false;
        try {
            list.add(t4);
        }
        catch (IndexOutOfBoundsException e){
            threwException = true;
        }

        assertTrue(threwException);
        
    }

    @Test
    public void arrayConstructor(){
        Time t1=new Time("9:15");
        Time t2=new Time("10:20");
        Time[] array = new Time[5];
        array[0] = t1;
        array[1] = t2;

        list = new TimeList(array);

        assertEquals(2, list.size());
        assertSame(t1, list.get(0));
        assertSame(t2, list.get(1));

        Time t3 = new Time("15:35");
        list.add(t3);
        assertEquals(3, list.size());
        assertSame(t3, list.get(2));

        Time t4 = new Time("19:30");
        list.add(t4);
        Time t5 = new Time("19:30");
        list.add(t5);
        Time t6 = new Time("19:30");
        
        
        boolean threwException = false;
        try {
            list.add(t6);
        }
        catch (IndexOutOfBoundsException e){
            threwException = true;
        }

        assertTrue(threwException);

    }

    @Test
    public void add(){
        list = new TimeList();
        Time t1=new Time("9:15");
        Time t2=new Time("10:20");
        list.add(t1);
        list.add(t2);
        assertEquals(2, list.size());
        assertEquals(list.get(0), t1);
        assertEquals(list.get(1), t2);
    }

    @Test
    public void remove(){
        list = new TimeList();
        Time t1=new Time("9:15");
        Time t2=new Time("10:20");
        list.add(t1);
        list.add(t2);
        
        Time element = list.remove(0);
        assertEquals(9, element.getHour());
        assertEquals(15, element.getMinute());
        assertEquals(1, list.size());
        
        Time element2 = list.remove(1);
        assertEquals(10, element2.getHour());
        assertEquals(20, element2.getMinute());
        assertEquals(0, list.size());
        
        list.add(element2);
        list.add(element);
        assertEquals(2, list.size());

        element = list.get(0);
        assertEquals(10, element.getHour());
        assertEquals(20, element.getMinute());
        assertEquals(2, list.size());
        element2 = list.get(1);
        assertEquals(9, element2.getHour());
        assertEquals(15, element2.getMinute());
        assertEquals(2, list.size());




    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
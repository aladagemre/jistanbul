/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

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
public class TimeTest {

    public TimeTest() {
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
    public void testConstructor(){
        Time t = new Time("19:05");
        assertEquals(19, t.getHour());
        assertEquals(5, t.getMinute());

        t = new Time("00:00");
        assertEquals(0, t.getHour());
        assertEquals(0, t.getMinute());
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
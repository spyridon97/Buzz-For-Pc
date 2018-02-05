/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manos
 */
public class StatMultiTest {
    
    public StatMultiTest() {
    }
    
    /**
     * Test of getName method, of class StatMulti.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        StatMulti instance = new StatMulti("test", 0);
        String expResult = "test";
        
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWins method, of class StatMulti.
     */
    @Test
    public void testGetWins() {
        System.out.println("getWins");
       
        int random = (int) (Math.random() * 100);
        
        StatMulti instance = new StatMulti("test", random);
        int expResult = random;
        
        int result = instance.getWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class StatMulti.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        
        int random1 = (int) (Math.random() * 100);
        int random2 = (int) (Math.random() * 100);
        
        StatMulti o = new StatMulti("test", random1);
        StatMulti instance = new StatMulti("test", random2);
        
        int expResult = Integer.compare(random1, random2);
        int result = instance.compareTo(o);
        
        assertEquals(expResult, result);
    }
    
}

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
public class StatSingleTest {
    
    public StatSingleTest() {
    }
    
    /**
     * Test of getName method, of class StatSingle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        StatSingle instance = new StatSingle("test", 0, 0);
        String expResult = "test";
        
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPoints method, of class StatSingle.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        
        int random = (int) (Math.random() * 100);
        
        StatSingle instance = new StatSingle("test", 0, random);
        int expResult = random;
        
        int result = instance.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGameMode method, of class StatSingle.
     */
    @Test
    public void testGetGameMode() {
        System.out.println("getGameMode");
            
        int random = (int) (Math.random() * 100);
        
        StatSingle instance = new StatSingle("test", random, 0);
        int expResult = random;
        
        int result = instance.getGameMode();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class StatSingle.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
            
        int random1 = (int) (Math.random() * 100);
        int random2 = (int) (Math.random() * 100);
        
        StatSingle o = new StatSingle("test", 0, random1);
        StatSingle instance = new StatSingle("test", 0, random2);
        
        int expResult = Integer.compare(random1, random2);
        int result = instance.compareTo(o);
        
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internationalization;

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
public class LanguageTest {
    
    public LanguageTest() {
    }
    
    /**
     * Test of setToGreek method, of class Language.
     */
    @Test
    public void testSetToGreek() {
        System.out.println("setToGreek");
        
        Language instance = new Language();
        instance.setToGreek();
        
        if (!instance.getMessage("start").equals("Νέο Παιχνίδι")) {
            fail("Language to greek isnt working");
        }
    }

    /**
     * Test of setToEnglish method, of class Language.
     */
    @Test
    public void testSetToEnglish() {
        System.out.println("setToEnglish");
        Language instance = new Language();
        instance.setToEnglish();
        
        if (!instance.getMessage("start").equals("New Game")) {
            fail("Language to greek isnt working");
        }
    }

    /**
     * Test of getMessage method, of class Language.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        String message = "exit";
        Language instance = new Language();
        
        String expResult = "Έξοδος";
        String result = instance.getMessage(message);
        
        assertEquals(expResult, result);
    }
    
}

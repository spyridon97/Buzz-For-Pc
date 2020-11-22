package internationalization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
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

        String expResult = "Exit";
        String result = instance.getMessage(message);

        assertEquals(expResult, result);
    }

}

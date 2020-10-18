package game;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Manolis, Spiros
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Player instance = new Player();
        instance.setName("Μανώλης");

        String expResult = "Μανώλης";
        String result = instance.getName();

        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = new Player();

        String expResult = null;
        String result = instance.getName();

        assertEquals(expResult, result);
    }

    /**
     * Test of addPoints method, of class Player.
     */
    @Test
    public void testAddPoints() {
        System.out.println("addPoints");

        Player instance = new Player();
        Random r = new Random();
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int randomInt = r.nextInt(1000);

            instance.addPoints(i);
            sum += i;
        }

        assertEquals(sum, instance.getPoints());
    }

    /**
     * Test of reducePoints method, of class Player.
     */
    @Test
    public void testReducePoints() {
        System.out.println("reducePoints");

        Player instance = new Player();
        Random r = new Random();

        int sum = 10000;
        instance.addPoints(10000);

        for (int i = 0; i < 10; i++) {
            int randomInt = r.nextInt(1000);

            instance.reducePoints(i);
            sum -= i;
        }

        assertEquals(sum, instance.getPoints());
    }

    /**
     * Test of getPoints method, of class Player.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        Player instance = new Player();

        instance.addPoints(1000);
        instance.reducePoints(500);

        int expResult = 500;
        int result = instance.getPoints();

        assertEquals(expResult, result);
    }

    /**
     * Test of getPoints method, of class Player.
     */
    @Test
    public void testSetKeyBindings() {
        System.out.println("setKeyBindings");
        Player instance = new Player();

        instance.setKeyBindings(new int[]{KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D});

        int[] expResult = new int[]{KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D};
        int[] result = instance.getKeyBindings();

        for (int i = 0; i < expResult.length; i++) {
            if (expResult[i] != result[i]) {
                fail("KeyBindings not same!");
            }
        }
    }

    /**
     * Test of getPoints method, of class Player.
     */
    @Test
    public void testGetKeyBindings() {
        System.out.println("getKeyBindings");
        Player instance = new Player();

        int[] result = instance.getKeyBindings();

        if (result != null) {
            fail("Incorrect return from Player.getKeyBindings()");
        }
    }

}

package userInterface;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.KeyStroke;

/**
 * @author Manolis, Spiros
 */
public abstract class TransferFocus {

    //Obtained from the net, as a ready Tab-focus change solution

    /**
     * @param c the component to add Tab focus change support
     */
    public static void patch(Component c) {
        Set<KeyStroke> strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
        c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes);

        strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
        c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes);
    }
}

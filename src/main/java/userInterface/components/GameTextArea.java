package userInterface.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

/**
 *
 * @author Manolis,Spiros
 */
public class GameTextArea extends JTextArea implements KeyListener {

    private static final long serialVersionUID = 3351766146178630961L;

    private static Font font = new Font("Arial", Font.PLAIN, 17);

    /**
     * minimal constructor
     */
    public GameTextArea() {
        super();

        this.setFont(font);
    }

    /**
     *
     * @param width the desired width of the component
     */
    public GameTextArea(int width) {
        this(width, 25);

        this.setFont(font);
    }

    /**
     *
     * @param width the desired width of the component
     * @param height the desired height of the component
     */
    public GameTextArea(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.addKeyListener(this);
        this.setFont(font);
    }

    @Override
    public void keyPressed(KeyEvent kevt) {
        int kcode = kevt.getKeyCode();

        if (kcode == KeyEvent.VK_ENTER || kcode == KeyEvent.VK_TAB) {
            kevt.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent kevt) {

    }

    @Override
    public void keyTyped(KeyEvent kevt) {

    }

}

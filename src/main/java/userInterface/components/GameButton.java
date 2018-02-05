package userInterface.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Manolis,Spiros
 */
public class GameButton extends JButton {

    private static final long serialVersionUID = -8616441039316706745L;

    private static Font font = new Font("Arial", Font.PLAIN, 17);

    /**
     * Initialize the button with just an icon in it
     *
     * @param ii The icon for this button
     */
    public GameButton(ImageIcon ii) {
        super(ii);
    }

    /**
     * minimal text display constructor
     *
     * @param text the text displayed in the button
     */
    public GameButton(String text) {
        super(text);

        this.setFont(font);
    }

    /**
     *
     * @param text the text displayed in the button
     * @param width the desired width of the component
     */
    public GameButton(String text, int width) {
        super(text);

        this.setPreferredSize(new Dimension(width, getPreferredSize().height));
        this.setFont(font);
    }

    /**
     *
     * @param text the text displayed in the label
     * @param width the desired width of the component
     * @param height the desired height of the component
     */
    public GameButton(String text, int width, int height) {
        super(text);

        this.setPreferredSize(new Dimension(width, height));
        this.setFont(font);
    }

}

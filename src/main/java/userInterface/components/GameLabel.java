package userInterface.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Manolis,Spiros
 */
public class GameLabel extends JLabel {

    private static final long serialVersionUID = 7573153993849279659L;

    private static Font font = new Font("Arial", Font.PLAIN, 17);

    /**
     * Initialize the label with just an icon in it
     *
     * @param ii The icon for this label
     */
    public GameLabel(ImageIcon ii) {
        super(ii);

        this.setFont(font);
    }

    /**
     * minimal text display constructor
     *
     * @param text the text displayed in the label
     */
    public GameLabel(String text) {
        super(text);

        this.setFont(font);
    }

    /**
     *
     * @param text the text displayed in the label
     * @param width the desired width of the component
     */
    public GameLabel(String text, int width) {
        this(text, width, false);
    }

    /**
     *
     * @param text the text displayed in the button
     * @param width the desired width of the component
     * @param height the desired height of the component
     */
    public GameLabel(String text, int width, int height) {
        super(text);

        this.setPreferredSize(new Dimension(width, height));
        this.setFont(font);
    }
    
    /**
     *
     * @param text the text displayed in the button
     * @param width the desired width of the component
     * @param isCentered true for center alignment, false for left alignment
     */
    public GameLabel(String text, int width, boolean isCentered) {
        super(text, isCentered ? SwingConstants.CENTER : SwingConstants.LEFT);

        this.setPreferredSize(new Dimension(width, getPreferredSize().height));
        this.setFont(font);
    }

}

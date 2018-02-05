package userInterface.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JRadioButton;

/**
 *
 * @author user
 */
public class GameRadioButton extends JRadioButton {

    private static final long serialVersionUID = 2883828781288395638L;

    private static Font font = new Font("Arial", Font.PLAIN, 17);

    /**
     *
     * @param text the text to display next to this radio button
     */
    public GameRadioButton(String text) {
        super(text);

        this.setFont(font);
    }
    
    /**
     * 
     * @param text the text to display next to this radio button
     * @param width the width from the next radio button
     */
    public GameRadioButton(String text, int width) {
        super(text);

        this.setPreferredSize(new Dimension(width, getPreferredSize().height));
        this.setFont(font);
    }

}

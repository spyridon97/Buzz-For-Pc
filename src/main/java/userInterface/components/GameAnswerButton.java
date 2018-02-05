package userInterface.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 *
 * @author Manolis,Spiros
 */
public class GameAnswerButton extends GameButton {

    private static final long serialVersionUID = 5517715632836328855L;

    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Character> keyBindings = new ArrayList<>();
    private Color overlay;

    /**
     * default constructor
     */
    public GameAnswerButton() {
        super("");
    }

    /**
     *
     * @param playerName the name of the player that answered this button
     */
    public void playerAnswer(String playerName) {
        if (!players.contains(playerName)) {
            players.add(playerName);
        }
    }

    /**
     * Clear the players shown as the players who answered this button
     */
    public void clearPlayerAnswers() {
        players.clear();
    }

    /**
     *
     * @param key The char that corresponds to the key binding of the key the
     * player pressed for this button
     */
    public void addKeyBinding(char key) {
        if (!keyBindings.contains(key)) {
            keyBindings.add(key);
        }
    }

    /**
     * clears the key bindings displayed on top of the button as selected
     * answers
     */
    public void clearKeyBindings() {
        keyBindings.clear();
    }

    /**
     *
     * @param newOverlay the new Color to set as overlay. Pass null for no
     * overlay
     */
    public void setColorOverlay(Color newOverlay) {
        this.overlay = newOverlay;
        this.repaint();
    }
    
    @Override
    /**
     * This method paints all the additional data on top of the GameButton that
     * are needed Those are the Key Bindings each player can use (rendered left
     * and right), and which player has answered the answer that is shown in
     * this button.
     *
     * This method is also responsible for drawing the custom color overlay, if
     * overlay is not equal to null
     *
     * @param g Graphics Object is passed by Swing to us for the rendering
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(getFont().deriveFont(Font.PLAIN, 18.0f));

        g2.setColor(new Color(60, 60, 60));
        if (players.size() == 1) {
            g2.drawString(players.get(0), 10, 30);
        } else if (players.size() == 2) {
            g2.drawString(players.get(0) + ", " + players.get(1), 10, 30);
        }

        g2.setColor(new Color(120, 120, 120));
        for (int i = 0; i < keyBindings.size(); i++) {
            g2.drawString(keyBindings.get(i) + "", 10 + i * 245, getSize().height - 10);
        }

        if (overlay != null) {
            g2.setColor(overlay);
            g2.fillRect(0, 0, getSize().width, getSize().height);
        }

        g2.dispose();
    }

}

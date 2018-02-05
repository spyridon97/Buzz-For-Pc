package game;

/**
 * @author Manolis, Spiros
 */
public class Player {

    private String name;
    private int points;
    private int[] keyBindings;

    public Player() {
        this.points = 0;
    }

    /**
     * @param name The name of the player given from the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @param points The points to be added
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * @param points The points to be subtracted
     */
    public void reducePoints(int points) {
        this.points -= points;
    }

    /**
     * @return The points of the player at the given moment
     */
    public int getPoints() {
        return points;
    }

    /**
     * set the key bindings of the player
     *
     * @param keyBindings the chosen keyBinding
     */
    public void setKeyBindings(int[] keyBindings) {
        this.keyBindings = keyBindings;
    }

    /**
     * @return the keyBinding of the player
     */
    public int[] getKeyBindings() {
        return keyBindings;
    }

}

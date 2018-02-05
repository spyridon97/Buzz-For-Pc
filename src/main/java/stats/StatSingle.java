/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 * @author Manolis, Spiros
 */
public class StatSingle implements Comparable<StatSingle> {

    private final String name;
    private final int gameMode;
    private final int points;

    /**
     * @param name     the name of the player that achieved this score
     * @param gameMode the gameMode in which the player achieved the score
     *                 (gameMode ID)
     * @param points   the points the player gathered in the specific Round
     */
    public StatSingle(String name, int gameMode, int points) {
        this.name = name;
        this.points = points;
        this.gameMode = gameMode;
    }

    /**
     * @return the statistic 's player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the statistic 's stored points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * @return the statistic 's stored GameMode ID
     */
    public int getGameMode() {
        return this.gameMode;
    }

    @Override
    /**
     * used for sorting the the StatSingle ArrayList, sort descending by points
     */
    public int compareTo(StatSingle o) {
        return Integer.compare(o.getPoints(), getPoints());
    }
}

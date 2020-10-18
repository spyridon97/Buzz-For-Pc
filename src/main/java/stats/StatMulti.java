/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 * @author Manolis, Spiros
 */
public class StatMulti implements Comparable<StatMulti> {

    private final String name;
    private final int wins;

    /**
     * @param name the name of the player that holds those wins
     * @param wins the amount of wins this specific player has
     */
    public StatMulti(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }

    /**
     * @return the name of the stat's player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the total wins of the stat's player
     */
    public int getWins() {
        return this.wins;
    }

    @Override
    public int compareTo(StatMulti o) {
        return Integer.compare(o.getWins(), getWins());
    }
}

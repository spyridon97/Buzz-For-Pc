/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import game.Player;
import internationalization.Language;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Manolis, Spiros
 */
public class Stats {

    private final ArrayList<StatSingle> statsSingle;
    private final ArrayList<StatMulti> statsMulti;
    private final Language language;

    /**
     * @param language the chosen language
     */
    public Stats(Language language) {
        statsSingle = new ArrayList<StatSingle>();
        statsMulti = new ArrayList<StatMulti>();

        this.language = language;

        readStatsSingle();
        readStatsMulti();
    }

    /**
     * @return the statsSingle ArrayList the contains all the StatSingle objects
     */
    public ArrayList<StatSingle> getStatsSingle() {
        return statsSingle;
    }

    /**
     * @return the statsMulti ArrayList the contains all the StatMulti objects
     */
    public ArrayList<StatMulti> getStatsMulti() {
        return statsMulti;
    }

    /**
     * Required format for the statSingle file:
     * <p>
     * [name, gameMode as a number, points]
     * <p>
     * THE FILE MUST BE UTF-8
     */
    private void readStatsSingle() {
        statsSingle.clear();

        try {
            File statsSingleFile = new File(this.getClass().getResource("statsSingle.txt").getPath());

            if (!statsSingleFile.exists()) {
                JOptionPane.showMessageDialog(null, language.getMessage("statsFileErrorTextSingle"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            Scanner reader = new Scanner(statsSingleFile, "UTF-8");

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(",");

                String name = fields[0];
                int gameMode = Integer.parseInt(fields[1]);
                int points = Integer.parseInt(fields[2]);

                StatSingle s = new StatSingle(name, gameMode, points);
                statsSingle.add(s);
            }

            reader.close();
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }

    }

    /**
     * Required format for the statMulti file:
     * <p>
     * [name, wins]
     * <p>
     * THE FILE MUST BE UTF-8
     */
    private void readStatsMulti() {
        statsMulti.clear();

        try {
            File statsMultiFile = new File(this.getClass().getResource("statsMulti.txt").getPath());

            if (!statsMultiFile.exists()) {
                JOptionPane.showMessageDialog(null, language.getMessage("statsFileErrorTextMulti"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                return;
            }

            Scanner reader = new Scanner(statsMultiFile, "UTF-8");

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(",");

                String name = fields[0];
                int wins = Integer.parseInt(fields[1]);

                StatMulti s = new StatMulti(name, wins);
                statsMulti.add(s);
            }

            reader.close();
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }

    }

    /**
     * @param players  the players of the game
     * @param gameMode the game that players are playing
     */
    public void addStat(ArrayList<Player> players, int gameMode) {

        if (players.size() == 1) {
            addStatSingle(players.get(0), gameMode);
        } else {
            addStatMulti(players);
        }
    }

    /**
     * adds a statSingle to the StatsSingle ArrayList
     *
     * @param player   the player that player the Current game Mode
     * @param gameMode the gameMOde as a number
     */
    private void addStatSingle(Player player, int gameMode) {
        int index = -1;

        for (int i = 0; i < statsSingle.size(); i++) {
            if (statsSingle.get(i).getName().equals(player.getName()) && statsSingle.get(i).getGameMode() == gameMode && statsSingle.get(i).getPoints() == player.getPoints()) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            StatSingle s = new StatSingle(player.getName(), gameMode, player.getPoints());
            statsSingle.add(s);
        }

        Collections.sort(statsSingle);

        writeStatsSingle();
    }

    /**
     * adds a statMulti to the StatsMulti ArrayList
     *
     * @param players The arrayList of the 2 players
     */
    private void addStatMulti(ArrayList<Player> players) {
        if (players.get(0).getPoints() != players.get(1).getPoints()) {
            String winName = players.get(0).getName();

            if (players.get(1).getPoints() > players.get(0).getPoints()) {
                winName = players.get(1).getName();
            }

            int index = -1;

            for (int i = 0; i < statsMulti.size(); i++) {
                if (statsMulti.get(i).getName().equals(winName)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                StatMulti s = new StatMulti(winName, statsMulti.get(index).getWins() + 1);
                statsMulti.remove(statsMulti.get(index));
                statsMulti.add(s);
            } else {
                StatMulti s = new StatMulti(winName, 1);
                statsMulti.add(s);
            }

            Collections.sort(statsMulti);

            writeStatsMulti();
        }
    }

    /**
     * writes the stats that are saved in the statsSingle ArrayList
     */
    private void writeStatsSingle() {
        PrintWriter StatsSingleFile;
        try {
            StatsSingleFile = new PrintWriter(new File(this.getClass().getResource("statsSingle.txt").getPath()));
            for (StatSingle statSingle : statsSingle) {
                StatsSingleFile.println(statSingle.getName() + "," + statSingle.getGameMode() + "," + statSingle.getPoints());
            }
            StatsSingleFile.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, language.getMessage("statsFileErrorTextSingle"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * writes the stats that are saved in the statsMulti ArrayList
     */
    private void writeStatsMulti() {
        PrintWriter StatsMultiFile;
        try {
            StatsMultiFile = new PrintWriter(new File(this.getClass().getResource("statsMulti.txt").getPath()));
            for (StatMulti statMulti : statsMulti) {
                StatsMultiFile.println(statMulti.getName() + "," + statMulti.getWins());
            }
            StatsMultiFile.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, language.getMessage("statsFileErrorTextMulti"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}

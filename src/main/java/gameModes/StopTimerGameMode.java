package gameModes;

import game.Player;
import questions.Question;
import userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Manolis, Spiros
 */
public class StopTimerGameMode extends GameMode {

    private long startTime;
    private int answerCount = 0;
    private boolean questionShown = false;

    private Timer timer;
    private JProgressBar pointCounter;

    /**
     * @param main The MainFrame instance of the application
     */
    public StopTimerGameMode(MainFrame main) {
        super(main);
    }

    @Override
    /**
     * See GameMode.getAdditionalGUI documentation
     *
     * This specific implementation sets up a progress bar that show the points
     * the player receive each moment live, with the use of a Swing Timer. There
     * is also a 3 second preparation time
     */
    public JPanel getAdditionalGUI() {
        JPanel timerGamemode = new JPanel();
        timerGamemode.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 20));

        pointCounter = new JProgressBar(0, 5000);
        pointCounter.setPreferredSize(new Dimension(350, 35));
        pointCounter.setValue(5000);
        pointCounter.setStringPainted(true);
        pointCounter.setString(main.getLanguage().getMessage("questionComing") + " 3");

        timerGamemode.add(pointCounter);

        return timerGamemode;
    }

    @Override
    /**
     * we just set the startTime when the question is shown
     */
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        startTime = System.currentTimeMillis();
        questionShown = false;
        answerCount = 0;

        timer = new Timer(1000 / 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int points = Math.max((int) (8000 - (System.currentTimeMillis() - startTime)), 0);

                if (points > 5000) {
                    int sec = (points - 5000) / 1000 + 1;
                    pointCounter.setString(main.getLanguage().getMessage("questionComing") + " " + sec);
                    pointCounter.setValue(5000);
                } else {
                    if (points != 0) {
                        if (!questionShown) {
                            main.getGameModePanel().showNextQuestion();
                            questionShown = true;
                        }

                        pointCounter.setString((int) (points * 0.2 + 0.5) + " " + main.getLanguage().getMessage("points"));
                        pointCounter.setValue(points);
                    } else {
                        main.getGameModePanel().fadeForNextQuestion();
                        timer.stop();
                    }
                }
            }
        });

        timer.start();
    }

    @Override
    /**
     * A countdown from 5 sec starts and you get as point the remaining time of
     * the countdown*0.2
     */
    public void questionAnswered(Player player, int playerIndex, boolean correct) {
        long answerTime = System.currentTimeMillis();
        //add back the 3 seconds from the countdown
        long diff = answerTime - (startTime + 3000);

        if (diff <= 5000 && diff >= 0) {
            if (correct) {
                int points = (int) ((5000 - diff) * 0.2 + 0.5);
                player.addPoints(points);
            } else {
            }

            answerCount++;

            if (answerCount >= main.getRound().getPlayers().size()) {
                timer.stop();
            }
        }
    }

    @Override
    /**
     *
     * @return the id of StopTimerGameMode
     */
    public int getId() {
        return 1;
    }
}

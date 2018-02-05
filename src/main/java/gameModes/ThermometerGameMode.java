package gameModes;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import game.Player;
import questions.Question;
import userInterface.MainFrame;
import userInterface.components.GameLabel;

/**
 * @author Manolis, Spiros
 */
public class ThermometerGameMode extends GameMode {

    private int[] questionsAnsweredCorrectly;
    private boolean isFirst;

    private GameLabel winsTitleLabel, winsLabel;

    /**
     * @param main The MainFrame instance of the application
     */
    public ThermometerGameMode(MainFrame main) {
        super(main);

        isFirst = false;
        questionsAnsweredCorrectly = new int[2];
    }

    @Override
    /**
     * See GameMode.getAdditionalGUI documentation
     *
     * This specific implementation sets up labels to display the wins each
     * player has so they can track down their progress
     */
    public JPanel getAdditionalGUI() {
        JPanel thermometerGamemode = new JPanel();
        thermometerGamemode.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        winsTitleLabel = new GameLabel(main.getLanguage().getMessage("correctAnswers"), 450, true);
        thermometerGamemode.add(winsTitleLabel);

        winsLabel = new GameLabel(" ", 450, true);
        thermometerGamemode.add(winsLabel);

        ArrayList<Player> players = main.getRound().getPlayers();
        winsLabel.setText(players.get(0).getName() + ": " + questionsAnsweredCorrectly[0] + "  -  " + players.get(1).getName() + ": " + questionsAnsweredCorrectly[1]);

        return thermometerGamemode;
    }

    @Override
    /**
     * Nothing is needed before a question is shown when playing with
     * ThermometerGameMode mode
     */
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        main.getGameModePanel().showNextQuestion();
        isFirst = true;
    }

    @Override
    /**
     * Give 1000 points to player if the answer was correct else nothing,
     * notifying him with a message
     */
    public void questionAnswered(Player player, int playerIndex, boolean correct) {
        if (correct) {
            questionsAnsweredCorrectly[playerIndex]++;

            if (isFirst && questionsAnsweredCorrectly[playerIndex] == 5) {
                player.addPoints(5000);
                isFirst = false;
            }
        }

        ArrayList<Player> players = main.getRound().getPlayers();
        winsLabel.setText(players.get(0).getName() + ": " + questionsAnsweredCorrectly[0] + "  -  " + players.get(1).getName() + ": " + questionsAnsweredCorrectly[1]);
    }

    @Override
    /**
     * The ThermometerGameMode has ended only when either of the players has
     * achieved 5 wins in total
     */
    public boolean hasEnded() {
        boolean ended = questionsAnsweredCorrectly[0] == 5 || questionsAnsweredCorrectly[1] == 5;

        if (ended) {
            Arrays.fill(questionsAnsweredCorrectly, 0);
        }

        return ended;
    }

    @Override
    /**
     *
     * @return the id of ThermometerGameMode
     */
    public int getId() {
        return 4;
    }

}

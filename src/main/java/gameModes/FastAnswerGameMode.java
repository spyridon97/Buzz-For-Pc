package gameModes;

import java.util.ArrayList;

import javax.swing.JPanel;

import game.Player;
import questions.Question;
import userInterface.MainFrame;

/**
 * @author Manolis, Spiros
 */
public class FastAnswerGameMode extends GameMode {

    private boolean isFirst;

    /**
     * @param main The MainFrame instance of the application
     */
    public FastAnswerGameMode(MainFrame main) {
        super(main);
        isFirst = false;
    }

    @Override
    /**
     * See GameMode.getAdditionalGUI documentation
     *
     * This specific implementation does not need any Additional GUI
     */
    public JPanel getAdditionalGUI() {
        return null;
    }

    @Override
    /**
     * we just set isFirst = true in order to make it work every time
     * FastAnswerGameMode is called
     */
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        main.getGameModePanel().showNextQuestion();
        isFirst = true;
    }

    @Override
    /**
     * gives 1000 points to the first player that answered correctly and 500
     * points to the second player that answer correctly
     */
    public void questionAnswered(Player player, int playerIndex, boolean correct) {
        if (correct) {
            if (isFirst) {
                player.addPoints(1000);

                isFirst = false;
            } else {
                player.addPoints(500);
            }
        }
    }

    @Override
    /**
     *
     * @return the id of FastAnswerGameMode
     */
    public int getId() {
        return 3;
    }
}

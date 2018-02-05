package gameModes;

import java.util.ArrayList;

import javax.swing.JPanel;

import game.Player;
import questions.Question;
import userInterface.MainFrame;

/**
 * @author Manolis, Spiros
 */
public class CorrectAnswerGameMode extends GameMode {

    /**
     * @param main The MainFrame instance oif the application
     */
    public CorrectAnswerGameMode(MainFrame main) {
        super(main);
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
     * Nothing is needed before a question is shown when playing with
     * CorrectAnswer mode
     */
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        main.getGameModePanel().showNextQuestion();
    }

    @Override
    /**
     * Give 1000 points to player if the answer was correct else nothing,
     * notifying him with a message
     */
    public void questionAnswered(Player player, int playerIndex, boolean correct) {
        if (correct) {
            player.addPoints(1000);
        }
    }

    @Override
    /**
     *
     * @return the id of CorrectAnswerGameMode
     */
    public int getId() {
        return 0;
    }
}

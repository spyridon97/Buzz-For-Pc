package gameModes;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import game.Player;
import internationalization.Language;
import questions.Question;
import userInterface.MainFrame;

/**
 * @author Manolis, Spiros
 */
public abstract class GameMode {

    protected MainFrame main;

    private int questions = 6;

    private static String[] gameModeNames;

    /**
     * Return a copy of the actual array, so it's values can't be changed from
     * the returned instance
     *
     * @return the copied array with all the GameMode names
     */
    public static String[] getGameModeNames() {
        return Arrays.copyOf(gameModeNames, gameModeNames.length);
    }

    /**
     * used to update the GameMode names array based on the current Locale used
     *
     * @param language The current language settings used in the game
     */
    public static void updateGameModeNames(Language language) {
        gameModeNames = new String[]{
                language.getMessage("mode1"),
                language.getMessage("mode2"),
                language.getMessage("mode3"),
                language.getMessage("mode4"),
                language.getMessage("mode5")
        };
    }

    /**
     * @param main The MainFrame instance of the application
     */
    public GameMode(MainFrame main) {
        this.main = main;
    }

    /**
     * Important method for handling GameMode-GUI interaction This method
     * optionally return a component that will be displayed on top of the answer
     * buttons that contains the GameMode specific GUI required
     *
     * @return return null for no component to be added, a JComponent otherwise
     */
    public abstract JPanel getAdditionalGUI();

    /**
     * Gets called before a question is made so the necessary console read/write
     * operations are done
     *
     * @param question The question that will be shown next
     * @param players  The players of the game
     */
    public abstract void beforeQuestion(ArrayList<Player> players, Question question);

    /**
     * Gets called when an answer to the question is given by the player
     *
     * @param player      The player That answered the last question
     * @param playerIndex the index of the player in the ArrayList
     * @param correct     true if the answer was correct, false else
     */
    public abstract void questionAnswered(Player player, int playerIndex, boolean correct);

    /**
     * @return the id of every GameMode
     */
    public abstract int getId();

    /**
     * Gets called after one question is answered by all the current players
     */
    public void afterQuestion() {
        questions--;
    }

    /**
     * all GameModes except from Thermometer inherit this method , don't
     * overrides it, because they end normally after 6 questions. Only
     * ThermometerGameMode overrides this because it has undefined amount of
     * questions that is calculated on runtime
     *
     * @return true if this round has ended, false otherwise
     */
    public boolean hasEnded() {
        return questions <= 0;
    }
}

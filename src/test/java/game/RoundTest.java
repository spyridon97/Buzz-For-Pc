package game;

import gameModes.GameMode;
import internationalization.Language;
import org.junit.Before;
import org.junit.Test;
import questions.Question;
import questions.Questions;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * @author Manolis, Spiros
 */
public class RoundTest {

    private Language language;

    private GameMode mode;
    private Questions questions;
    private int questionPointer = 0;
    private ArrayList<Player> players;


    @Before
    public void setUp() {
        language = new Language();
        questions = new Questions(language);
        players = new ArrayList<>();
        Player player = new Player();
        player.setName("name");
        players.add(player);
    }

    public RoundTest() {
    }

    /**
     * Test of beforeQuestion method, of class Round.
     */
    @Test
    public void testBeforeQuestion() {
        System.out.println("beforeQuestion");

        TestGameMode testGameMode = new TestGameMode();
        Round instance = new Round(questions, testGameMode);
        instance.setPlayers(players);
        Question q = new Question();

        instance.beforeQuestion(q, players);

        if (!testGameMode.getBeforeQuestionSuccess(players, q)) {
            fail("GameMode.beforeQuestion doesn't get called properly");
        }
    }

    /**
     * Test of questionAnswered method, of class Round.
     */
    @Test
    public void testQuestionAnswered() {
        System.out.println("questionAnswered");

        TestGameMode testGameMode = new TestGameMode();
        Round instance = new Round(questions, testGameMode);
        instance.setPlayers(players);
        Question q = new Question("test", null, "test", "a", new String[]{"a", "b", "c", "d"});

        instance.questionAnswered(0, q, 0);

        if (!testGameMode.getQuestionAnsweredCalled(players.get(0), 0, true)) {
            fail("GameMode.questionAnswered doesn't get called properly");
        }

        instance.questionAnswered(0, q, 1);

        if (!testGameMode.getQuestionAnsweredCalled(players.get(0), 0, false)) {
            fail("GameMode.questionAnswered doesn't get called properly");
        }
    }

    /**
     * Test of chooseQuestion method, of class Round.
     */
    @Test
    public void testChooseQuestion() {
        System.out.println("chooseQuestion");

        Round instance = new Round(questions, new TestGameMode());
        ArrayList<Question> testUnique = new ArrayList<>();

        for (int i = 0; i < questions.getTotalQuestions(); i++) {
            testUnique.add(questions.getQuestion(i));
        }

        boolean unique = true;

        for (int i = 0; i < questions.getTotalQuestions(); i++) {
            Question q = instance.chooseQuestion();

            int count = 0;

            if (testUnique.contains(q)) {
                count++;
            }

            if (count > 1) {
                unique = false;

                break;
            }
        }

        if (!unique) {
            fail("The chosen questions aren't random and unique!");
        }
    }
}

class TestGameMode extends GameMode {
    private boolean beforeQuestionCalled = false, questionAnsweredCalled = false;
    private Question beforeQuestionParam;
    private Player questionAnsweredParam;
    private boolean correctAnswerParam;
    private int correctPlayerIndex;
    private ArrayList<Player> correctPlayerArraylist;

    public TestGameMode() {
        super(null);
    }

    @Override
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        beforeQuestionParam = question;
        correctPlayerArraylist = players;

        beforeQuestionCalled = true;
    }

    @Override
    public void questionAnswered(Player player, int index, boolean correct) {
        questionAnsweredParam = player;
        correctAnswerParam = correct;
        correctPlayerIndex = index;

        questionAnsweredCalled = true;
    }

    public boolean getBeforeQuestionSuccess(ArrayList<Player> players, Question param) {
        return beforeQuestionCalled && this.beforeQuestionParam == param && correctPlayerArraylist.equals(players);
    }

    public boolean getQuestionAnsweredCalled(Player player, int index, boolean correct) {
        return questionAnsweredCalled && questionAnsweredParam == player && correctPlayerIndex == index && correctAnswerParam == correct;
    }

    @Override
    public JPanel getAdditionalGUI() {
        //NO GUI
        return null;
    }

    @Override
    public int getId() {
        return -1;
    }

}
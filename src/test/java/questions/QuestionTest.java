package questions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Manolis, Spiros
 */
public class QuestionTest {

    public QuestionTest() {
    }

    /**
     * Test of getQuestion method, of class Question.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");

        Question instance = new Question("question", null, "category", "correct", new String[]{"", "", "", ""});
        String expResult = "question";
        String result = instance.getQuestion();

        assertEquals(expResult, result);
    }

    /**
     * Test of getRightAnswer method, of class Question.
     */
    @Test
    public void testGetRightAnswer() {
        System.out.println("getRightAnswer");

        Question instance = new Question("question", null, "category", "correct", new String[]{"", "", "", ""});
        String expResult = "correct";
        String result = instance.getRightAnswer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuestionType method, of class Question.
     */
    @Test
    public void testGetQuestionType() {
        System.out.println("getQuestionType");

        Question instance = new Question("question", null, "category", "correct", new String[]{"", "", "", ""});
        String expResult = "category";
        String result = instance.getQuestionType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnswers method, of class Question.
     */
    @Test
    public void testGetAnswers() {
        System.out.println("getAnswers");

        String[] initial = new String[]{"a", "b", "c", "d"};
        Question instance = new Question("test", null, "test", "a", initial);

        //test defencive copying
        String[] array = instance.getAnswers();
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";

        String[] expResult = initial;
        String[] result = instance.getAnswers();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of shuffle method, of class Question.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");

        String[] initial = new String[]{"a", "b", "c", "d"};
        Question instance = new Question("test", null, "test", "a", initial);

        boolean different = false;

        for (int i = 0; i < 5; i++) {
            instance.shuffle();

            String[] shuffled = instance.getAnswers();

            for (int z = 0; z < 4; z++) {
                if (!shuffled[z].equals(initial[z])) {
                    different = true;

                    break;
                }
            }

            if (different) {
                break;
            }
        }

        if (!different) {
            fail("Question.shuffle doesn't shuffles!");
        }
    }

    /**
     * Test of isCorrect method, of class Question.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");

        String[] initial = new String[]{"a", "b", "c", "d"};
        Question instance = new Question("test", null, "test", "a", initial);

        boolean expResult = true;
        boolean result = instance.isCorrect("a");

        assertEquals(expResult, result);

        instance = new Question("test", null, "test", "b", initial);

        expResult = false;
        result = instance.isCorrect("a");

        assertEquals(expResult, result);
    }

}

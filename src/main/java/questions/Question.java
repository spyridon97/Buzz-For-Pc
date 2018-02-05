package questions;

import java.awt.Image;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Manolis, Spiros
 */
public class Question {

    private final String question, questionType, rightAnswer;
    private final Image questionImage;
    private final String[] answers = new String[4];

    /**
     * minimal constructor, initializes everything with empty Strings
     */
    public Question() {
        this("", null, "", "", new String[]{"", "", "", ""});
    }

    /**
     * set values to
     *
     * @param question      contains the string for the question
     * @param questionImage contains the image of the question
     * @param questionType  defines the type of the question
     * @param rightAnswer   contains the right answer as a string
     * @param answers       String array with the possible answers for the question
     */
    public Question(String question, Image questionImage, String questionType, String rightAnswer, String[] answers) {
        this.question = question;
        this.questionImage = questionImage;
        this.questionType = questionType;
        this.rightAnswer = rightAnswer;

        if (answers == null || answers.length != 4) {
            throw new IllegalArgumentException("There must be 4 possible answers to all questions!");
        }

        for (int i = 0; i < 4; i++) {
            this.answers[i] = answers[i];
        }
    }

    /**
     * @return the string with the question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * @return the image of the question
     */
    public Image getImage() {
        return this.questionImage;
    }

    /**
     * @return the right answer as a string
     */
    public String getRightAnswer() {
        return rightAnswer;
    }

    /**
     * @return the string with the question type
     */
    public String getQuestionType() {
        return this.questionType;
    }

    /**
     * Return a copy of the actual array, so it's values can't be changed from
     * the returned instance
     *
     * @return the answer array
     */
    public String[] getAnswers() {
        return Arrays.copyOf(answers, answers.length);
    }

    /**
     * shuffles the answers using a slight variation of the algorithm used in
     * java.util.Collections.shuffle(...)
     */
    public void shuffle() {
        Random random = new Random();

        for (int i = 4; i >= 1; i--) {
            int randomElement = random.nextInt(i);

            String temp = answers[randomElement];
            answers[randomElement] = answers[i - 1];
            answers[i - 1] = temp;
        }
    }

    /**
     * @param selectedAnswer the answer that the users gives in order to check
     *                       if he answered correctly
     * @return the boolean with the corresponding value
     */
    public boolean isCorrect(String selectedAnswer) {
        return rightAnswer.equals(selectedAnswer);
    }

    /**
     * @return if the question has image or not
     */
    public boolean hasImage() {
        return getImage() != null;
    }
}

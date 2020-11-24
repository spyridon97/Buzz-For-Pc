package questions;

import internationalization.Language;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Manolis, Spiros
 */
public class Questions {

    private final ArrayList<Question> questions;
    private final Language language;

    /**
     * @param language the instance of the current language choice
     */
    public Questions(Language language) {
        questions = new ArrayList<Question>();
        this.language = language;

        read();
    }

    /**
     * @param index of the question
     * @return the question which exists at the given index
     */
    public Question getQuestion(int index) {
        return questions.get(index);
    }

    /**
     * @return the number of total questions which are included in the file
     */
    public int getTotalQuestions() {
        return questions.size();
    }

    /**
     * Required format for the question file:
     * <p>
     * [question text] [question image] [question category] [correct question as
     * a number 1-4] [answer 1] [answer 2] [answer 3] [answer 4] [empty line]
     * <p>
     * ...
     * <p>
     * THE FILE MUST BE UTF-8
     * <p>
     * Reads the questions from the specified file and stores them as Question
     * objects in the questions ArrayList
     */
    public void read() {
        questions.clear();
        try {

            InputStream questionsStream = getClass().getResourceAsStream(language.getMessage("questionsFileName"));

            if (questionsStream == null) {
                JOptionPane.showMessageDialog(null, language.getMessage("questionFileErrorText"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            Scanner reader = new Scanner(questionsStream, "UTF-8");

            int counter = 0;
            while (reader.hasNextLine()) {
                String question = readLine(reader);
                String imageURL = readLine(reader);
                Image questionImage = null;

                // this is added to remove the starting special character from the file
                if (counter == 0) {
                    question = question.substring(1);
                    counter++;
                }

                if (!imageURL.equalsIgnoreCase("null")) {
                    InputStream imageStream = getClass().getResourceAsStream(imageURL);
                    questionImage = new ImageIcon(ImageIO.read(imageStream)).getImage();
                }

                String questionType = readLine(reader);
                String correctAnswer = readLine(reader);

                String[] answers = new String[4];
                for (int i = 0; i < 4; i++) {
                    answers[i] = readLine(reader);
                }

                //Consume the empty line at the end of the current question
                readLine(reader);

                int correctAnswerIndex = Integer.parseInt(correctAnswer);
                correctAnswer = answers[correctAnswerIndex - 1];

                Question q = new Question(question, questionImage, questionType, correctAnswer, answers);

                questions.add(q);
            }

            reader.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Helper method for read() - Used above
     * <p>
     * Checks if the scanner has an available line to read and returns the empty
     * string if there isn't available input
     *
     * @param reader the java.util.Scanner in use
     * @return The read line or empty string if there is no available input
     */
    private String readLine(Scanner reader) {
        if (reader.hasNextLine()) {
            return reader.nextLine();
        } else {
            return "";
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questions;

import internationalization.Language;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
            File questionSource = new File(language.getMessage("questionsFileName"));

            if (!questionSource.exists()) {
                JOptionPane.showMessageDialog(null, language.getMessage("questionFileErrorText"), language.getMessage("fileError"), JOptionPane.ERROR_MESSAGE);
                System.exit(1);

            }

            Scanner reader = new Scanner(questionSource, "UTF-8");

            while (reader.hasNextLine()) {
                String question = readLine(reader);
                String imageDirectory = readLine(reader);
                Image questionImage = null;

                if (!imageDirectory.equalsIgnoreCase("null")) {
                    questionImage = new ImageIcon("Photos of questions/" + imageDirectory).getImage();
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
        } catch (FileNotFoundException exc) {
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

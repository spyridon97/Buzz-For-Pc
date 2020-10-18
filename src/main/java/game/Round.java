package game;

import gameModes.GameMode;
import questions.Question;
import questions.Questions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Manolis, Spiros
 */
public class Round {

    private GameMode mode;
    private final Questions questions;
    private final ArrayList<Integer> questionsToShow;
    private int questionPointer = 0;
    private ArrayList<Player> players;

    /**
     * @param questions the Questions object as read from the corresponding file
     * @param mode      the desired mode to play, as a GameMode enum
     */
    public Round(Questions questions, GameMode mode) {
        this.questions = questions;
        this.mode = mode;

        //We create a lookup arraylist and shuffle the elements and we use the questionPointer in order to choose the index from the arraylist
        questionsToShow = new ArrayList<Integer>();
        for (int i = 0; i < questions.getTotalQuestions(); i++) {
            questionsToShow.add(i);
        }

        Collections.shuffle(questionsToShow);
    }

    /**
     * @param question the question that is shown
     * @param players  the players of the game
     */
    public void beforeQuestion(Question question, ArrayList<Player> players) {
        mode.beforeQuestion(players, question);
    }

    /**
     * @param playerIndex  the index of the player that answered this question
     * @param question     the question that was answered
     * @param chosenAnswer the chosen answer as an index (values [0-3])
     */
    public void questionAnswered(int playerIndex, Question question, int chosenAnswer) {
        boolean isAnswerCorrect;

        String playerChoice = question.getAnswers()[chosenAnswer];

        isAnswerCorrect = playerChoice.equals(question.getRightAnswer());

        mode.questionAnswered(players.get(playerIndex), playerIndex, isAnswerCorrect);
    }

    /**
     * @return a unique random question
     */
    public Question chooseQuestion() {
        //If all elements are used we reset the pointer and reshuffle the arraylist
        if (questionPointer == questions.getTotalQuestions()) {
            questionPointer = 0;
            Collections.shuffle(questionsToShow);
        }

        Question ret = questions.getQuestion(questionsToShow.get(questionPointer));
        questionPointer++;

        return ret;
    }

    /**
     * @return the players that are playing this round
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players that are gonna play in this round
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * @param mode the gameMode of the round that players are gonna play
     */
    public void setGameMode(GameMode mode) {
        this.mode = mode;
    }

    /**
     * @return the gameMode that players are playing
     */
    public GameMode getGameMode() {
        return mode;
    }

    /**
     * @return a boolean type that answers if a round has ended
     */
    public boolean hasEnded() {
        return mode.hasEnded();
    }

    /**
     * updates the language
     */
    public void updateQuestionLanguage() {
        this.questions.read();
    }
}

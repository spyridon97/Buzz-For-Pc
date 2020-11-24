package userInterface;

import game.Player;
import game.Round;
import questions.Question;
import userInterface.components.GameAnswerButton;
import userInterface.components.GameLabel;
import userInterface.components.GameTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Manolis, Spiros
 */
public class GameModePanel extends JPanel implements HierarchyListener, ActionListener {

    private static final long serialVersionUID = 5192279939566980221L;

    private static final Color overlayGreen = new Color(0, 255, 0, 100);
    private static final Color overlayRed = new Color(255, 0, 0, 100);

    private final int margin = 20;

    private final MainFrame main;

    private GameTextArea questionDisplay;
    private GameAnswerButton[] answerButtons = new GameAnswerButton[4];
    private GameLabel imageLabel;
    private GameLabel[] pointLabels = new GameLabel[4];
    private JPanel gamemodeSpecific;

    private boolean[] hasPlayerAnswered;
    private boolean fromKeyboard = false;
    private int fromPlayer = 0, answersMade = 0;

    private Question currentQuestion = null;

    private Timer fadeAnimation = new Timer(1000 / 40, this);
    private int textOpacity = 0, targetTextOpacity = 0;
    private boolean showQuestionAfterAnimation = false;

    /**
     * @param main The MainFrame instance of the application
     */
    public GameModePanel(MainFrame main) {
        this.main = main;

        setupComponents();
    }

    /**
     * Initialization of all Components used in this Panel Complex Swing code to
     * achieve all needed relations and event handling of the components in this
     * panel. The text is initialized to Greek by default
     */
    private void setupComponents() {
        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(main.width, main.height));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, margin, margin));
        this.addHierarchyListener(this);

        Font displayFont = new Font("Arial", Font.PLAIN, 28);
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);

        questionDisplay = new GameTextArea();
        questionDisplay.setLineWrap(true);
        questionDisplay.setWrapStyleWord(true);
        questionDisplay.setEditable(false);
        questionDisplay.setFont(displayFont);
        questionDisplay.setHighlighter(null);
        questionDisplay.setForeground(new Color(0, 0, 0, textOpacity));

        imageLabel = new GameLabel("");
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        top.add(questionDisplay);
        top.add(imageLabel);
        top.setBackground(Color.white);
        top.setPreferredSize(new Dimension(main.width - margin * 2, 200));
        this.add(top);

        JPanel downArea = new JPanel();
        downArea.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        downArea.setPreferredSize(new Dimension(main.width - 40, 400 - 60));

        JPanel borderPanel = new JPanel();
        borderPanel.setBackground(Color.green);
        borderPanel.setPreferredSize(new Dimension(main.width - 200 - margin * 2, 400 - 60));

        borderPanel.setLayout(new BorderLayout(0, 0));

        gamemodeSpecific = new JPanel();
        gamemodeSpecific.setPreferredSize(new Dimension(1, 80));
        gamemodeSpecific.setLayout(new BorderLayout());

        borderPanel.add(gamemodeSpecific, BorderLayout.NORTH);

        JPanel b2 = new JPanel();
        b2.setPreferredSize(new Dimension(100, 170));
        b2.setLayout(new GridLayout(2, 2, 10, 10));

        for (int i = 0; i < answerButtons.length; i++) {
            GameAnswerButton answerButton = new GameAnswerButton();
            answerButton.setFont(buttonFont);
            answerButton.setForeground(new Color(0, 0, 0, textOpacity));

            final int answerIndex = i;

            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Round game = main.getRound();

                    if (game.getPlayers().size() == 1) {
                        game.questionAnswered(0, currentQuestion, answerIndex);

                        answersMade++;
                    } else {
                        if (fromKeyboard) {
                            game.questionAnswered(fromPlayer, currentQuestion, answerIndex);
                            answerButton.playerAnswer(game.getPlayers().get(fromPlayer).getName());

                            answersMade++;
                            fromKeyboard = false;
                        }
                    }

                    if (answersMade >= game.getPlayers().size()) {
                        String correct = currentQuestion.getRightAnswer();
                        String[] answers = currentQuestion.getAnswers();

                        for (int i = 0; i < answers.length; i++) {
                            if (answers[i].equals(correct)) {
                                answerButtons[i].setColorOverlay(overlayGreen);
                            } else {
                                answerButtons[i].setColorOverlay(overlayRed);
                            }
                        }

                        updatePointLabels();
                        fadeForNextQuestion();
                    }
                }
            });

            b2.add(answerButton);

            answerButtons[i] = answerButton;
        }

        borderPanel.add(b2, BorderLayout.CENTER);

        JPanel playerPoints = new JPanel();
        playerPoints.setPreferredSize(new Dimension(200, 400 - 60));

        Font fontNames = new Font("Arial", Font.BOLD, 19);
        Font fontPoints = new Font("Arial", Font.PLAIN, 19);

        for (int i = 0; i < 2; i++) {
            pointLabels[i * 2] = new GameLabel(" ", 200);
            pointLabels[i * 2].setFont(fontNames);
            playerPoints.add(pointLabels[i * 2]);

            pointLabels[i * 2 + 1] = new GameLabel(" ", 200);
            pointLabels[i * 2 + 1].setFont(fontPoints);
            playerPoints.add(pointLabels[i * 2 + 1]);
        }

        downArea.add(borderPanel);
        downArea.add(playerPoints);

        this.add(downArea);
    }

    /**
     * @return the current question showing
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * gets called before a question is displayed
     */
    private void beforeQuestion() {
        Round game = main.getRound();

        if (currentQuestion != null) {
            game.getGameMode().afterQuestion();
        }

        if (game.hasEnded()) {
            main.getStats().addStat(game.getPlayers(), game.getGameMode().getId());

            main.setLayer(MainFrame.ENDGAME);
        } else {
            currentQuestion = game.chooseQuestion();
            game.getGameMode().beforeQuestion(game.getPlayers(), currentQuestion);

            //imageLabel.setIcon(null);
        }
    }

    /**
     * fades-out the text and prepares to show the next question
     */
    public void fadeForNextQuestion() {
        showQuestionAfterAnimation = true;
        targetTextOpacity = 0;

        for (GameAnswerButton button : answerButtons) {
            button.setEnabled(false);
        }
    }

    //called by game modes

    /**
     * Gets called by the current GameMode to show the next "prepared" question
     */
    public void showNextQuestion() {
        questionDisplay.setText(currentQuestion.getQuestion());

        String[] answers = currentQuestion.getAnswers();

        for (int i = 0; i < answers.length; i++) {
            answerButtons[i].setText(answers[i]);
            answerButtons[i].clearPlayerAnswers();
            answerButtons[i].setEnabled(true);
        }

        Arrays.fill(hasPlayerAnswered, false);
        answersMade = 0;

        if (currentQuestion.getImage() != null) {
            imageLabel.setIcon(new ImageIcon(currentQuestion.getImage()));
            imageLabel.setPreferredSize(new Dimension(150, 200));
            questionDisplay.setPreferredSize(new Dimension(main.width - margin * 2 - 150, 200));
        } else {
            imageLabel.setIcon(null);
            imageLabel.setPreferredSize(new Dimension(0, 200));
            questionDisplay.setPreferredSize(new Dimension(main.width - margin * 2, 200));
        }

        targetTextOpacity = 270;
    }

    @Override
    /**
     * handles the localization of the components based on the current Language
     * and executes code for when the component is displayed on the screen
     */
    public void hierarchyChanged(HierarchyEvent evt) {
        if ((HierarchyEvent.SHOWING_CHANGED & evt.getChangeFlags()) != 0 && this.isShowing()) {
            setPlayerKeybindings();
            updatePointLabels();

            Round game = main.getRound();

            hasPlayerAnswered = new boolean[game.getPlayers().size()];
            Arrays.fill(hasPlayerAnswered, false);

            JPanel additionalGUI = game.getGameMode().getAdditionalGUI();
            gamemodeSpecific.removeAll();

            if (additionalGUI != null) {
                gamemodeSpecific.setPreferredSize(new Dimension(1, 80));
                gamemodeSpecific.add(additionalGUI, BorderLayout.CENTER);
            } else {
                gamemodeSpecific.setPreferredSize(new Dimension(1, 0));
            }

            if (!fadeAnimation.isRunning()) {
                fadeAnimation.start();
            }

            fadeForNextQuestion();
        }
    }

    /**
     * updates the text in the bottom-right panel where the current points of
     * the player(s) are displayed
     */
    private void updatePointLabels() {
        ArrayList<Player> players = main.getRound().getPlayers();

        for (int i = 0; i < players.size(); i++) {
            pointLabels[i * 2].setText(players.get(i).getName() + ":");
            pointLabels[i * 2 + 1].setText("     " + players.get(i).getPoints() + " " + main.getLanguage().getMessage("points"));
        }
    }

    @SuppressWarnings("serial")
    /**
     * Sets the buttons to activate when the players press the corresponding key
     * that they selected in the SettingsPanel
     */
    private void setPlayerKeybindings() {
        ArrayList<Player> players = main.getRound().getPlayers();

        for (int i = 0; i < answerButtons.length; i++) {
            GameAnswerButton answerButton = answerButtons[i];
            answerButton.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, new ComponentInputMap(answerButton));//.getInputMap().clear();
            answerButton.clearKeyBindings();

            for (int z = 0; z < players.size(); z++) {
                int[] kb = players.get(z).getKeyBindings();
                final int playerIndex = z;

                answerButton.addKeyBinding((char) kb[i]);
                answerButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(kb[i], 0), "pressed" + z);
                answerButton.getActionMap().put("pressed" + z, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (!hasPlayerAnswered[playerIndex]) {
                            fromKeyboard = true;
                            fromPlayer = playerIndex;
                            hasPlayerAnswered[playerIndex] = true;

                            answerButton.doClick();
                        }
                    }
                });
            }
        }
    }

    @Override
    /**
     * Gets called only by the swing Timer, used to perform the textFade in-out
     * There is the current and target opacity, and operations performed adjust
     * the current to go towards the target
     */
    public void actionPerformed(ActionEvent evt) {
        boolean updateColor = true;

        if (textOpacity < targetTextOpacity) {
            textOpacity += 5;
        } else if (textOpacity > targetTextOpacity) {
            textOpacity -= 5;
        } else {
            updateColor = false;

            if (showQuestionAfterAnimation && targetTextOpacity == 0) {
                for (GameAnswerButton button : answerButtons) {
                    button.setColorOverlay(null);
                }

                beforeQuestion();

                showQuestionAfterAnimation = false;
            }
        }

        if (updateColor) {
            int opacityValue = Math.min(textOpacity, 255);

            questionDisplay.setForeground(new Color(0, 0, 0, opacityValue));

            for (GameAnswerButton answerButton : answerButtons) {
                answerButton.setForeground(new Color(0, 0, 0, opacityValue));
            }
        }
    }

}

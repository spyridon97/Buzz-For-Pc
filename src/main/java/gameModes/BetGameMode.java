package gameModes;

import game.Player;
import internationalization.Language;
import questions.Question;
import userInterface.MainFrame;
import userInterface.components.GameButton;
import userInterface.components.GameLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Manolis, Spiros
 */
public class BetGameMode extends GameMode {

    private int[] pointsBet;
    private int currentBettingPlayer = 0;
    private ArrayList<Player> currentPlayers;

    JPanel betGamemode;
    CardLayout layout;
    GameLabel betPlayerLabel;

    /**
     * @param main The MainFrame instance of the application
     */
    public BetGameMode(MainFrame main) {
        super(main);
        pointsBet = new int[2];
    }

    @Override
    /**
     * See GameMode.getAdditionalGUI documentation
     *
     * This specific implementation makes the Bet Buttons and the displays for
     * the bet amounts
     */
    public JPanel getAdditionalGUI() {
        betGamemode = new JPanel();
        layout = new CardLayout();
        betGamemode.setLayout(layout);

        JPanel showBets = new JPanel();
        showBets.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        GameLabel showBetsLabel = new GameLabel(" ");
        showBets.add(showBetsLabel);

        JPanel betPanel = new JPanel();
        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        betPlayerLabel = new GameLabel(" ", 550, 26);
        betPlayerLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        betPanel.add(betPlayerLabel);

        JPanel buttonContainer = new JPanel();

        for (int i = 0; i < 4; i++) {
            final int points = (i + 1) * 250;

            GameButton betButton = new GameButton(points + "", 80, 40);
            betButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pointsBet[currentBettingPlayer] = points;
                    currentBettingPlayer++;

                    Language language = main.getLanguage();

                    if (currentBettingPlayer >= currentPlayers.size()) {
                        String playersBet = currentPlayers.get(0).getName() + ": " + pointsBet[0] + " " + language.getMessage("points");

                        for (int i = 1; i < currentPlayers.size(); i++) {
                            playersBet += ", " + currentPlayers.get(i).getName() + ": " + pointsBet[i] + " " + language.getMessage("points");
                        }

                        showBetsLabel.setText(playersBet);
                        layout.show(betGamemode, "showBets");

                        main.getGameModePanel().showNextQuestion();
                    } else {
                        String cat = language.getMessage("category") + ": " + main.getGameModePanel().getCurrentQuestion().getQuestionType();
                        betPlayerLabel.setText(currentPlayers.get(currentBettingPlayer).getName() + " " + language.getMessage("betPoints") + ". " + cat);
                    }
                }
            });

            buttonContainer.add(betButton);
        }

        betPanel.add(buttonContainer);

        betGamemode.add(betPanel, "selectBets");
        betGamemode.add(showBets, "showBets");

        return betGamemode;
    }

    @Override
    /**
     * For bet GameMode before the betting options are shown alongside the
     * question category and then the player must choose one valid option.
     */
    public void beforeQuestion(ArrayList<Player> players, Question question) {
        layout.show(betGamemode, "selectBets");

        this.currentPlayers = players;
        this.currentBettingPlayer = 0;

        String cat = main.getLanguage().getMessage("category") + ": " + main.getGameModePanel().getCurrentQuestion().getQuestionType();
        betPlayerLabel.setText(players.get(0).getName() + ", " + main.getLanguage().getMessage("betPoints") + ". " + cat);
    }

    @Override
    /**
     * Checks if the answer was correct, and with showing a corresponding
     * message either adds or subtracts the points bet by the player previously
     */
    public void questionAnswered(Player player, int playerIndex, boolean correct) {
        if (correct) {
            player.addPoints(pointsBet[playerIndex]);
        } else {
            player.reducePoints(pointsBet[playerIndex]);
        }
    }

    @Override
    /**
     *
     * @return the id of BetGameMode
     */
    public int getId() {
        return 2;
    }

}

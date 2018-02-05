package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

import game.Player;
import game.Round;
import gameModes.BetGameMode;
import gameModes.CorrectAnswerGameMode;
import gameModes.FastAnswerGameMode;
import gameModes.GameMode;
import gameModes.StopTimerGameMode;
import gameModes.ThermometerGameMode;
import internationalization.Language;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import userInterface.components.GameButton;
import userInterface.components.GameLabel;
import userInterface.components.GameRadioButton;
import userInterface.components.GameSplitPane;
import userInterface.components.GameTextArea;

/**
 * @author Manolis, Spiros
 */
public class SettingsPanel extends JPanel implements HierarchyListener {

    private static final long serialVersionUID = 5192279939566980221L;

    private final int margin = 20;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private GameRadioButton[] onPlayerKeybindButtons;
    private GameRadioButton[] twoPlayerKeybindButtons1, twoPlayerKeybindButtons2;

    //Used for renaming depending on the Locale
    private GameButton onePlayerButton, twoPlayerButton, continueToGame;
    private GameLabel onePlayerNameLabel, onePlayerSelectGameMode, onePlayerSelectBindings;
    private GameLabel twoPlayerFirstNameLabel, twoPlayerSecondNameLabel, twoPlayerSelectGameMode, twoPlayerSelectBindings;
    private GameLabel onePlayerGameModeLabel, twoPlayerGameModeLabel, gameModeDescription;
    private GameTextArea descriptionDisplay;

    private String[] onePlayerGameModeNames = {"Σωστή Απάντηση", "Σταμάτησε το χρονόμετρο", "Ποντάρισμα"};
    private int onePlayerGameModeShown = 0;

    private String[] twoPlayerGameModeNames = {"Σωστή Απάντηση", "Σταμάτησε το χρονόμετρο", "Ποντάρισμα", "Γρήγορη Απάντηση", "Θερμόμετρο"};
    private int twoPlayerGameModeShown = 0;

    private boolean isOnePlayer = true;

    private int[][] onePlayerKeybindOptions = {
            {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4},
            {KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F},
            {KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_R}
    };

    private int[][] twoPlayerKeybindOptions = {
            {KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_0},
            {KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L},
            {KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O, KeyEvent.VK_P}
    };

    private final MainFrame main;

    /**
     * @param main The MainFrame instance of the application
     */
    public SettingsPanel(MainFrame main) {
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

        onePlayerButton = new GameButton("Ένας παίκτης");

        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                isOnePlayer = true;
                cardLayout.show(cardPanel, "one player");
                updateDescriptionText();
            }
        });

        twoPlayerButton = new GameButton("Δύο παίκτες");

        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                isOnePlayer = false;
                cardLayout.show(cardPanel, "two players");
                updateDescriptionText();
            }
        });

        GameSplitPane top = new GameSplitPane(50, JSplitPane.HORIZONTAL_SPLIT, onePlayerButton, twoPlayerButton);
        top.setPreferredSize(new Dimension(main.width - margin * 2, 80));
        add(top);

        JPanel settingsArea = new JPanel();
        settingsArea.setPreferredSize(new Dimension(main.width - margin * 2, main.height - 80 - 60 - 60));

        cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(main.width - margin * 2, main.height - 80 - 60 - 60 - 120));

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel onePlayerPanel = new JPanel();

        onePlayerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        onePlayerNameLabel = new GameLabel("Όνομα παίκτη", 300);
        onePlayerPanel.add(onePlayerNameLabel);

        GameTextArea onePlayerName = new GameTextArea(320);
        onePlayerName.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        TransferFocus.patch(onePlayerName);
        onePlayerPanel.add(onePlayerName);

        onePlayerPanel.add(new GameLabel("", 100));

        onePlayerGameModeLabel = new GameLabel(onePlayerGameModeNames[onePlayerGameModeShown]);
        onePlayerGameModeLabel.setPreferredSize(new Dimension(200, 28));
        onePlayerGameModeLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        GameButton onePlayerGameModeLeft = new GameButton("<", 50);
        TransferFocus.patch(onePlayerGameModeLeft);
        GameButton onePlayerGameModeRight = new GameButton(">", 50);
        TransferFocus.patch(onePlayerGameModeRight);

        onePlayerGameModeLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onePlayerGameModeShown--;

                if (onePlayerGameModeShown < 0) {
                    onePlayerGameModeShown = onePlayerGameModeNames.length - 1;
                }

                onePlayerGameModeLabel.setText(onePlayerGameModeNames[onePlayerGameModeShown]);
                updateDescriptionText();
            }
        });

        onePlayerGameModeRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onePlayerGameModeShown++;

                if (onePlayerGameModeShown >= onePlayerGameModeNames.length) {
                    onePlayerGameModeShown = 0;
                }

                onePlayerGameModeLabel.setText(onePlayerGameModeNames[onePlayerGameModeShown]);
                updateDescriptionText();
            }
        });

        onePlayerSelectGameMode = new GameLabel("Επιλογή τύπου παιχνιδιού", 300);
        onePlayerPanel.add(onePlayerSelectGameMode);

        onePlayerPanel.add(onePlayerGameModeLeft);
        onePlayerPanel.add(onePlayerGameModeLabel);
        onePlayerPanel.add(onePlayerGameModeRight);

        onePlayerPanel.add(new GameLabel("", 100));

        onePlayerSelectBindings = new GameLabel("Επιλογή κουμπιών", 300);
        onePlayerPanel.add(onePlayerSelectBindings);

        onPlayerKeybindButtons = new GameRadioButton[3];
        onPlayerKeybindButtons[0] = new GameRadioButton("1, 2, 3, 4", 125);
        onPlayerKeybindButtons[1] = new GameRadioButton("A, S, D, F", 125);
        onPlayerKeybindButtons[2] = new GameRadioButton("Q, W, E, R", 125);

        for (GameRadioButton rbutton : onPlayerKeybindButtons) {
            TransferFocus.patch(rbutton);
        }

        ButtonGroup group = new ButtonGroup();
        group.add(onPlayerKeybindButtons[0]);
        group.add(onPlayerKeybindButtons[1]);
        group.add(onPlayerKeybindButtons[2]);

        onPlayerKeybindButtons[0].setSelected(true);

        onePlayerPanel.add(onPlayerKeybindButtons[0]);
        onePlayerPanel.add(new GameLabel("", 100));

        onePlayerPanel.add(new GameLabel("", 300));
        onePlayerPanel.add(onPlayerKeybindButtons[1]);
        onePlayerPanel.add(new GameLabel("", 100));

        onePlayerPanel.add(new GameLabel("", 300));
        onePlayerPanel.add(onPlayerKeybindButtons[2]);

        cardPanel.add(onePlayerPanel, "one player");

        JPanel twoPlayerPanel = new JPanel();

        twoPlayerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        twoPlayerFirstNameLabel = new GameLabel("Όνομα πρώτου παίκτη", 300);
        twoPlayerPanel.add(twoPlayerFirstNameLabel);

        GameTextArea twoPlayerFirstName = new GameTextArea(320);
        twoPlayerFirstName.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        TransferFocus.patch(twoPlayerFirstName);
        twoPlayerPanel.add(twoPlayerFirstName);

        twoPlayerPanel.add(new GameLabel("", 100));

        twoPlayerSecondNameLabel = new GameLabel("Όνομα δεύτερου παίκτη", 300);
        twoPlayerPanel.add(twoPlayerSecondNameLabel);

        GameTextArea twoPlayerSecondName = new GameTextArea(320);
        twoPlayerSecondName.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        TransferFocus.patch(twoPlayerSecondName);
        twoPlayerPanel.add(twoPlayerSecondName);

        twoPlayerPanel.add(new GameLabel("", 100));

        twoPlayerGameModeLabel = new GameLabel(twoPlayerGameModeNames[onePlayerGameModeShown]);
        twoPlayerGameModeLabel.setPreferredSize(new Dimension(200, 28));
        twoPlayerGameModeLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        GameButton twoPlayerGameModeLeft = new GameButton("<", 50);
        TransferFocus.patch(twoPlayerGameModeLeft);
        GameButton twoPlayerGameModeRight = new GameButton(">", 50);
        TransferFocus.patch(twoPlayerGameModeRight);

        twoPlayerGameModeLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoPlayerGameModeShown--;

                if (twoPlayerGameModeShown < 0) {
                    twoPlayerGameModeShown = twoPlayerGameModeNames.length - 1;
                }

                twoPlayerGameModeLabel.setText(twoPlayerGameModeNames[twoPlayerGameModeShown]);
                updateDescriptionText();
            }
        });

        twoPlayerGameModeRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoPlayerGameModeShown++;

                if (twoPlayerGameModeShown >= twoPlayerGameModeNames.length) {
                    twoPlayerGameModeShown = 0;
                }

                twoPlayerGameModeLabel.setText(twoPlayerGameModeNames[twoPlayerGameModeShown]);
                updateDescriptionText();
            }
        });

        twoPlayerSelectGameMode = new GameLabel("Επιλογή τύπου παιχνιδιού", 300);
        twoPlayerPanel.add(twoPlayerSelectGameMode);

        twoPlayerPanel.add(twoPlayerGameModeLeft);
        twoPlayerPanel.add(twoPlayerGameModeLabel);
        twoPlayerPanel.add(twoPlayerGameModeRight);

        twoPlayerPanel.add(new GameLabel("", 100));

        twoPlayerSelectBindings = new GameLabel("Επιλογή κουμπιών", 300);
        twoPlayerPanel.add(twoPlayerSelectBindings);

        twoPlayerKeybindButtons1 = new GameRadioButton[3];
        twoPlayerKeybindButtons1[0] = new GameRadioButton("1, 2, 3, 4", 125);
        twoPlayerKeybindButtons1[1] = new GameRadioButton("A, S, D, F", 125);
        twoPlayerKeybindButtons1[2] = new GameRadioButton("Q, W, E, R", 125);

        for (GameRadioButton rbutton : twoPlayerKeybindButtons1) {
            TransferFocus.patch(rbutton);
        }

        twoPlayerKeybindButtons2 = new GameRadioButton[3];
        twoPlayerKeybindButtons2[0] = new GameRadioButton("7, 8, 9, 0", 125);
        twoPlayerKeybindButtons2[1] = new GameRadioButton("H, J, K, L", 125);
        twoPlayerKeybindButtons2[2] = new GameRadioButton("U, I, O, P", 125);

        for (GameRadioButton rbutton : twoPlayerKeybindButtons2) {
            TransferFocus.patch(rbutton);
        }

        group = new ButtonGroup();
        group.add(twoPlayerKeybindButtons1[0]);
        group.add(twoPlayerKeybindButtons1[1]);
        group.add(twoPlayerKeybindButtons1[2]);

        group = new ButtonGroup();
        group.add(twoPlayerKeybindButtons2[0]);
        group.add(twoPlayerKeybindButtons2[1]);
        group.add(twoPlayerKeybindButtons2[2]);

        twoPlayerKeybindButtons1[0].setSelected(true);
        twoPlayerKeybindButtons2[0].setSelected(true);

        twoPlayerPanel.add(twoPlayerKeybindButtons1[0]);
        twoPlayerPanel.add(twoPlayerKeybindButtons2[0]);
        twoPlayerPanel.add(new GameLabel("", 100));

        twoPlayerPanel.add(new GameLabel("", 300));
        twoPlayerPanel.add(twoPlayerKeybindButtons1[1]);
        twoPlayerPanel.add(twoPlayerKeybindButtons2[1]);
        twoPlayerPanel.add(new GameLabel("", 100));

        twoPlayerPanel.add(new GameLabel("", 300));
        twoPlayerPanel.add(twoPlayerKeybindButtons1[2]);
        twoPlayerPanel.add(twoPlayerKeybindButtons2[2]);

        cardPanel.add(twoPlayerPanel, "two players");

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setPreferredSize(new Dimension(main.width - margin * 2, 120));

        gameModeDescription = new GameLabel("Περιγραφή παιχνιδιού", main.width - margin * 2, 25);
        descriptionPanel.add(gameModeDescription);

        descriptionDisplay = new GameTextArea();
        descriptionDisplay.setPreferredSize(new Dimension(main.width - margin * 2, 100));
        descriptionDisplay.setEditable(false);
        descriptionDisplay.setHighlighter(null);
        descriptionDisplay.setLineWrap(true);
        descriptionDisplay.setWrapStyleWord(true);
        descriptionPanel.setOpaque(true);

        int showForMode = (isOnePlayer ? onePlayerGameModeShown : twoPlayerGameModeShown) + 1;
        descriptionDisplay.setText(main.getLanguage().getMessage("infoMode" + showForMode));

        descriptionPanel.add(descriptionDisplay);

        settingsArea.add(cardPanel);
        settingsArea.add(descriptionPanel);

        this.add(settingsArea);

        continueToGame = new GameButton("Έναρξη παιχνιδιού", main.width - margin * 2, 45);

        continueToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Language language = main.getLanguage();

                if (isOnePlayer) {
                    if (onePlayerName.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, language.getMessage("nameIsEmpty"), language.getMessage("nameError"), JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                } else {
                    if (twoPlayerFirstName.getText().trim().equals("") || twoPlayerSecondName.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, language.getMessage("nameIsEmpty"), language.getMessage("nameError"), JOptionPane.WARNING_MESSAGE);

                        return;
                    } else if (twoPlayerFirstName.getText().trim().equals(twoPlayerSecondName.getText().trim())) {
                        JOptionPane.showMessageDialog(null, language.getMessage("namesAreSame"), language.getMessage("namesError"), JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                }

                ArrayList<Player> playersToPlay = new ArrayList<>();

                if (isOnePlayer) {
                    Player player = new Player();
                    player.setName(onePlayerName.getText().trim());

                    int[] keyBindings = onePlayerKeybindOptions[getSelectedRadioButton(onPlayerKeybindButtons)];
                    player.setKeyBindings(keyBindings);

                    playersToPlay.add(player);
                } else {
                    Player player1 = new Player();
                    player1.setName(twoPlayerFirstName.getText().trim());

                    int[] keyBindings1 = onePlayerKeybindOptions[getSelectedRadioButton(twoPlayerKeybindButtons1)];
                    player1.setKeyBindings(keyBindings1);

                    Player player2 = new Player();
                    player2.setName(twoPlayerSecondName.getText().trim());

                    int[] keyBindings2 = twoPlayerKeybindOptions[getSelectedRadioButton(twoPlayerKeybindButtons2)];
                    player2.setKeyBindings(keyBindings2);

                    playersToPlay.add(player1);
                    playersToPlay.add(player2);
                }

                GameMode mode = null;

                switch (isOnePlayer ? onePlayerGameModeShown : twoPlayerGameModeShown) {
                    case 0:
                        mode = new CorrectAnswerGameMode(main);
                        break;
                    case 1:
                        mode = new StopTimerGameMode(main);
                        break;
                    case 2:
                        mode = new BetGameMode(main);
                        break;
                    case 3:
                        mode = new FastAnswerGameMode(main);
                        break;
                    case 4:
                        mode = new ThermometerGameMode(main);
                        break;
                }

                Round game = main.getRound();

                game.setGameMode(mode);
                game.setPlayers(playersToPlay);

                game.updateQuestionLanguage();

                main.setLayer(MainFrame.PLAY);
            }
        });

        add(continueToGame);
    }

    /**
     * update the description text for the game mode, Locale aware
     */
    private void updateDescriptionText() {
        int showForMode = (isOnePlayer ? onePlayerGameModeShown : twoPlayerGameModeShown) + 1;
        descriptionDisplay.setText(main.getLanguage().getMessage("infoMode" + showForMode));
    }

    /**
     * @param buttons the group of the buttons to get the index from
     * @return the index of the selected button
     */
    private int getSelectedRadioButton(JRadioButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public void hierarchyChanged(HierarchyEvent evt) {
        if ((HierarchyEvent.SHOWING_CHANGED & evt.getChangeFlags()) != 0 && this.isShowing()) {
            Language language = main.getLanguage();

            onePlayerButton.setText(language.getMessage("onePlayerButton"));
            twoPlayerButton.setText(language.getMessage("twoPlayerButton"));
            continueToGame.setText(language.getMessage("continueToGame"));
            onePlayerNameLabel.setText(language.getMessage("onePlayerNameLabel"));
            onePlayerSelectGameMode.setText(language.getMessage("playerSelectGameMode"));
            onePlayerSelectBindings.setText(language.getMessage("playerSelectBindings"));
            twoPlayerFirstNameLabel.setText(language.getMessage("twoPlayerFirstNameLabel"));
            twoPlayerSecondNameLabel.setText(language.getMessage("twoPlayerSecondNameLabel"));
            twoPlayerSelectGameMode.setText(language.getMessage("playerSelectGameMode"));
            twoPlayerSelectBindings.setText(language.getMessage("playerSelectBindings"));

            twoPlayerGameModeNames = GameMode.getGameModeNames();
            onePlayerGameModeNames = Arrays.copyOf(twoPlayerGameModeNames, 3);

            onePlayerGameModeLabel.setText(onePlayerGameModeNames[onePlayerGameModeShown]);
            twoPlayerGameModeLabel.setText(twoPlayerGameModeNames[twoPlayerGameModeShown]);

            gameModeDescription.setText(language.getMessage("disc"));
            updateDescriptionText();


        }
    }

}

package userInterface;

import gameModes.GameMode;
import internationalization.Language;
import stats.StatMulti;
import stats.StatSingle;
import stats.Stats;
import userInterface.components.GameButton;
import userInterface.components.GameLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;

/**
 * @author Manolis, Spiros
 */
public class HighscorePanel extends JPanel implements HierarchyListener {

    private static final long serialVersionUID = 1306990908281678202L;

    private final MainFrame main;
    private JPanel singleStats, multiStats;

    GameLabel singlePlayerNameLabel, singlePlayerPointsLabel, singlePLayerGameModeLabel;
    GameLabel multiPlayerNameLabel, multiPlayerWinsLabel;
    GameButton back;

    /**
     * @param main The MainFrame instance of the application
     */
    public HighscorePanel(MainFrame main) {
        this.main = main;

        setupComponents();
    }

    /**
     * Initialization of all Components used in this Panel Complex Swing code to
     * achieve all needed relations and event handling of the components in this
     * panel. The text is initialized to Greek by default
     */
    private void setupComponents() {
        this.setPreferredSize(new Dimension(main.width, main.height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 0));
        this.addHierarchyListener(this);
        this.setBackground(Color.darkGray);

        JPanel singleTitles = new JPanel();
        singleTitles.setPreferredSize(new Dimension(main.width / 2 - 2, 30));
        singleTitles.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        singleTitles.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        singlePlayerNameLabel = makeTitleLabel("Όνομα", main.width / 2 / 3 - 1);
        singlePlayerPointsLabel = makeTitleLabel("Πόντοι", main.width / 2 / 3 - 1);
        singlePLayerGameModeLabel = makeTitleLabel("Είδος Παιχνιδιού", main.width / 2 / 3 - 1);

        singleTitles.add(singlePlayerNameLabel);
        singleTitles.add(singlePlayerPointsLabel);
        singleTitles.add(singlePLayerGameModeLabel);

        this.add(singleTitles);

        JPanel multiTitles = new JPanel();
        multiTitles.setPreferredSize(new Dimension(main.width / 2 - 2, 30));
        multiTitles.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        multiTitles.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        multiPlayerNameLabel = makeTitleLabel("Όνομα", main.width / 2 / 2 - 2);
        multiPlayerWinsLabel = makeTitleLabel("Νίκες", main.width / 2 / 2 - 2);

        multiTitles.add(multiPlayerNameLabel);
        multiTitles.add(multiPlayerWinsLabel);

        this.add(multiTitles);

        singleStats = new JPanel();
        singleStats.setPreferredSize(new Dimension(main.width / 2 - 2, main.height - 30 - 45 - 1));
        singleStats.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        singleStats.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(singleStats);

        multiStats = new JPanel();
        multiStats.setPreferredSize(new Dimension(main.width / 2 - 2, main.height - 30 - 45 - 1));
        multiStats.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        multiStats.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(multiStats);

        back = new GameButton("Πίσω");

        back.setPreferredSize(new Dimension(100, 45));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLayer(MainFrame.MENU);
            }
        });

        this.add(back);
    }

    private Font fontTitle = new Font("Arial", Font.BOLD, 14);

    //Helper function to create title JLabels
    private GameLabel makeTitleLabel(String name, int width) {
        GameLabel label = new GameLabel(name);
        label.setPreferredSize(new Dimension(width, 30));
        label.setBackground(new Color(50, 50, 255, 20));
        label.setOpaque(true);
        label.setFont(fontTitle);

        return label;
    }

    @Override
    /**
     * handles the localization of the components based on the current Language
     * and executes code for when the component is displayed on the screen
     */
    public void hierarchyChanged(HierarchyEvent evt) {
        if ((HierarchyEvent.SHOWING_CHANGED & evt.getChangeFlags()) != 0 && this.isShowing()) {
            singleStats.removeAll();

            Language language = main.getLanguage();
            Stats stats = main.getStats();

            String[] gameModeNames = GameMode.getGameModeNames();

            ArrayList<StatSingle> sStats = stats.getStatsSingle();
            for (StatSingle stat : sStats) {
                singleStats.add(makeStatLabel(" " + stat.getName(), main.width / 2 / 3 - 1, 50));
                singleStats.add(makeStatLabel(" " + stat.getPoints(), main.width / 2 / 3 - 1, 65));
                singleStats.add(makeStatLabel(" " + gameModeNames[stat.getGameMode()], main.width / 2 / 3 - 1, 50));
            }

            ArrayList<StatMulti> mStats = stats.getStatsMulti();
            for (StatMulti stat : mStats) {
                multiStats.add(makeStatLabel(" " + stat.getName(), main.width / 2 / 2 - 2, 65));
                multiStats.add(makeStatLabel(" " + stat.getWins(), main.width / 2 / 2 - 2, 50));
            }

            singlePlayerNameLabel.setText(language.getMessage("name"));
            singlePlayerPointsLabel.setText(language.getMessage("points"));
            singlePLayerGameModeLabel.setText(language.getMessage("gameMode"));

            multiPlayerNameLabel.setText(language.getMessage("name"));
            multiPlayerWinsLabel.setText(language.getMessage("wins"));

            back.setText(language.getMessage("backButton"));
        }
    }

    private Font font = new Font("Arial", Font.PLAIN, 14);

    //Helper function to create stat JLabels
    private GameLabel makeStatLabel(String name, int width, int opacity) {
        GameLabel label = new GameLabel(name, width);
        label.setBackground(new Color(50, 50, 255, opacity));
        label.setOpaque(true);
        label.setFont(font);

        return label;
    }

}

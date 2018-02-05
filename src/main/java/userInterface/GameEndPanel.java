package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Player;
import gameModes.GameMode;
import internationalization.Language;
import userInterface.components.GameButton;
import userInterface.components.GameLabel;

/**
 * @author Manolis, Spiros
 */
public class GameEndPanel extends JPanel implements HierarchyListener {

    private static final long serialVersionUID = 1306990908281678202L;

    private final int margin = 20;

    private final Font fontTitle = new Font("Arial", Font.PLAIN, 40);

    private final MainFrame main;

    private GameButton goToMenu, goToStats;
    private GameLabel topTitle, resultLabel;
    private JPanel resultPanel;

    /**
     * @param main The MainFrame instance of the application
     */
    public GameEndPanel(MainFrame main) {
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
        this.setLayout(new FlowLayout(FlowLayout.LEFT, margin, margin));
        this.setBackground(Color.red);
        this.addHierarchyListener(this);

        JPanel contents = new JPanel();
        contents.setPreferredSize(new Dimension(main.width - margin * 2, main.height - margin * 2 - 60));
        contents.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        topTitle = new GameLabel("Η παρτίδα τελείωσε!");
        topTitle.setHorizontalAlignment(SwingConstants.CENTER);
        topTitle.setPreferredSize(new Dimension(main.width - margin * 2 - 2 * 10, 100));
        topTitle.setFont(fontTitle);

        contents.add(topTitle);

        resultLabel = new GameLabel("Αποτελέσματα:");
        contents.add(resultLabel);

        resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(main.width - margin * 2 - 2 * 10, 250));
        contents.add(resultPanel);

        this.add(contents);

        goToMenu = new GameButton("Επιστροφή στο μενού", main.width / 2 - margin * 2, 45);
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLayer(MainFrame.MENU);
            }
        });
        this.add(goToMenu);

        goToStats = new GameButton("Στατιστικά", main.width / 2 - margin, 45);
        goToStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLayer(MainFrame.HIGHSCORE);
            }
        });
        this.add(goToStats);
    }

    @Override
    /**
     * handles the localization of the components based on the current Language
     * and executes code for when the component is displayed on the screen
     */
    public void hierarchyChanged(HierarchyEvent evt) {
        if ((HierarchyEvent.SHOWING_CHANGED & evt.getChangeFlags()) != 0 && this.isShowing()) {
            Language language = main.getLanguage();

            ArrayList<Player> players = main.getRound().getPlayers();
            int fwidth = main.width - margin * 2 - 2 * 10;

            resultPanel.removeAll();

            resultPanel.add(new GameLabel("    " + language.getMessage("gameMode") + ": ", 250, 25));

            int gameModeId = main.getRound().getGameMode().getId();
            resultPanel.add(new GameLabel(GameMode.getGameModeNames()[gameModeId] + "", fwidth - 250 - 10, 25));

            if (players.size() == 1) {
                Player p = players.get(0);

                resultPanel.add(new GameLabel("    " + p.getName() + ": ", 250, 25));
                resultPanel.add(new GameLabel(p.getPoints() + " " + language.getMessage("points"), fwidth - 250 - 10, 25));
            } else if (players.size() == 2) {
                Player p1 = players.get(0);
                Player p2 = players.get(1);

                resultPanel.add(new GameLabel("    " + p1.getName() + ": ", 250, 25));
                resultPanel.add(new GameLabel(p1.getPoints() + " " + language.getMessage("points"), fwidth - 250 - 10, 25));

                resultPanel.add(new GameLabel("    " + p2.getName() + ": ", 250, 25));
                resultPanel.add(new GameLabel(p2.getPoints() + " " + language.getMessage("points"), fwidth - 250 - 10, 25));

                resultPanel.add(new GameLabel(" ", fwidth));

                Player winner = (p1.getPoints() > p2.getPoints()) ? p1 : p2;
                resultPanel.add(new GameLabel("    " + language.getMessage("roundWinner") + ": ", 250, 25));
                resultPanel.add(new GameLabel(winner.getName(), fwidth - 250 - 10, 25));
            }

            topTitle.setText(language.getMessage("roundEnded"));
            goToMenu.setText(language.getMessage("goToMenuButton"));
            goToStats.setText(language.getMessage("statsButton"));
            resultLabel.setText(language.getMessage("results") + ":");

        }
    }
}

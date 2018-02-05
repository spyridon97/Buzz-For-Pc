package userInterface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import game.Round;
import gameModes.GameMode;
import internationalization.Language;
import questions.Question;
import questions.Questions;
import stats.Stats;

/**
 * @author Manolis, Spiros
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = -7305041585921460642L;

    public static final String MENU = "menu", SETTINGS = "settings", HIGHSCORE = "highscore", PLAY = "gamemmode", ENDGAME = "end";

    final int width = 800, height = 600;

    private JPanel mainPanel;
    private CardLayout cardLayout = new CardLayout();
    private GameModePanel gmPanel;

    private Round round;
    private Stats stats;
    private Language language;

    /**
     * the starting point of the Application and GUI
     */
    public MainFrame() {
        language = new Language();

        Questions questions = new Questions(language);
        GameMode.updateGameModeNames(language);

        round = new Round(questions, null);
        stats = new Stats(language);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        setupComponents();
    }

    /**
     * Initialization of all Components used in the MainFrame
     */
    private void setupComponents() {
        this.setTitle("Buzz");

        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int x = (d.width - width) / 2;
        int y = (d.height - height) / 2;
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new Dimension(width, height));

        mainPanel.add(new MenuPanel(this), MENU);
        mainPanel.add(new SettingsPanel(this), SETTINGS);
        mainPanel.add(new HighscorePanel(this), HIGHSCORE);
        gmPanel = new GameModePanel(this);
        mainPanel.add(gmPanel, PLAY);
        mainPanel.add(new GameEndPanel(this), ENDGAME);

        this.add(mainPanel);

        this.setVisible(true);
        this.pack();
    }

    /**
     * @return the instance of the current round
     */
    public Round getRound() {
        return round;
    }

    /**
     * @return the Statistics of the Game
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * @return the current instance of the selected language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * changes the shown panel
     *
     * @param layer the layer that must be shown
     */
    public void setLayer(String layer) {
        cardLayout.show(mainPanel, layer);
    }

    /**
     * @return the Game Mode panel
     */
    public GameModePanel getGameModePanel() {
        return gmPanel;
    }

}

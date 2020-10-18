package userInterface;

import gameModes.GameMode;
import internationalization.Language;
import userInterface.components.GameButton;
import userInterface.components.GameLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Manolis, Spiros
 */
public class MenuPanel extends JPanel {

    private static final long serialVersionUID = -6174568492321091663L;

    private final MainFrame main;

    /**
     * @param main The MainFrame instance of the application
     */
    public MenuPanel(MainFrame main) {
        this.main = main;

        setupComponents();
    }

    /**
     * Initialization of all Components used in this Panel Complex Swing code to
     * achieve all needed relations and event handling of the components in this
     * panel. The text is initialized to Greek by default
     */
    private void setupComponents() {
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        GameLabel nameLabel = new GameLabel("Buzz!");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setPreferredSize(new Dimension(800, 330));
        nameLabel.setFont(nameLabel.getFont().deriveFont(50.0f));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttons.setPreferredSize(new Dimension(200, 180));

        GameButton start = new GameButton(main.getLanguage().getMessage("start"), 200, 60);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLayer(MainFrame.SETTINGS);
            }
        });

        buttons.add(start);

        GameButton stats = new GameButton(main.getLanguage().getMessage("statsButton"), 200, 60);

        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLayer(MainFrame.HIGHSCORE);
            }
        });

        buttons.add(stats);

        GameButton exit = new GameButton(main.getLanguage().getMessage("exit"), 200, 60);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttons.add(exit);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        top.setPreferredSize(new Dimension(main.width, 50));
        top.setBackground(Color.yellow);

        GameButton greek = new GameButton(new ImageIcon("flag_en.png"));
        greek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Language language = main.getLanguage();

                language.setToEnglish();
                GameMode.updateGameModeNames(language);

                start.setText(main.getLanguage().getMessage("start"));
                stats.setText(main.getLanguage().getMessage("statsButton"));
                exit.setText(main.getLanguage().getMessage("exit"));
            }
        });
        top.add(greek);

        GameButton english = new GameButton(new ImageIcon("flag_gr.png"));
        english.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Language language = main.getLanguage();

                language.setToGreek();
                GameMode.updateGameModeNames(language);

                start.setText(main.getLanguage().getMessage("start"));
                stats.setText(main.getLanguage().getMessage("statsButton"));
                exit.setText(main.getLanguage().getMessage("exit"));
            }
        });
        top.add(english);

        this.add(top);
        this.add(nameLabel);
        this.add(buttons);
    }

}

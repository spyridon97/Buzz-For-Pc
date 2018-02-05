package userInterface.components;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

/**
 *
 * @author Manolis,Spiros
 */
public class GameSplitPane extends JSplitPane {

    private static final long serialVersionUID = 1866706676384236884L;

    /**
     *
     * @param splitPercent the percent to split the components 0-100
     * @param orientation GameSplitPane.HORIZONTAL or GameSplitPane.VERTICAL
     * @param comp1 the left component of the split
     * @param comp2 the right component of the split
     */
    public GameSplitPane(int splitPercent, int orientation, JComponent comp1, JComponent comp2) {
        super(orientation, comp1, comp2);

        this.setDividerSize(0);
        this.setResizeWeight(splitPercent / 100.0);
    }

}

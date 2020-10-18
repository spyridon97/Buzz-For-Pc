package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Manolis, Spiros
 */
public class Language {

    private final Locale greekLocale = new Locale("el", "GR");
    private final Locale englishLocale = new Locale("en", "US");

    private ResourceBundle messages;

    /**
     * default constructor to Greek
     */
    public Language() {
        setToEnglish();
    }

    /**
     * sets the language to Greek
     */
    public void setToGreek() {
        this.messages = ResourceBundle.getBundle("internationalization.MessagesBundle", greekLocale);
    }

    /**
     * sets the language to English
     */
    public void setToEnglish() {
        this.messages = ResourceBundle.getBundle("internationalization.MessagesBundle", englishLocale);
    }

    /**
     * @param message is the en_US message that you want to get back based on
     *                the currentLocale
     * @return the message that you want to get back based on the currentLocale
     */
    public String getMessage(String message) {
        return this.messages.getString(message);
    }

}

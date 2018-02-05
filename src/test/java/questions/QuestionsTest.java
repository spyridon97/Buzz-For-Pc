/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questions;

import internationalization.Language;
import questions.Questions;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manolis, Spiros
 */
public class QuestionsTest {
    
    public QuestionsTest() {
    }
    
    /**
     * Test of getQuestion method, of class Questions.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");
        Language language = new Language();
        Questions questions = new Questions(language);
        
        if (!questions.getQuestion(1).getQuestion().equals("Ποια είναι η μεγαλύτερη σε έκταση ήπειρος;")) {
            fail("Questions.getQuestion doesn't return correct question or file isn't read correctly!");
        }
    }

    /**
     * Test of getTotalQuestions method, of class Questions.
     */
    @Test
    public void testGetTotalQuestions() {
        System.out.println("getTotalQuestions");
        Language language = new Language();
        Questions questions = new Questions(language);
        
        if (questions.getTotalQuestions() != 19) {
            fail("Questions.getTotalQuestions doesn't return correct number of questions or file isn't read correctly!");
        }
    }
    
}

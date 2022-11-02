/**
 * Frequently Asked Question
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
public class FAQ {
    String question; 
    String answer;
    /**
     * Parameterized constructor
     * @param question a String of the question
     * @param answer a String of the answer
     */
    public FAQ(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    /**
     * return the question + the answer
     */
    public String toString(){
        return this.question + "\n\n" +this.answer + "\n";
    }
}

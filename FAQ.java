/**
 * Frequently Asked Question
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
public class FAQ {
    String question; 
    String answer;
    public FAQ(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    public String toString(){
        return this.question + "\n\n" +this.answer + "\n";
    }
}

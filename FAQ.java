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

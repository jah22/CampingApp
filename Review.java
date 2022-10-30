/**
 * A Review class containing a String author, an int rating, a String body, a String title
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public class Review {
    private String author; 
    private int rating;
    private String body;
    private String title;

    /**
     * Parameterized constructor
     * @param author the author of the review
     * @param rating the rating of the review
     * @param title the title of the review
     * @param body the body of the review
     */
    public Review(String author, int rating,String title, String body){
        this.author = author;
        this.rating = rating;
        this.body = body;
        this.title = title;
    }

    /**
     * to get the title
     * @return the title of the review
     */
    public String getTitle(){
        return this.title;
    }
    /**
     * to get the rating
     * @return the rating of the review
     */
    public int getRating(){
        return this.rating;
    }
    /**
     * to get the body
     * @return the body of the review
     */
    public String getbody(){
        return this.body;
    }
    /**
     * to get the author
     * @return the author of the review
     */
    public String getAuthor(){
        return this.author;
    }
    /**
     * print out the review info
     */
    public String toString(){
        String out = "Review: " + this.title + "\n";
        out += "Author: " + this.author + "\n";
        out += "Body: \n" + this.body + "\n";
        return out;
    }
}

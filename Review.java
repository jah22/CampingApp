public class Review {
    private String author; 
    private int rating;
    private String text;

    public Review(String author, int rating, String text){
        this.author = author;
        this.rating = rating;
        this.text = text;
    }

    public int getRating(){
        return this.rating;
    }
    public String getText(){
        return this.text;
    }
    public String getAuthor(){
        return this.author;
    }
    public String toString(){
        // to do
        return "";
    }
}

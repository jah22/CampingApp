public class Review {
    private String author; 
    private int rating;
    private String body;
    private String title;

    public Review(String author, int rating,String title, String body){
        this.author = author;
        this.rating = rating;
        this.body = body;
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public int getRating(){
        return this.rating;
    }
    public String getbody(){
        return this.body;
    }
    public String getAuthor(){
        return this.author;
    }
    public String toString(){
        String out = "Review: " + this.title + "\n";
        out += "Author: " + this.author + "\n";
        out += "Body: \n" + this.body + "\n";
        return out;
    }
}

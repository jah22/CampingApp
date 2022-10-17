import java.util.ArrayList;

public class ReviewManager {
    private ArrayList<Review> reviews = new ArrayList<Review>();

    public ReviewManager(ArrayList<Review> reviews){
        this.reviews = reviews;
    }

    public void seeAvgReview(){
        // to do
    }
    public void seeReviewsByAuthor(String author){
        // to do
    }
    public void seeReviewsByRating(int rating){
        // to do
    }
    public void addReview(String author, int rating, String text){
        // to do
    }
    public boolean writeReview(String author, String text, int rating){
        // to do
        return false;
    }
    public boolean removeReview(String author, String text, int rating){
        // to do
        return false;
    }
}

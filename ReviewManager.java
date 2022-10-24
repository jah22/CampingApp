import java.util.ArrayList;

public class ReviewManager {
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private double avgReview = 0.0;

    public ReviewManager(ArrayList<Review> reviews){
        this.reviews = reviews;
    }
    public ReviewManager(){
        // param ctor
    }
    public void setReviews(ArrayList<Review> revs){
        this.reviews = revs;
    }
    public Review getReviewByTitle(String title){
        for(Review r:this.reviews){
            if(r.getTitle().equals(title)){
                return r;
            }
        }
        return null;
    }

    public void seeAvgReview(){
        if(this.avgReview == 0){
            System.out.println("No reviews yet");
            return;
        }
        System.out.println("Average rating: " + this.avgReview);
    }
    public void seeReviewsByAuthor(String author){
        /*
         * Author is first name last name
         */
        int reviewCount = 0;
        for(Review r : this.reviews){
            if(r.getAuthor().equals(author)){
                System.out.println(r);
                reviewCount += 1;
            }
        }
        System.out.println("Total reviews by " + author + ": " + reviewCount + "\n");
    }
    public void seeReviewsByRating(int rating){
        int reviewCount = 0;
        for(Review r: this.reviews){
            if(r.getRating() == rating){
                System.out.println(r);
                reviewCount += 1;
            }
        }
        System.out.println("Total reviews with rating of " + rating + ": " + reviewCount +"\n");
    }
    public void seeReviewsByTitle(String title){
        for(Review r: this.reviews){
            if(r.getTitle().equals(title)){
                System.out.println(r);
            }
        }
    }
    public void addReview(String author, int rating, String title, String text){
        // update the avg
        // avg = sum of all entries / number of entries
        // to update we first multiple by old number of entries
        double newAvg = this.avgReview * this.reviews.size();
        // then add the new value
        newAvg += rating;
        // then divide by new size
        newAvg /= this.reviews.size() + 1;
        // add review
        this.reviews.add(new Review(author, rating, title,text));
        // update avg
        this.avgReview = newAvg;
    }
    public boolean removeReview(String title, Person yourSelf){
        // need to check username password combo
        // to do
        Review rev = this.getReviewByTitle(title);
        if(rev == null){
            return false;
        }
        if(rev.getAuthor() != yourSelf.getFullName()){
            return false;
        }
        // else you have the review
        this.reviews.remove(rev);
        return true;
    }
}

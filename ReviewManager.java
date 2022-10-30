import java.util.ArrayList;
/**
 * A ReviewManager class that contains an ArrayList<Review> review and a double aveReview
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public class ReviewManager {
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private double avgReview = 0.0;

    /**
     * Parameterized constructor
     * @param reviews a new review
     */
    public ReviewManager(ArrayList<Review> reviews){
        this.reviews = reviews;
        updateAvgReview(reviews);
    }
    public ReviewManager(){
        // param ctor
    }
    /**
     * set the ArrayList reviews
     * @param revs an ArrayList of review
     */
    public void setReviews(ArrayList<Review> revs){
        this.reviews = revs;
        updateAvgReview(revs);
    }
    /**
     * update the Average Review
     * @param revs an ArrayList of review
     */
    public void updateAvgReview(ArrayList<Review> revs){
        // add to avg
        for(Review rev: revs){
            this.avgReview += rev.getRating();
        }
        this.avgReview = (this.avgReview/revs.size());
    }
    /**
     * Find a review by title
     * @param title the title of the review
     * @return the review that matches the title
     */
    public Review getReviewByTitle(String title){
        for(Review r:this.reviews){
            if(r.getTitle().equals(title)){
                return r;
            }
        }
        return null;
    }
    /**
     * get the average rating
     * @return the average rating
     */
    public double getAvgRating(){
        return this.avgReview;
    }
    /**
     * print out the average rating
     */
    public void viewAvgRating(){
        if(this.avgReview == 0){
            System.out.println("No reviews yet");
            return;
        }
        System.out.println("Average rating: " + this.avgReview);
    }
    /**
     * Find and print all reviews of an author
     * @param author an author
     */
    public void viewReviewsByAuthor(String author){
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
    /**
     * Find and print all reviews of a rating
     * @param rating a rating
     */
    public void viewReviewsByRating(int rating){
        int reviewCount = 0;
        System.out.println("SEARCHING FOR REVIEWS...\n");
        for(Review r: this.reviews){
            if(r.getRating() == rating){
                System.out.println(r);
                reviewCount += 1;
            }
        }
        System.out.println("Total reviews with rating of " + rating + ": " + reviewCount +"\n");
    }
    /**
     * Find and print all reviews of a title
     * @param title a title
     */
    public void viewReviewsByTitle(String title){
        for(Review r: this.reviews){
            if(r.getTitle().equals(title)){
                System.out.println(r);
            }
        }
    }
    /**
     * add a review and update the average review
     * @param author an authorof the review
     * @param rating a rating of the review
     * @param title a title of the review
     * @param text a body of the review
     */
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
    /**
     * remove a review and update the average review
     * @param title a title of the review
     * @param yourSelf an authorized user
     * @return true if successful removed the review
     */
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
    /**
     * print out all reviews
     */
    public void viewAllReviews(){
        if(this.reviews.size() == 0){
            System.out.println("No reviews present.");
            return;
        }
        for(Review r: this.reviews){
            System.out.println(r);
        }
    }
    public void save(){
        FileIO.writeReview(this.reviews);
    }
}
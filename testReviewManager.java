import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for review manager. 
 * COMPLETE
 */
public class testReviewManager {

    @BeforeClass
    public static void oneTimeSetup(){

    }
    @AfterClass
    public static void oneTimeTearDown(){

    }
    @BeforeEach
    public static void setup(){
        // runs before each test
    }
    @AfterEach
    public static void tearDown(){
        // runs after each test
    }
    @Test
    public void testUpdateAvgReviewNullReviews(){
        ReviewManager r = new ReviewManager(null);
        r.updateAvgReview(null);
        assertEquals(r.getAvgRating(),0);
    }
    @Test
    public void testAddReviewChangeAvg1(){
        // add review needs to properly change the average review count
        ReviewManager r = new ReviewManager(null);
        r.addReview("asdf", 1,"asdf","asdf");
        assertEquals(r.getAvgRating(),1);
    }
    @Test
    public void testAddReviewChangeAvg2(){
        // add review needs to properly change the average review count
        ReviewManager r = new ReviewManager(null);
        r.addReview("asdf", 1,"asdf","asdf");
        assertEquals(r.getAvgRating(),1);
    }
    @Test
    public void testAddReviewChangeAvg2(){
        // add review needs to properly change the average review count
        ReviewManager r = new ReviewManager(null);
        r.addReview("asdf", 1,"asdf","asdf");
        r.addReview("asdfisdf", 2,"asdf","asdf");
        assertEquals(r.getAvgRating(),1.5);
    }

    @Test
    public void  testSetReview() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        ReviewManager r = new ReviewManager(revs);
        ArrayList<Review> revs2 = new ArrayList<Review>();
        revs2.add(new Review("B", 5, "b?", "b!"));
        r.setReviews(revs2);
        assertEquals(r.getAvgRating(), 5);
    }
    @Test 
    public void testUpdateAveReview() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        ReviewManager r = new ReviewManager(revs);
        revs.add(new Review("B", 5, "b?", "b!"));
        r.updateAvgReview(revs);
        assertEquals(4.5, r.getAvgRating());
    }
    @Test
    public void testGetReviewByTitle() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        ReviewManager rm = new ReviewManager(revs);
        Review r = new Review("A", 4, "a?", "a!");
        assertEquals(r, rm.getReviewByTitle("a?"));
    }
    @Test
    public void testGetAveRating() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        revs.add(new Review("B", 5, "b?", "b!"));
        ReviewManager rm = new ReviewManager(revs);
        assertEquals(4.5, rm.getAvgRating());
    }
    @Test
    public void testAddReview() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        ReviewManager rm = new ReviewManager(revs);      
        rm.addReview("B", 5, "b?", "b!");
        assertEquals(4.5, rm.getAvgRating());
    }
    @Test
    public void testRemoveReview() {
        ArrayList<Review> revs = new ArrayList<Review>();
        revs.add(new Review("A", 4, "a?", "a!"));
        revs.add(new Review("B", 5, "b?", "b!"));
        ReviewManager rm = new ReviewManager(revs);      
        Person p =new Person("B", " ","1900-09-09" , "abc st", UUID.randomUUID());
        rm.removeReview("b?", p);
        assertEquals(4, rm.getAvgRating());
    }
}

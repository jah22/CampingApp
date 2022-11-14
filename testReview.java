import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Tests for Review
 * Complete
 */
public class testReview{

    @Test
    public void testNullAuthor(){
        assertEquals(new Review(null, 0,"adsf","asdf"), null);
    }
    @Test
    public void testNullTitle(){
        assertEquals(new Review("asdf", 0,null,"asdf"), null);
    }
    @Test
    public void testNulLBody(){
        assertEquals(new Review("asdf", 0,"asdf",null), null);
    }
    @Test
    public void testEmptyTitle(){
        assertEquals(new Review("asdf", 0,"","asdf"), null);
    }
    @Test
    public void testEmptyBody(){
        assertEquals(new Review("asdf", 0,"asdf",""), null);
    }
    @Test
    public void testNegativeReview(){
        assertEquals(new Review("asdf", -1,"asdf","asdf"), null);
    }
    @Test
    public void testOutBoundsReview(){
        assertEquals(new Review("asdf", 6,"asdf","asdf"), null);
    }
    
    @Test
    public void testGetTitle() {
        Review review = new Review("John", 5, "good", "very good");
        assertEquals("good",review.getTitle());
    }
    @Test
    public void testGetRating() {
        Review review = new Review("John", 5, "good", "very good");
        assertEquals(5, review.getRating());
    }
    @Test
    public void testGetBody() {
        Review review = new Review("John", 5, "good", "very good");
        assertEquals("very good", review.getbody());
    }
    @Test
    public void testGetAuthor() {
        Review review = new Review("John", 5, "good", "very good");
        assertEquals("John", review.getAuthor());
    }
    @Test
    public void testToString() {
        Review review = new Review("John", 5, "good", "very good");
        assertEquals("Review: good\nAuthor: John\nBody: \nvery good\n", review.toString());
    }
}

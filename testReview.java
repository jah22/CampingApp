import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testReview{

    @BeforeClass
    public void oneTimeSetup(){

    }
    @AfterClass
    public void oneTimeTearDown(){

    }
    @BeforeEach
    public void setup(){
        // runs before each test
    }
    @AfterEach
    public void tearDown(){
        // runs after each test
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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

public class testSchedule {

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
    public void testHasActivityToEmptySchedule() {
        Schedule sch = new Schedule(0, "one");
        assertEquals(sch.hasActivity("Monday", "a"),false);
    }
    @Test
    public void testHasActivityForNullActivity() {
        Schedule sch = new Schedule(0, "one");
        assertEquals(sch.hasActivity("Monday",null),false);
    }
    @Test
    public void testHasActivityForNullDate() {
        Schedule sch = new Schedule(0, "one");
        assertEquals(sch.hasActivity("nope", "a"),false);
    }
}

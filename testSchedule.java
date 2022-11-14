import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for schedules
 * COMPLETE
 */
public class testSchedule {
    @Test
    public void testBadSessionNumberCtor(){
        // no negatives
        assertEquals(new Schedule(-1,"asdf"),null);
    }
    @Test
    public void testEmptyCabinNameCtor(){
        // no negatives
        assertEquals(new Schedule(1,""),null);
    }
    @Test
    public void testNullCabinNameCtor(){
        // no negatives
        assertEquals(new Schedule(1,null),null);
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

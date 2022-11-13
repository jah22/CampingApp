import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

public class testActivityManager{

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
    public void testGetActivityStartTimeForInvalidActivity() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.getActivityStartTime("d"),-1);
    }
    @Test
    public void testGetActivityEndTimeForInvalidActivity() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.getActivityEndTime("d"),-1);
    }
    @Test
    public void testIsBeforeForInvalidAcvtivity1() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.isBefore("d", "a"),false);
    }
    @Test
    public void testIsBeforeForInvalidAcvtivity2() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.isBefore("a", "d"),false);
    }
    @Test
    public void testAddActvityWhenScheduleIsFull() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.addActivityToEnd("d"), false);
    }
    @Test
    public void testAddActvityWhenScheduleIsNotFull() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.addActivityToEnd("d"), true);
    }
    @Test
    public void testHasNullActivity() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.hasActivity(null), false);
    }
}

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for ActvityManager.
 * COMPLETE
 */

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
    public void testGetActivityStartTimeForDuplicateActivity(){
        // should only get the first activity
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.getActivityStartTime("a"),0);
    }
    @Test
    public void testGetActivityEndTimeForDuplicateActivity(){
        // should only get the first activity's end time
        // all activities are one hour
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.getActivityStartTime("a"),1);
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
    public void testIsBeforeSameActivity() {
        // should only check the first one
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("a");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.isBefore("a", "a"),false);
    }
    @Test
    public void testIsAfterSameActivity() {
        // should only check the first one
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("a");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.isAfter("a", "a"),false);
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
    public void testAddActivityWhenScheduleIsNotFull() {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("a");
        activities.add("b");
        activities.add("c");

        ActivityManager test = new ActivityManager(activities);
        assertEquals(test.addActivityToEnd("d"), true);
    }
    @Test
    public void testAddEmptyActivity(){
        ArrayList<String> activities = new ArrayList<>();
        activities.add("");
        ActivityManager test = new ActivityManager(activities);
        assertEquals(test,null);
    }
    @Test
    public void testAddNullActivity(){
        ArrayList<String> activities = new ArrayList<>();
        activities.add(null);
        ActivityManager test = new ActivityManager(activities);
        assertEquals(test,null);
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

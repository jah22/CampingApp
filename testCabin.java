import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Test;

public class testCabin{

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
    public void testGetCabinName() {
        Cabin c = new Cabin("Cabin name");
        assertEquals("Cabin name", c.getCabinName());
    }
    @Test 
    public void testHasDependent() {
        
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        campers.add(d);
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(true, c.hasDependent(d));
    }
    @Test
    public void testGetCoordinators() {
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        coordinators.add(d);
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(coordinators, c.getCoordinators());
    }
    @Test
    public void testGetCampers() {
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        campers.add(d);
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(campers, c.getCampers());
    }
    @Test
    public void testGetSchedules() {
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        Schedule schedule = new Schedule(7, "Cabin 1");
        schedules.add(schedule);
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(schedule, c.getSchedules());
    }
    @Test
    public void testGenerateRandomSchedule() {
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);

        int sessionNumber = 7;
        Schedule s = new Schedule(sessionNumber,"Cabin name");
        for(int i = 0 ; i < 7 ; i++){
            String day = "";
            if(i == 0){
                day = "Saturday" ;
                s.addDayActivityManager(day);
            }
            else if(i == 1){
                day = "Sunday";
                s.addDayActivityManager(day);
            }
            else if(i == 2){
                day = "Monday";
                s.addDayActivityManager(day);
            }
            else if(i == 3){
                day = "Tuesday";
                s.addDayActivityManager(day);
            }
            else if(i == 4){
                day = "Wednesday";
                s.addDayActivityManager(day);
            }
            else if(i == 5){
                day = "Thursday";
                s.addDayActivityManager(day);
            }
            else if(i == 6){
                day = "Friday";
                s.addDayActivityManager(day);
            }
            for(int j = s.getStartTime(); j <= s.getEndTime();j++){
                // some preset times
                if(j == ActivityManager.START_TIME) {
                    // wake up always first
                    s.addActivity(day,Activity.WAKEUP__CABIN.toString());
                }
                else if(j == ActivityManager.BREAKFAST_TIME){
                    // breakfast after wake up
                    s.addActivity(day,Activity.BREAKFAST__CABIN.toString());
                }
                else if(j == ActivityManager.LUNCH_TIME){
                    s.addActivity(day,Activity.LUNCH__CABIN.toString());
                }
                else if(j == ActivityManager.DINNER_TIME){
                    s.addActivity(day,Activity.DINNER__CABIN.toString());
                }
                else{
                    // randomize
                    String randActivity= Activity.values()[new Random().nextInt(Activity.values().length)].toString();
                    while(s.hasActivity(day,randActivity)){
                        randActivity = Activity.values()[new Random().nextInt(Activity.values().length)].toString();
                    }
                    s.addActivity(day,randActivity);
                }
            }
        }
        assertEquals(s, c.generateRandomSchedule(sessionNumber));
    }
    @Test
    public void testToString() {
        
    }
}

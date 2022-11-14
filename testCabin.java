import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Test;

import java.util.Random;

public class testCabin{

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
        assertEquals(schedules.toString(), c.getSchedules().toString());
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
        
        assertNotNull(c.generateRandomSchedule(sessionNumber));
    }
    @Test
    public void testToString() {
        
    }
}

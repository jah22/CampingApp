import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Test;

import java.util.Random;
import java.util.ArrayList;

/*
 * Tests for Cabin.java
 * COMPLETE
 */
public class testCabin{
    @Test
    public void testGetCabinName() {
        Cabin c = new Cabin("Cabin name");
        assertEquals("Cabin name", c.getCabinName());
    }
    @Test 
    public void testCreateNullNameCabin(){
        Cabin c = new Cabin(null);
        assertEquals(c, null);
    }
    @Test
    public void testCreateEmptyNameCabin(){
        Cabin c = new Cabin("");
        assertEquals(c, null);
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
    public void testHasGuardianDependentsNullGuardian(){
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
        assertEquals(c.hasGuardianDependents(null),false);
    }
    @Test
    public void testHasGuardianDependentsNoDependents(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        // Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        // campers.add(d);
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Guardian g = new Guardian("name","lastName","2011-11-11","address","password","username","email","phone");

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(c.hasGuardianDependents(g),false);
    }
    @Test
    public void testHasGuardianDependentsHasDependents(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        Guardian g = new Guardian("name","lastName","2011-11-11","address","password","username","email","phone");
        g.addDependent(d);
        campers.add(d);

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(c.hasGuardianDependents(g),true);
    }
    @Test
    public void testHasDependentVoidDependent(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        Guardian g = new Guardian("name","lastName","2011-11-11","address","password","username","email","phone");
        g.addDependent(d);
        campers.add(d);

        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(c.hasDependent(null),false);
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
    public void testAddNullCamperToCabin(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(c.addCamperToCabin(null),false);
    }
    @Test
    public void testAddCamperToCabinNoSpace(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        Dependent d1 = new Dependent("a", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d2 = new Dependent("b", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d3 = new Dependent("c", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d4 = new Dependent("d", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d5 = new Dependent("asdf", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d6 = new Dependent("asdfasdf", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d7 = new Dependent("asdfj", "Smith", "1990-09-09", "abc st",null, null);
        Dependent d8 = new Dependent("dJohn", "Smith", "1990-09-09", "abc st",null, null);
        c.addCamperToCabin(d1);
        c.addCamperToCabin(d2);
        c.addCamperToCabin(d3);
        c.addCamperToCabin(d4);
        c.addCamperToCabin(d5);
        c.addCamperToCabin(d6);
        c.addCamperToCabin(d7);
        c.addCamperToCabin(d8);
        Dependent d9 = new Dependent("asd", "Smith", "1990-09-09", "abc st",null, null);

        assertEquals(c.addCamperToCabin(d9),false);
    }
    @Test
    public void testAddSameCamperToCabin(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        Dependent d = new Dependent("John", "Smith", "1990-09-09", "abc st",null, null);
        c.addCamperToCabin(d);

        assertEquals(c.addCamperToCabin(d),false);
    }
    @Test
    public void testInCabinNullCamper(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        assertEquals(c.inCabin(null),false);
    }
    @Test
    public void testInAgeRangeNegativeRange(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        c.setLowerAgeBound(1);
        c.setUpperAgeBound(2);
        assertEquals(c.inAgeRange(-1),false);
    }
    @Test
    public void testCanAddDependentNullDependent(){
        String name = "Cabin name";
        ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
        ArrayList<Dependent> campers = new ArrayList<Dependent>();
        ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
        int camperCapacity = 8;
        int coordinatorCapacity = 1;
        int lowerAgeBound = 0;
        int upperAgeBound = 0;
        Cabin c = new Cabin(name,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound, upperAgeBound);
        c.setLowerAgeBound(1);
        c.setUpperAgeBound(2);
        assertEquals(c.checkCanAddDependent(null),false);
    }
}

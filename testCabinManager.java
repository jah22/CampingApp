import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import org.junit.Test;

/*
 * Tests for CabinManager
 * COMPLETION
 */
public class testCabinManager{

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
    public void testNullCtor(){
        CabinManager c = new CabinManager(null);
        assertEquals(c, null);
    }
    @Test
    public void testGetNegativeCabinIndex(){
        CabinManager c = new CabinManager(null);
        assertEquals(c.getCabinByIndex(-1), null);
    }
    @Test
    public void testGetOverBoundsCabinIndex(){
        CabinManager c = new CabinManager(null);
        assertEquals(c.getCabinByIndex(1), null);
    }
    @Test
    public void testGetInboundsCabinIndex(){
        Cabin c = new Cabin("Cabin");
        CabinManager cm = new CabinManager();
        cm.addCabin(c);
        assertEquals(cm.getCabinByIndex(0),c);
    }
    @Test
    public void testCheckCabinsForValidDependent(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Dependent d = new Dependent("first","last","2011-11-11","address",new ArrayList<String>(),new ArrayList<EmergencyContact>());
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ArrayList<Dependent> ds = new ArrayList<>();
        ds.add(d);
        g.setRegisteredDependents(ds);
        c.addCamperToCabin(d);
        CabinManager cm = new CabinManager();
        cm.addCabin(c);
        assertEquals(cm.checkCabinsForDependents(g),true);
    }
    @Test
    public void testCheckCabinsForInvalidDependent(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        CabinManager cm = new CabinManager();
        cm.addCabin(c);
        assertEquals(cm.checkCabinsForDependents(g),false);
    }
    @Test
    public void testCheckCabinsForNullGuardian(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        CabinManager cm = new CabinManager();
        cm.addCabin(c);
        assertEquals(cm.checkCabinsForDependents(null),false);
    }
    @Test
    public void testGuardianHasCampersRegisteredNoCamper(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        CabinManager cm = new CabinManager();
        cm.addCabin(c);
        assertEquals(cm.guardianHasCampersRegistered(g),false);
    }
    @Test
    public void testGuardianHasCampersRegisteredHasCamper(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        Dependent d = new Dependent("first","last","2011-11-11","address",new ArrayList<String>(),new ArrayList<EmergencyContact>());
        ArrayList<Dependent> deps = new ArrayList<Dependent>();
        deps.add(d);
        g.setRegisteredDependents(deps);
        c.addCamperToCabin(d);

        CabinManager cm = new CabinManager();
        cm.addCabin(c);

        assertEquals(cm.guardianHasCampersRegistered(g),true);
    }
    @Test
    public void testGuardianHasCampersRegisteredNullGuardian(){
        Cabin c = new Cabin("Cabin",10,30,4);
        CabinManager cm = new CabinManager();
        cm.addCabin(c);

        assertEquals(cm.guardianHasCampersRegistered(null),false);
    }
    @Test
    public void testGuardianHasCampersRegisteredNullCabin(){
        Cabin c = new Cabin("Cabin",10,30,4);
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        CabinManager cm = new CabinManager();
        cm.addCabin(c);

        assertEquals(cm.guardianHasCampersRegistered(null),false);
    }
    @Test 
    public void testAddNullCamperToCabin(){
        Cabin c = new Cabin("Cabin",10,30,4);
        assertEquals(c.addCamperToCabin(null),false);
    }
    @Test
    public void testGetCabinsByCoordinatorNullCoordinator(){
        CabinManager cm = new CabinManager();
        assertEquals(cm.getCabinsByCoordinator(null),false);
    }
    @Test
    public void testGetDependentCabinsNullDependent(){
        CabinManager cm = new CabinManager();
        assertEquals(cm.getDependentCabins(null), null);
    }
    @Test
    public void testGetDependentCabinsValidDependent(){
        CabinManager cm = new CabinManager();
        Cabin c = new Cabin("Cabin",10,30,4);
        Dependent d = new Dependent(null, null, null, null, null, null);
        c.addCamperToCabin(d);
        cm.addCabin(c);
        assertNotEquals(cm.getDependentCabins(d),null);
    }
    @Test
    public void testGetCabinCountByDependentNullDependent(){
        CabinManager cm = new CabinManager();
        assertEquals(cm.getCabinCountByDependent(null),0);
    }
}

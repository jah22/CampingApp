import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

public class testPersonManager{

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
    public void testNullAdmins(){
        PersonManager p = new PersonManager(null,new ArrayList<Guardian>(),new ArrayList<Dependent>(),new ArrayList<EmergencyContact>());
        assertEquals(p, null);
    }
    @Test
    public void testNullGuardians(){
        PersonManager p = new PersonManager(new ArrayList<CampAdmin>(),new ArrayList<Guardian>(),null,new ArrayList<EmergencyContact>());
        assertEquals(p, null);
    }
}

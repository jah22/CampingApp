import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for PriortyBehavior
 * COMPLETE
 */
public class testPriorityBehavior{

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
    public void testNullUsername(){
        PriorityBehavior pb = new PriorityBehavior(null,"Anything","111-222-3333","j@gmail.com");
        assertEquals(pb,null);
    }
    @Test
    public void testEmptyUsername(){
        PriorityBehavior pb = new PriorityBehavior("","Anything","111-222-3333","j@gmail.com");
        assertEquals(pb,null);
    }
    @Test
    public void testNullPassword(){
        PriorityBehavior pb = new PriorityBehavior("Anything",null,"111-222-3333","j@gmail.com");
        assertEquals(pb,null);
    }
    @Test
    public void testEmptyPassword(){
        PriorityBehavior pb = new PriorityBehavior("Anything","","111-222-3333","j@gmail.com");
        assertEquals(pb,null);
    }
    @Test
    public void testInvalidPhone(){
        PriorityBehavior pb = new PriorityBehavior("Anything","","asdf","j@gmail.com");
        assertEquals(pb,null);
    }
    @Test
    public void testInvalidEmail(){
        PriorityBehavior pb = new PriorityBehavior("Anything","","111-222-3333","asdfae");
        assertEquals(pb,null);
    }
    @Test
    public void testLoginValidCreds(){
        PriorityBehavior pb = new PriorityBehavior("username","password","111-222-3333","j@gmail.com");
        assertEquals(pb.login("username","password"),true);
    }
    @Test
    public void testLoginInValidCreds(){
        PriorityBehavior pb = new PriorityBehavior("username","password","111-222-3333","j@gmail.com");
        assertEquals(pb.login("user","password"),true);
    }
}

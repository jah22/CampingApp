import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for NoPriorityBehavior
 * COMPLETE
 */
public class testNoPriorityBehavior{

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
    public void testLogin(){
        NoPriorityBehavior np = new NoPriorityBehavior();
        assertEquals(np.login("Anything","Anything"),false);
    }
    @Test
    public void testNullPasswordLogin(){
        NoPriorityBehavior np = new NoPriorityBehavior();
        assertEquals(np.login("Anything",null),false);
    }
    @Test
    public void testNullUsernameLogin(){
        NoPriorityBehavior np = new NoPriorityBehavior();
        assertEquals(np.login(null,"Anything"),false);
    }
}

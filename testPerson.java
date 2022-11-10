import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for Person
 * COMPLETE
 */
public class testPerson {

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
    public void testToString(){
        Person p = new Person("Peter","Paul","2001-11-30","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        assertEquals(p.toString(), "Peter Paul");
    }
    @Test
    public void testGetAgeInt(){
        Person p = new Person("Peter","Paul","2001-10-30","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        assertEquals(p.getAgeInt(), 21);
    }
    @Test
    public void checkFullName(){
        Person p = new Person("Peter","Paul","2001-10-30","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        assertEquals(p.checkFullName("Peter","Paul"),true);
    }

    @Test
    public void testNullFirstName(){
        Person p = new Person(null,"Paul","2001-10-30","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        // should not create a user
        assertEquals(p,null);
    }
    @Test
    public void testNullLastName(){
        Person p = new Person("Peter",null,"2001-10-30","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        // should not create a user
        assertEquals(p,null);
    }
    @Test
    public void testNullBirthdate(){
        Person p = new Person("Peter","Paul",null,"Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        // should not create a user
        assertEquals(p,null);
    }
    @Test
    public void testBadBirthdateFormat(){
        Person p = new Person("Peter","Paul","11-22-2001","Address","24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        // should not create a user
        assertEquals(p,null);
    }
    @Test
    public void testNullAddress(){
        Person p = new Person("Peter","Paul","2001-10-10",null,"24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        // should not create a user
        assertEquals(p,null);
    }
    @Test
    public void testInvalidId(){
        Person p = new Person("Peter","Paul","2001-10-10","address","id");
        // should not create a user
        assertEquals(p,null);
    }
}

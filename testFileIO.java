import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Console;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

public class testFileIO{

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
    public void testSingleton() {
        FileIO firstInstance = FileIO.getInstance();
        FileIO secondInstance = FileIO.getInstance();
        assertEquals(firstInstance, secondInstance);
    }
    @Test
    public void testwriteToTxtFile() {
        ///Users/jacob/Documents/CampingApp/testing.txt
        FileIO.writeToTxtFile("test", "{}/testing.txt");
        System.out.print("OK!");
        }
}

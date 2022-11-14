import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for FAQ
 * No business logic, just holds data.
 * COMPLETE
 */
public class testFAQ{
    @Test
    public void testNullQuestion(){
        assertEquals(new FAQ(null,"asdf"), null);
    }
    @Test
    public void testNullAnswer(){
        assertEquals(new FAQ("asdf",null), null);
    }

    @Test
    public void testEmptyQuestion(){
        assertEquals(new FAQ(null,""), null);
    }
    @Test
    public void testEmptyAnswer(){
        assertEquals(new FAQ("",null), null);
    }
}

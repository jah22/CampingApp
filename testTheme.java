import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * The theme class only contains data, so no tests.
 * COMPLETE
 */
public class testTheme {
    @Test
    public void testThemeToString() {
        Theme t = new Theme("testing", 1, "this is a test");
        String testString = t.toString();
        assertEquals("Week: 1 -- Theme: testing\nDescription: this is a test", testString);
    }
}

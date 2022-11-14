import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.Test;

/*
 * Tests for CampSiteManager.
 * As this class is a facade, it was said in class that there would
 * not be much testing here as all testing is in the respective classes whose methods
 * are called. 
 * Also since many functions are just for viewing and are thus void, tests
 * could not be made for those.
 * 
 * We tried our best to find testable things unique to the class.
 * 
 * COMPLETE
 */
public class testCampSiteManager{

    @Test
    public void testNullNameCtor(){
        CampSiteManager csm = CampSiteManager.getInstance(null,"asdf", 2001,"jan", null);
        assertEquals(csm, null);
    }

    @Test
    public void testEmptyNameCtor(){
        CampSiteManager csm = CampSiteManager.getInstance("","asdf", 2001,"jan", null);
        assertEquals(csm, null);
    }

    @Test
    public void testNullAddressCtor(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf",null, 2001,"jan", null);
        assertEquals(csm, null);
    }
    @Test
    public void testEmptyAddressCtor(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", 2001,"jan", null);
        assertEquals(csm, null);
    }
    @Test
    public void testNegativeYearCtor(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", -1,"jan", null);
        assertEquals(csm, null);
    }
    @Test
    public void testRegisterNullAdmin(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", 123,"jan", null);
        assertEquals(csm.registerAdmin(null),null);
    }
    public void testResetCampName(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", 123,"jan", null);
        csm.resetCamp();
        assertEquals(csm.getName(),"");
    }
    public void testResetCampYear(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", 123,"jan", null);
        csm.resetCamp();
        assertEquals(csm.getYear(),-1);
    }
    public void testResetCampAddress(){
        CampSiteManager csm = CampSiteManager.getInstance("asdf","", 123,"jan", null);
        csm.resetCamp();
        assertEquals(csm.getAddress(),"");
    }
}

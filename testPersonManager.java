import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

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
        PersonManager p = new PersonManager(new ArrayList<CampAdmin>(),null,new ArrayList<Dependent>(),new ArrayList<EmergencyContact>());
        assertEquals(p, null);
    }
    @Test
    public void testNullDependents(){
        PersonManager p = new PersonManager(new ArrayList<CampAdmin>(),new ArrayList<Guardian>(),null,new ArrayList<EmergencyContact>());
        assertEquals(p, null);
    }
    @Test
    public void testNullEmergencyContacts(){
        PersonManager p = new PersonManager(new ArrayList<CampAdmin>(),new ArrayList<Guardian>(),new ArrayList<Dependent>(),null);
        assertEquals(p, null);
    }
    @Test
    public void testGetDependentByValidName(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        gs.add(new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        UUID id = UUID.fromString("24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        assertEquals(p.getDependentByName(id,"first","last"),d);
    }
    @Test
    public void testGetDependentByInValidName(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        gs.add(new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        UUID id = UUID.fromString("24f72791-fa08-4dd6-980a-d9958f3a4fd0");
        assertEquals(p.getDependentByName(id,"last","last"),null);
    }
    @Test
    public void testGuardiantGetDependentByInt(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        assertEquals(p.guardianGetDependentByInt(g.getId(), 0), d);
    }
    @Test
    public void testGuardianGetNumberDependents(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        assertEquals(p.guardianGetNumberDependents(g.getId()), 1);
    }
    @Test
    public void testGuardianHasDependents(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        assertEquals(p.guardianHasDependents(g), true);
    }
    @Test
    public void testRemoveDependent(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        p.removeDependent(g, d);
        assertEquals(p.guardianHasDependents(g),false);
    }
    @Test
    public void testRemoveGuardian(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        p.removeGuardian(g.getId());
        assertEquals(p.getGuardianById(g.getId()),null);
    }
    @Test
    public void testRemoveCamper(){
        ArrayList<CampAdmin> as = new ArrayList<>();
        ArrayList<Guardian> gs= new ArrayList<>();
        ArrayList<Dependent> ds= new ArrayList<>();
        ArrayList<EmergencyContact> es= new ArrayList<>();
        as.add(new CampAdmin("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333"));
        Guardian g = new Guardian("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        gs.add(g);
        Dependent d = new Dependent("first","last","2011-11-11","address","password","username","j@gmail.com","111-222-3333");
        ds.add(d);
        g.setRegisteredDependents(ds);
        es.add(new EmergencyContact("first","last","2011-11-11","addresss","111-222-3333","relation"));
        PersonManager p = new PersonManager(as, gs, ds, es);
        p.removeCamper(d.getId());
        assertEquals(p.getDependentById(d.getId()),null);
    }
}

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.print.attribute.HashAttributeSet;
import javax.swing.JSpinner.DefaultEditor;

import org.json.simple.JSONArray;
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
    //written by Jacob Hammond

    @Test
    public void testSingleton() {
        FileIO firstInstance = FileIO.getInstance();
        FileIO secondInstance = FileIO.getInstance();
        assertEquals(firstInstance, secondInstance);
    }
    //written by Jacob Hammond
    @Test
    public void testparseJsonFileArrPath() {
        JSONArray expectParse = FileIO.fileIOTester.testParseJsonFileArr("./testdata/parseJsonFileTestData.json");
        assertEquals(expectParse.size(), 1);
    }
    //written by Jacob Hammond
    @Test
    public void testParseJsonFileArrFileNotFound() {
        //Throws FileNotFoundException and returns an empty array
        JSONArray expectParse = FileIO.fileIOTester.testParseJsonFileArr("./parseJsonFileTestData.json");
        assertEquals(expectParse.size(), 0);
    }
    //written by Jacob Hammond
    @Test
    public void testParseJsonFileArrBadFormat() {
        //Throws InvocationExceptionError, not caught by application
        JSONArray expectParse = FileIO.fileIOTester.testParseJsonFileArr("./testdata/failJsonFile.json");
        assertEquals(expectParse.size(), 0);
    }
    //written by Jacob Hammond
    @Test
    public void testWriteEMC() {
        EmergencyContact expectedContact = new EmergencyContact("John", "Doe", "01-01-1990", "123 Main St.", "830-555-5555", "Father", UUID.randomUUID());
        ArrayList<EmergencyContact> expectedList = new ArrayList<>();
        expectedList.add(expectedContact);
        FileIO.writeEmergencyContact(expectedList);
        ArrayList<EmergencyContact> actualList = FileIO.fileIOTester.testReadEmergencyContacts();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testWriteEMCContent() {
        boolean isContentValid = true;
        EmergencyContact testContact = new EmergencyContact("John", "Doe", "01-01-1990", "123 Main St.", "830-555-5555", "Father", UUID.randomUUID());
        ArrayList<EmergencyContact> expectedList = new ArrayList<>();
        expectedList.add(testContact);
        FileIO.writeEmergencyContact(expectedList);
        ArrayList<EmergencyContact> actualList = FileIO.fileIOTester.testReadEmergencyContacts();
        for(int i = 0; i < actualList.size(); i++) {
            EmergencyContact actualContact = actualList.get(i);
            EmergencyContact expectedContact = expectedList.get(i);
            if(!actualContact.isEqual(expectedContact)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testWriteCampContent() {
        boolean isContentValid = true;
        CampSiteManager testCamp = CampSiteManager.getInstance();
        FileIO.writeCamp(testCamp);
        CampSiteManager actualCamp = FileIO.fileIOTester.testReadCampSiteManager();
        if(!actualCamp.isEqual(testCamp)) {
            isContentValid = false; 
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testWriteAndReadTheme() {
        ArrayList<Theme> testThemeList = new ArrayList<>();
        Theme t = new Theme("test", 1, "this is a test theme");
        testThemeList.add(t);
        ArrayList<ThemeManager> expectedList = new ArrayList<>();
        ThemeManager testThemeManager = new ThemeManager(testThemeList);
        expectedList.add(testThemeManager);
        FileIO.writeTheme(expectedList);
        ArrayList<ThemeManager> actualList = FileIO.fileIOTester.testReadThemeManager();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testWriteThemeContent() {
        boolean isContentValid = true;
        ArrayList<Theme> testThemeList = new ArrayList<>();
        Theme t = new Theme("test", 1, "this is a test theme");
        testThemeList.add(t);
        ArrayList<ThemeManager> expectedList = new ArrayList<>();
        ThemeManager testThemeManager = new ThemeManager(testThemeList);
        expectedList.add(testThemeManager);
        FileIO.writeTheme(expectedList);
        ArrayList<ThemeManager> actualList = FileIO.fileIOTester.testReadThemeManager();
        for(int i = 0; i < actualList.size(); i++) {
            ThemeManager actualTheme = actualList.get(i);
            ThemeManager expectedTheme = expectedList.get(i);
            if(!actualTheme.IsEqual(expectedTheme)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testWriteAndReadSchedule() {
        ArrayList<Schedule> testScheduleList = new ArrayList<>();
        HashMap<String, ActivityManager> testMap = new HashMap<>();
        testMap.put("Monday", new ActivityManager());
        Schedule s = new Schedule(testMap, 1, UUID.randomUUID(), "test name");
        testScheduleList.add(s);
        ArrayList<Schedule> expectedList = new ArrayList<>();
        expectedList.add(s);
        FileIO.writeSchedule(testScheduleList);
        ArrayList<Schedule> actualList = FileIO.fileIOTester.testReadSchedules();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testWriteScheduleContent() {
        boolean isContentValid = true;
        ArrayList<Schedule> testScheduleList = new ArrayList<>();
        HashMap<String, ActivityManager> testMap = new HashMap<>();
        testMap.put("Monday", new ActivityManager());
        Schedule s = new Schedule(testMap, 1, UUID.randomUUID(), "test name");
        testScheduleList.add(s);
        ArrayList<Schedule> expectedList = new ArrayList<>();
        expectedList.add(s);
        FileIO.writeSchedule(testScheduleList);
        ArrayList<Schedule> actualList = FileIO.fileIOTester.testReadSchedules();
        for(int i = 0; i < actualList.size(); i++) {
            Schedule actualTheme = actualList.get(i);
            Schedule expectedTheme = expectedList.get(i);
            if(!actualTheme.isEqual(expectedTheme)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testWriteAndReadCabin() {
        ArrayList<Cabin> testCabinList = new ArrayList<>();

        ArrayList<Schedule> scheduleForCabin = new ArrayList<>();
        HashMap<String, ActivityManager> testMap = new HashMap<>();
        testMap.put("Monday", new ActivityManager());
        Schedule s = new Schedule(testMap, 1, UUID.randomUUID(), "test name");
        scheduleForCabin.add(s);

        ArrayList<Dependent> campersForCabin = new ArrayList<>();
        Dependent c = new Dependent("first", "last", "2007-11-11", "none", UUID.randomUUID(), false, null, null);
        campersForCabin.add(c);

        ArrayList<Dependent> coordinatorsForCabin = new ArrayList<>();
        Dependent coord = new Dependent("last", "first", "1997-11-11", "there", UUID.randomUUID(), true, null, null);
        coordinatorsForCabin.add(coord);

        Cabin cabinForTest = new Cabin("test name", coordinatorsForCabin, campersForCabin, scheduleForCabin, 1, 1, 0, 18);
        testCabinList.add(cabinForTest);

        ArrayList<Cabin> expectedList = new ArrayList<>();
        expectedList.add(cabinForTest);
        FileIO.writeCabin(testCabinList);
        ArrayList<Cabin> actualList = FileIO.fileIOTester.testReadCabins();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testWriteCabinContent() {
        boolean isContentValid = true;
        ArrayList<Cabin> testCabinList = new ArrayList<>();

        ArrayList<Schedule> scheduleForCabin = new ArrayList<>();
        HashMap<String, ActivityManager> testMap = new HashMap<>();
        testMap.put("Monday", new ActivityManager());
        Schedule s = new Schedule(testMap, 1, UUID.randomUUID(), "test name");
        scheduleForCabin.add(s);

        ArrayList<Dependent> campersForCabin = new ArrayList<>();
        Dependent c = new Dependent("first", "last", "2007-11-11", "none", UUID.randomUUID(), false, null, null);
        campersForCabin.add(c);

        ArrayList<Dependent> coordinatorsForCabin = new ArrayList<>();
        Dependent coord = new Dependent("last", "first", "1997-11-11", "there", UUID.randomUUID(), true, null, null);
        coordinatorsForCabin.add(coord);

        Cabin cabinForTest = new Cabin("test name", coordinatorsForCabin, campersForCabin, scheduleForCabin, 1, 1, 0, 18);
        testCabinList.add(cabinForTest);

        ArrayList<Cabin> expectedList = new ArrayList<>();
        expectedList.add(cabinForTest);
        FileIO.writeCabin(testCabinList);
        ArrayList<Cabin> actualList = FileIO.fileIOTester.testReadCabins();
        for(int i = 0; i < actualList.size(); i++) {
            Cabin actualTheme = actualList.get(i);
            Cabin expectedTheme = expectedList.get(i);
            if(!actualTheme.isEqual(expectedTheme)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testReadAndWriteDependent() {
        ArrayList<Dependent> testDependentList = new ArrayList<>();
        Dependent c = new Dependent("first", "last", "2007-11-11", "none", UUID.randomUUID(), false, null, null);
        testDependentList.add(c);
        Dependent coord = new Dependent("last", "first", "1997-11-11", "there", UUID.randomUUID(), true, null, null);
        testDependentList.add(coord);
        ArrayList<Dependent> expectedList = new ArrayList<>();
        expectedList.add(c);
        expectedList.add(coord);
        FileIO.writeDependent(testDependentList);
        ArrayList<Dependent> actualList = FileIO.fileIOTester.testReadDependents();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testWriteDependentContent() {
        boolean isContentValid = true;
        ArrayList<Dependent> testDependentList = new ArrayList<>();
        Dependent c = new Dependent("first", "last", "2007-11-11", "none", UUID.randomUUID(), false, null, null);
        testDependentList.add(c);
        Dependent coord = new Dependent("last", "first", "1997-11-11", "there", UUID.randomUUID(), true, null, null);
        testDependentList.add(coord);
        ArrayList<Dependent> expectedList = new ArrayList<>();
        expectedList.add(c);
        expectedList.add(coord);
        FileIO.writeDependent(testDependentList);
        ArrayList<Dependent> actualList = FileIO.fileIOTester.testReadDependents();
        for(int i = 0; i < actualList.size(); i++) {
            Dependent actualTheme = actualList.get(i);
            Dependent expectedTheme = expectedList.get(i);
            if(!actualTheme.isEqual(expectedTheme)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testReadAndWriteCampAdmin() {
        CampAdmin expectedAdmin = new CampAdmin("first", "last", "1990-10-10", "123 Main St.", "124", "admin", "admin@gmail.com", "830-555-5555");
        ArrayList<CampAdmin> expectedList = new ArrayList<>();
        expectedList.add(expectedAdmin);
        FileIO.writeCampAdmin(expectedList);
        ArrayList<CampAdmin> actualList = FileIO.fileIOTester.testReadCampAdmins();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testCampAdminContent() {
        boolean isContentValid = true;
        CampAdmin expectedAdmin = new CampAdmin("first", "last", "1990-10-10", "123 Main St.", "124", "admin", "admin@gmail.com", "830-555-5555");
        ArrayList<CampAdmin> expectedList = new ArrayList<>();
        expectedList.add(expectedAdmin);
        FileIO.writeCampAdmin(expectedList);
        ArrayList<CampAdmin> actualList = FileIO.fileIOTester.testReadCampAdmins();
        for(int i = 0; i < actualList.size(); i++) {
            CampAdmin actualContact = actualList.get(i);
            CampAdmin expectedContact = expectedList.get(i);
            if(!actualContact.isEqual(expectedContact)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentValid,true);
    }
    //written by Jacob Hammond
    @Test
    public void testReadAndWriteGuardian() {
        Guardian expectedGuardian = new Guardian("first", "last", "1990-10-10", "123 Main St.", "124", "admin", "admin@gmail.com", "830-555-5555");
        ArrayList<Guardian> expectedList = new ArrayList<>();
        expectedList.add(expectedGuardian);
        FileIO.writeGuardian(expectedList);
        ArrayList<Guardian> actualList = FileIO.fileIOTester.testReadGuardians();
        assertEquals(expectedList.size(),actualList.size());
    }
    //written by Jacob Hammond
    @Test
    public void testGuardianContent() {
        boolean isContentValid = true;
        Guardian expectedGuardian = new Guardian("first", "last", "1990-10-10", "123 Main St.", "124", "admin", "admin@gmail.com", "830-555-5555");
        ArrayList<Guardian> expectedList = new ArrayList<>();
        expectedList.add(expectedGuardian);
        FileIO.writeGuardian(expectedList);
        ArrayList<Guardian> actualList = FileIO.fileIOTester.testReadGuardians();
        for(int i = 0; i < actualList.size(); i++) {
            Guardian actualContact = actualList.get(i);
            Guardian expectedContact = expectedList.get(i);
            if(!actualContact.isEqual(expectedContact)) {
                isContentValid = false;
            }
        }
        assertEquals(isContentVa,true);
    }
}

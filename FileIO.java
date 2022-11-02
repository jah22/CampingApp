/**
 * FileIO Class
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

/**
 * This class handles getting, parsing, reading, and writing JSONFiles within the camp system.
 */

public class FileIO {
    /*
     * Arrays of class objects
     */
    private static ArrayList<Guardian> guardians;
    private static ArrayList<CampAdmin> admins;
    private static ArrayList<Cabin> cabins;
    private static ArrayList<FAQ> faqs;
    private static ArrayList<Review> reviews;
    private static ArrayList<Schedule> schedules;
    private static ArrayList<Dependent> dependents;
    private static ArrayList<EmergencyContact> emergencyContacts;
    private static CampSiteManager campSiteManager;
    private static ArrayList<ThemeManager> themeManagers;

    /*
     * Managers
     */
    private PersonManager pM;

    /**
     * singleton initializer
     */
    private static FileIO fileIO;
    /**
     * This functions populates the initalized ArrayLists with data.
     */
    private void populateData(){
        /*
         * ORDER MATTERS! DEPENDENCIES FIRST!
         */
        this.faqs = readFaqs();
        this.reviews = readReviews();
        this.themeManagers = readThemeManagers();

        // start dealing with people
        this.pM = new PersonManager();
        this.admins = readAdmins();
        this.pM.setAdmins(this.admins);
        this.emergencyContacts = readEmergencyContacts();
        this.pM.setEmergencyContacts(this.emergencyContacts);
        this.dependents = readDependents();
        this.pM.setDependents(this.dependents);

        // read guardians after dependents since guardians depend on them
        this.guardians = readGuardians();

        // read cabins and THEN schedules
        this.cabins = readCabins();
        this.schedules = readSchedules();

        this.campSiteManager = readCamp();
    }
    /**
     * Calls populateData in order to read from JSON files and populate ArrayLists
     */
    private FileIO(){
        populateData();
    }
    /**
     * Creates a new instance of FileIO as a singleton
     * @return one instance of FileIO
     */
    public static FileIO getInstance(){
        if(fileIO == null){
            fileIO = new FileIO();
            return fileIO;
        }
        return fileIO;
    }
    /*
     * **********************
     * JSON getters
     * **********************
     */
    /**
     * Main accessor for data in the JSON files
     * @return the ArrayList of objects of their respective types.
     */
    public static ArrayList<CampAdmin> getAdmins(){
        return admins;
    }
    public static ArrayList<Dependent> getDependents(){
        return dependents;
    }
    public static ArrayList<Guardian> getGuardians(){
        return guardians;
    }
    public static ArrayList<Review> getReviews(){
        return reviews;
    }
    public static ArrayList<FAQ> getFaqs(){
        return faqs;
    }
    public static ArrayList<Cabin> getCabins(){
        return cabins;
    }
    public static CampSiteManager getCamp(){
        return campSiteManager;
    }
    public static ArrayList<EmergencyContact> getEmergencyContacts(){
        return emergencyContacts;
    }
    public static ArrayList<Schedule> getSchedules(){
        return schedules;
    }
    public static ArrayList<ThemeManager> getThemes() {
        return themeManagers;
    }

    /*
     * ***************************
     * Json Parsers
     * ***************************
     */
    /**
     * Creates a new JSONObject that contains the information needed to create a Schedule
     * Each Schedule contains a UUID, cabinName, sessionNumber, and a HashMap of activities
     * @param jSchedule an empty JSONObject
     * @return jO, a JSONObject populated with Schedule information
     */
    private static Schedule parseScheduleObj(JSONObject jSchedule){
        UUID id = UUID.fromString((String)jSchedule.get(DataConstants.SCHEDULE_ID));
        String cabinName = (String) jSchedule.get(DataConstants.SCHEDULE_CABIN_NAME);
        int sessionNumber  = Math.toIntExact((long) jSchedule.get("sessionNumber"));
        JSONArray jSchedules = (JSONArray)jSchedule.get(DataConstants.SCHEDULE_SCHEDULES);
        // create the hash
        HashMap<String,ActivityManager> hash = new HashMap<String,ActivityManager>();


        jSchedules.forEach(jSubSchedule->{
            JSONObject jO = (JSONObject) jSubSchedule;
            // start an activitiy manager
            ActivityManager aM = new ActivityManager();
            // get day of week    
            String dayOfWeek = (String) jO.get(DataConstants.SCHEDULE_DAY_OF_WEEK);
            // get schedules
            JSONArray jsonArrScheds = (JSONArray) jO.get(DataConstants.SCHEDULE_SCHEDULE);
            jsonArrScheds.forEach(jsonActivity->{
                String activity = (String)  jsonActivity;
                aM.addActivityToEnd(activity);
            });
            hash.put(dayOfWeek,aM);
        });
        Schedule sched = new Schedule(hash, sessionNumber,id,cabinName);
        // add to cabin
        for(Cabin c: cabins){
            if(c.getCabinName().equals(cabinName)){
                c.addSchedule(sched);
            }
        }
        return sched;
    }
    /**
     * Creates a new JSONObject that contains the information needed to create an FAQ
     * @param jFaq an empty JSONObject
     * @return new FAQ, a JSONObject populated with FAQ information
     */
    private static FAQ parseFaqObj(JSONObject jFaq){
        String question = (String) jFaq.get(DataConstants.FAQ_QUESTION);
        String answer = (String) jFaq.get(DataConstants.FAQ_ANSWER);
        return new FAQ(question,answer);
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a new ThemeManager
     * A ThemeManager contains themes for the camp to be used week by week.
     * It also contains a UUID, an Array of Themes 
     * Each Theme has a name, week, and description
     * @param jTheme an empty JSONObject
     * @return tm, a JSONObject populated with ThemeManager information
     */
    private ThemeManager parseThemeManager(JSONObject jTheme) {
        ThemeManager tm = new ThemeManager();
        UUID id = UUID.fromString((String) jTheme.get("id"));
        tm.setId(id);
        JSONArray jaThemes = (JSONArray) jTheme.get("themes");
        jaThemes.forEach(objTheme->{
            JSONObject joTheme = (JSONObject)objTheme;
            String name = (String) joTheme.get("name");
            int week = Math.toIntExact((long)joTheme.get("week"));
            String description = (String) joTheme.get("description");
            tm.addTheme(new Theme(name, week,description));
        });
        return tm;
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a Review
     * A Review has an Author, Rating, Title, and Body
     * @param rev, an empty JSON Object
     * @return a new Review with the needed information as a parameter
     */
    private Review parseReviewObj(JSONObject rev){
        
        String author= (String) rev.get(DataConstants.REVIEW_AUTHOR);
        int rating= Math.toIntExact((Long)rev.get(DataConstants.REVIEW_RATING));
        String title= (String) rev.get(DataConstants.REVIEW_TITLE);
        String body= (String) rev.get(DataConstants.REVIEW_BODY);

        return new Review(author,rating,title,body);
    }
    /**
     * Creates a new JSONObject that contains the information needed to create an EmergencyContact
     * An EmergencyContact requires a last name, first name, address, UUID, birthdate, phone number, and relationship to the camper
     * @param jEM, an empty JSON Object
     * @return a new EmergencyContact with needed information parameterized.
     */
    private EmergencyContact parseEmergencyContactObj(JSONObject jEM){
        // get attributes
        String firstName = (String) jEM.get(DataConstants.PERSON_FIRST_NAME);
        String lastName = (String) jEM.get(DataConstants.PERSON_LAST_NAME);
        String address = (String) jEM.get(DataConstants.PERSON_ADDRESS);
        UUID id = UUID.fromString((String)jEM.get(DataConstants.PERSON_ID));
        String birthDate = (String) jEM.get(DataConstants.PERSON_BIRTHDATE);
        String phone = (String) jEM.get("phone");
        String relation = (String) jEM.get("relation");
        
        return (new EmergencyContact(firstName, lastName, birthDate, address,phone,relation,id));
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a Guardian
     * A Guardian requires a first name, last name, address, UUID, birthdate, password, username, email, phone number, and any registered dependents.
     * @param guardian, an empty JSON Object
     * @return a new Guardian with the needed information parameterized
     */
    private Guardian parseGuardianObj(JSONObject guardian){
        String firstName = (String) guardian.get(DataConstants.PERSON_FIRST_NAME);
        String lastName = (String) guardian.get(DataConstants.PERSON_LAST_NAME);
        String address = (String) guardian.get(DataConstants.PERSON_ADDRESS);
        UUID id = UUID.fromString((String)guardian.get(DataConstants.PERSON_ID));
        String birthDate = (String) guardian.get(DataConstants.PERSON_BIRTHDATE);

        String password = (String) guardian.get(DataConstants.GUARDIAN_PASSWORD);
        String username = (String) guardian.get(DataConstants.GUARDIAN_USERNAME);
        String email = (String) guardian.get(DataConstants.GUARDIAN_EMAIL);
        String phoneNumber = (String) guardian.get(DataConstants.GUARDIAN_PHONE_NUMBER);

        ArrayList<Dependent> deps = new ArrayList<Dependent>();

        JSONArray jaDependents = (JSONArray) guardian.get(DataConstants.GUARDIAN_REGISTERED_DEPENDENTS);
        jaDependents.forEach(jaDependent ->{
            JSONObject jDep = (JSONObject) jaDependent;
            UUID jDepId = UUID.fromString((String)jDep.get(DataConstants.GUARDIAN_ID));
            Dependent dep = pM.getDependentById(jDepId);
            if(dep != null){
                deps.add(dep);
            }
        });
        return new Guardian(firstName, lastName, birthDate, address, id, password, username, email, phoneNumber,deps);
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a Camper
     * A Camper is a Dependent which requires a first name, last name, address, UUID, birthdate, isCoordinator, emergency contacts,
     * and medical notes.
     * @param jsonDep, an empty JSON Object
     * @return a new Dependent with the needed information parameterized
     */
    private Dependent parseCamperObj(JSONObject jsonDep){
        // get attributes
        String firstName = (String) jsonDep.get(DataConstants.DEPENDENT_FIRST_NAME);
        String lastName = (String) jsonDep.get(DataConstants.DEPENDENT_LAST_NAME);
        String address = (String) jsonDep.get(DataConstants.DEPENDENT_ADDRESS);
        UUID id = UUID.fromString((String) jsonDep.get(DataConstants.DEPENDENT_ID));
        String birthDate = (String) jsonDep.get(DataConstants.DEPENDENT_BIRTH_DATE);
        boolean isCoordinator = (boolean) jsonDep.get(DataConstants.DEPENDENT_IS_COORDINATOR);

        ArrayList<EmergencyContact> emContacts = new ArrayList<EmergencyContact>();
        ArrayList<String> medNotes = new ArrayList<String>();

        // parse the contacts
        JSONArray jEmContacts = (JSONArray) jsonDep.get(DataConstants.DEPENDENT_EMERGENCY_CONTACTS);
        jEmContacts.forEach(jContact->{
            JSONObject jEm =(JSONObject) jContact;
            UUID jEmId= UUID.fromString((String) jEm.get(DataConstants.EMERGENCY_CONTACT_ID));
            EmergencyContact emContact = pM.getEmergencyContactById(jEmId);
            if(emContact != null){
                emContacts.add(emContact);
            }
        });

        // parse the medical notes
        JSONArray jMedNotes = (JSONArray) jsonDep.get(DataConstants.DEPENDENT_MEDICAL_NOTES);
        jMedNotes.forEach(
            jMedNote->{
                String [] split = ((String) jMedNote).split(",");
                ArrayList<String> notes = new ArrayList<String>(Arrays.asList(split));
                medNotes.addAll(notes);
            }
        );
        
        NoPriorityBehavior npB = new NoPriorityBehavior();
        
        return new Dependent(firstName, lastName, birthDate, address, id,isCoordinator,emContacts,medNotes,npB);
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a Coordinator
     * Coordinators are identical to Dependents with the exceptions of their AuthBehaviors and their boolean isCoordinator set to true
     * Coordinators have a username, email, phoneNumber, and password
     * @param jObject, an empty JSON Object
     * @return a Dependent object with the needed information for a Coordinator
     */
    private Dependent parseCoordinatorObj(JSONObject jObject){
        Dependent coordinator = parseCamperObj(jObject) ;

        // now read auth stuff
        String username = (String) jObject.get("username");
        String email = (String) jObject.get("email");
        String phone = (String) jObject.get("phoneNumber");
        String password = (String) jObject.get("password");

        PriorityBehavior pB = new PriorityBehavior(username,password,phone,email);
        coordinator.setAuthBehavior(pB);

        return coordinator;
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a CampAdmin
     * These are authorized users with admin priveleges in the camp system
     * They have a first name, last name, address, UUID, birthdate, password, username, and email
     * @param admin, an empty JSON Object
     * @return a new CampAdmin with the needed information parameterized
     */
    private CampAdmin parseAdminObj(JSONObject admin){
        // get attributes
        String firstName = (String) admin.get("firstName");
        String lastName = (String) admin.get("lastName");
        String address = (String) admin.get("address");
        UUID id = UUID.fromString((String) admin.get("id"));
        String birthDate = (String) admin.get("birthDate");
        String password = (String)  admin.get("password");
        String username = (String)  admin.get("username");
        String email = (String)  admin.get("email");
        String phone = (String)  admin.get("phone");

        return(new CampAdmin(firstName, lastName, birthDate, address, id, password, username, email, phone));
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a Cabin
     * Cabins contain ArrayLists of coordinators, campers, and schedules.
     * They also contain a name, camper capacity, coordinator capacity, lower age limit, and upper age limit
     * Due to having coordinators and campers, they must be parsed again in order to get the information needed
     * @param cabin, an empty JSON Object
     * @return a new Cabin with the needed information parameterized
     */
    private Cabin parseCabinObj(JSONObject cabin){
        // get attributes
        String cabinName = (String) cabin.get("name");

        int camperCapacity = Math.toIntExact((long)cabin.get("camperCapacity")) ;
        int coordinatorCapacity= Math.toIntExact((long)cabin.get("coordinatorCapacity")) ;

        int lowerAgeBound = Math.toIntExact((long)cabin.get("lowerAgeBound"));
        int upperAgeBound = Math.toIntExact((long)cabin.get("upperAgeBound"));

        ArrayList<Dependent> coordinators = new ArrayList<>();
        ArrayList<Dependent> campers = new ArrayList<>();
        ArrayList<Schedule> schedules= new ArrayList<>();


        // parse coordinators
        JSONArray jCoordinators = (JSONArray) cabin.get("coordinators");
        jCoordinators.forEach(jCoord->{
            JSONObject jO = (JSONObject) jCoord;
            UUID id = UUID.fromString((String) jO.get("id"));
            // find coordinator
            Dependent c = pM.getDependentById(id);
            if(c != null){
                coordinators.add(c);
            }
        });
        // parse campers
        JSONArray jCampers = (JSONArray) cabin.get("campers");
        jCampers.forEach(jCamper ->{
            JSONObject jO = (JSONObject) jCamper;
            UUID id = UUID.fromString((String) jO.get("id"));
            // find camper
            Dependent c = pM.getDependentById(id);
            if(c != null){
                campers.add(pM.getDependentById(id));
            }
        });

        return(new Cabin(cabinName,coordinators,campers,schedules, camperCapacity, coordinatorCapacity,lowerAgeBound,upperAgeBound));
    }
    /**
     * Creates a new JSONObject that contains the information needed to create a CampsiteManager
     * Creates a new camp that would have its own independent cabins, schedules, activities, etc.
     * @param jCamp, an empty JSON Object
     * @return a new CampSiteManager with the needed information parameterized.
     */
    private CampSiteManager parseCampObj(JSONObject jCamp){

        String name = (String) jCamp.get("name");
        String address = (String) jCamp.get("address");
        int year = Math.toIntExact((Long) jCamp.get("year"));
        UUID themeId = UUID.fromString((String) jCamp.get("themeId"));
        String startMonth = (String) jCamp.get("startMonth");

        ThemeManager themeManager = new ThemeManager();
        for(ThemeManager t: this.themeManagers){
            if(t.getId().equals(themeId)){
                themeManager = t;
            }
        }


        return CampSiteManager.getInstance(name,address,year,startMonth,themeManager);
    }

    /*
     * ***************************
     * Json Readers
     * ***************************
     */
    /**
     * Reads the JSON file for Schedules
     * Populates the ArrayList with new JSONObjects containing the information read.
     * @return scheds, the ArrayList of Schedules
     */
    private ArrayList<Schedule> readSchedules(){
        ArrayList<Schedule> scheds = new ArrayList<Schedule>();
        JSONArray jScheds = parseJsonFileArr(DataConstants.SCHEDULE_FILE_NAME);
        jScheds.forEach(jSched->{
            scheds.add(parseScheduleObj((JSONObject)jSched));
        });
        return scheds; 
    }
    /**
     * Reads the JSON file for Reviews
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return revs, the ArrayList of Reviews
     */
    private ArrayList<Review> readReviews(){
        ArrayList<Review> revs=  new ArrayList<Review>();
        JSONArray revList = parseJsonFileArr(DataConstants.REVIEW_FILE_NAME);
        revList.forEach(rev ->{
            revs.add(parseReviewObj((JSONObject)rev));
        });
        return revs;
    }
    /**
     * Reads the JSON file for FAQs
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return faqs, the ArrayList of FAQs
     */
    private ArrayList<FAQ> readFaqs(){
        ArrayList<FAQ> faqs=  new ArrayList<FAQ>();
        JSONArray faqList= parseJsonFileArr(DataConstants.FAQ_FILE_NAME);
        faqList.forEach(faq->{
            faqs.add(parseFaqObj((JSONObject)faq));
        });
        return faqs;
    }
    /**
     * Reads the JSON file for Camp
     * @return calls parseCampObject to return a JSONObject containing the information read from the JSON file
     */
    private CampSiteManager readCamp(){
        JSONArray campObject = parseJsonFileArr(DataConstants.CAMP_FILE_NAME);

        // only deal with one camp for now
        return parseCampObj((JSONObject) campObject.get(0));
    }
    /**
     * Reads the JSON file for EmergencyContacts
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return emContacts, the ArrayList of EmergencyContacts
     */
    private ArrayList<EmergencyContact> readEmergencyContacts(){
        ArrayList<EmergencyContact> emContacts = new ArrayList<EmergencyContact>();
        JSONArray emList = parseJsonFileArr(DataConstants.EMERGENCY_CONTACT_FILE_NAME);
        emList.forEach(person->{
            emContacts.add(parseEmergencyContactObj((JSONObject) person));
        });
        return emContacts;
    }
    /**
     * Reads the JSON file for Guardians
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return guardians, an ArrayList populated with Guardians
     */
    private ArrayList<Guardian> readGuardians() { 
        ArrayList<Guardian> guardians = new ArrayList<Guardian>();
        JSONArray guardianList = parseJsonFileArr(DataConstants.GUARDIAN_FILE_NAME);
        guardianList.forEach(guardian ->
            guardians.add(parseGuardianObj((JSONObject)guardian))
        );
        return guardians;
    }
    /**
     * Reads the JSON file for ThemeManagers
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return ret, populated ArrayList of ThemeManagers
     */
    private ArrayList<ThemeManager> readThemeManagers(){
        ArrayList<ThemeManager> ret = new ArrayList<ThemeManager>();
        JSONArray jaThemeManagerList = parseJsonFileArr("./json/Theme.json");
        jaThemeManagerList.forEach(jTheme ->
            ret.add(parseThemeManager((JSONObject) jTheme))
        );
        return ret;
    }
    /**
     * Reads the JSON file for CampAdmins
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return admins, a populated ArrayList of CampAdmins
     */
    private ArrayList<CampAdmin> readAdmins(){
        ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>();
        JSONArray adminList = parseJsonFileArr(DataConstants.CAMP_ADMIN_FILE_NAME);
        adminList.forEach(admin ->
            admins.add(parseAdminObj((JSONObject)admin))
        );
        return admins;
    }
    /**
     * Reads the JSON file for Cabins
     * Populates the ArrayList with new JSONObjects containing the information read
     * @return cabins, a populated ArrayList of Cabins
     */
    private ArrayList<Cabin> readCabins(){
        // cabins hold dependents, so need to read those in first
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        JSONArray cabinList = parseJsonFileArr(DataConstants.CABIN_FILE_NAME);
        cabinList.forEach(cabin ->
            cabins.add(parseCabinObj((JSONObject)cabin))
        );
        return cabins;
    }
    /**
     * Reads the JSON file for Dependents
     * Populates the ArrayList with new JSONObjects containing the information read
     * Notably populates the ArrayList with both Campers and Coordinators as they are both dependents
     * @return deps, an ArrayList populated with Dependents
     */
    private ArrayList<Dependent> readDependents() {
        ArrayList<Dependent> deps = new ArrayList<Dependent>();
        // read campers
        JSONArray jCamperList = parseJsonFileArr(DataConstants.CAMPER_FILE_NAME);
        jCamperList.forEach(jDependent ->{
            deps.add(parseCamperObj((JSONObject)jDependent));
            }
        );
        // read coordinators
        JSONArray jCoordinatorList = parseJsonFileArr(DataConstants.COORDINATOR_FILE_NAME);
        jCoordinatorList.forEach(jDependent->{
            deps.add(parseCoordinatorObj((JSONObject)jDependent));
        });
        
        return deps;
    }
    /*
     * ***************************
     * JSON Getters
     * ***************************
     */
    /**
     * Populates a Person object with information needed to create a Person
     * The information needed is a UUID, first name, last name, address, and birthdate
     * @param p, an empty Person object
     * @return jP, a JSONObject containing information needed for a Person
     */
    private static JSONObject getPersonJson(Person p){
        JSONObject jP = new JSONObject();
        jP.put("id",p.getId().toString());
        jP.put("firstName",p.getFirstName());
        jP.put("lastName",p.getLastName());
        jP.put("address",p.getAddress());
        jP.put("birthDate",p.getBirthDate());

        return jP;
    }
    /**
     * Contains the information needed for a person
     * Also contains password, email, phone, and username
     * @param p, an empty Person object
     * @return jP, a JSONObject containing information needed for a Person
     */
    private static JSONObject getPriorityPersonJson(Person p){
        PriorityBehavior pB = (PriorityBehavior) p.getAuthBehavior();
        JSONObject jP = getPersonJson(p);
        jP.put("password",pB.getPassword());
        jP.put("email",pB.getEmail());
        jP.put("phone",pB.getPhone());
        jP.put("username",pB.getUsername());

        return jP;
    }
    /**
     * Contains the information of a PriorityPerson
     * Creates a new ArrayList of JSONObjects to populate with the Guardian's Dependent's IDs
     * @param g, an empty Guardian object
     * @return jsonG, a JSONObject containing the information of Guardians
     */
    private static JSONObject getGuardianJson(Guardian g){
        JSONObject jsonG = getPriorityPersonJson(g);
        ArrayList<JSONObject> ids = new ArrayList<>();
        for(Dependent regDependent : g.getRegisteredDependents()) {
            JSONObject dependent = new JSONObject();
            dependent.put(DataConstants.DEPENDENT_ID, regDependent.id);
            ids.add(dependent);
        }
        jsonG.put(DataConstants.GUARDIAN_REGISTERED_DEPENDENTS, ids);
        return jsonG;
    }
    /**
     * Calls get priorityPerson and returns that JSONObject
     * @param cA, an empty CampAdmin object
     * @return jO, a JSONObject containing CampAdmin information
     */
    private static JSONObject getCampAdminJson(CampAdmin cA){
        JSONObject jO = getPriorityPersonJson(cA);
        return jO;
    }
    /**
     * Calls getPersonJson to get basic Camper information
     * Stores isCoordinator by calling getIsCoordinator from Dependent
     * Creates a new ArrayList for EmergencyContact ids and populates that
     * @param d, an empty Dependent object
     * @return jO, a JSONObject containing Camper information
     */
    private static JSONObject getCamperJson(Dependent d){
        JSONObject jO = getPersonJson(d);
        jO.put("isCoordinator",d.getIsCoordinator());
        ArrayList<JSONObject> ids = new ArrayList<>();
        for(EmergencyContact regEmergencyContact : d.getEmergencyContacts()) {
            JSONObject emergencyContacts = new JSONObject();
            emergencyContacts.put("id", regEmergencyContact.id);
            ids.add(emergencyContacts);
        }
        jO.put("medicalNotes",d.getMedicalNotes());
        jO.put("emergencyContacts",ids);

        return jO;
    }
    /**
     * Calls getPriorityPerson for the basic Coordinator information
     * Stores isCoordinator by calling getIsCoordinator from Dependent
     * Creates a new ArrayList for EmergencyContact ids and populates that
     * @param c, an empty Coordinator object
     * @return jCo, a JSONObject containing Coordinator information
     */
    private static JSONObject getCoordinatorJson(Dependent c) {
        JSONObject jCo = getPriorityPersonJson(c);
        jCo.put("isCoordinator",c.getIsCoordinator());
        ArrayList<JSONObject> ids = new ArrayList<>();
        for(EmergencyContact regEmergencyContact : c.getEmergencyContacts()) {
            JSONObject emergencyContacts = new JSONObject();
            emergencyContacts.put("id", regEmergencyContact.id);
            ids.add(emergencyContacts);
        }
        jCo.put("medicalNotes",c.getMedicalNotes());
        jCo.put("emergencyContacts",ids);
        return jCo;
    }
    /**
     * Creates a new JSONObject and populates it with Review information
     * @param r, an empty Review object
     * @return jR, a JSONObject containing Review information
     */
    private static JSONObject getReviewJson(Review r) {
        JSONObject jR = new JSONObject();
        jR.put("title", r.getTitle());
        jR.put("author", r.getAuthor());
        jR.put("body", r.getbody());
        jR.put("rating", r.getRating());
        return jR;
    }
    /**
     * Calls getPersonJson to get basic EmergencyContact information
     * Populates the JSONObject with phone number and relation
     * @param eC, an empty EmergencyContact object
     * @return jEc, a JSONObject containing EmergencyContact information
     */
    private static JSONObject getEmergencyContactJson(EmergencyContact eC) {
        JSONObject jEC = getPersonJson(eC);
        jEC.put("phone",eC.getPhone());
        jEC.put("relation",eC.getRelation());

        return jEC;
    }
    /**
     * Calls functions from cabin to get name, camper capacity, etc.
     * Creates two new ArrayLists of JSONObjects
     * Populates these ArrayLists with coordinator and camper IDs
     * @param c, an empty Cabin object
     * @return jC, a JSONObject containing all the Cabin information
     */
    private static JSONObject getCabinJson(Cabin c) {
        JSONObject jC = new JSONObject();
        jC.put("name", c.getCabinName());
        jC.put("camperCapacity", c.getCamperCapacity());
        jC.put("coordinatorCapacity", c.getCoordinatorCapacity());
        jC.put("lowerAgeBound", c.getLowerAgeBound());
        jC.put("upperAgeBound", c.getUpperAgeBound());
        ArrayList<JSONObject> coordIds = new ArrayList<>();
        ArrayList<JSONObject> camperIds = new ArrayList<>();
        for(Dependent regCampers : c.getCampers()) {
            JSONObject campers = new JSONObject();
            campers.put("id", regCampers.id);
            camperIds.add(campers);
        }
        for(Dependent regCoords : c.getCoordinators()) {
            JSONObject coords = new JSONObject();
            coords.put("id", regCoords.id);
            coordIds.add(coords);
        }
        jC.put("campers", camperIds);
        jC.put("coordinators", coordIds);
        return jC;
    }
    /**
     * Calls functions from Schedule to get id, session number, and cabin name
     * Creates a new ArrayList to store the daily schedules
     * Each daily schedule has a week day and activity list within it
     * @param s, an empty Schedule object
     * @return jS, a JSONObject containing Schedule information
     */
    private static JSONObject getScheduleJson(Schedule s) {
        JSONObject jS = new JSONObject();
        jS.put("id", s.getScheduleID());
        jS.put("sessionNumber", s.getSessionNumber());
        jS.put("cabinName", s.getCabinName());
        ArrayList<JSONObject> schedule = new ArrayList<>();
        
        for(Entry<String, ActivityManager> weekEntry : s.getScheduledActivities().entrySet()){
            String weekDay = weekEntry.getKey();
            JSONObject dailySchedule = new JSONObject();
            dailySchedule.put("dayOfWeek", weekDay);
           
            dailySchedule.put("schedule", weekEntry.getValue().getActivityList());
            schedule.add(dailySchedule);
        }
        jS.put("schedules",schedule);
        return jS;
    }
    /**
     * Calls functions from ThemeManager and Theme to get information needed.
     * Each ThemeManager has a UUID, and an ArrayList of Themes that have names, week numbers, and descriptions
     * @param t, an empty ThemeManager object
     * @return jT, a JSONObject containing ThemeManager information
     */
    private static JSONObject getThemeManagerJson(ThemeManager t) {
        JSONObject jT = new JSONObject();
        jT.put("id", t.getId());
        ArrayList<JSONObject> weeklyThemes = new ArrayList<>();
        for(Theme themes : t.getThemes()) {
            JSONObject dailyTheme = new JSONObject();
            dailyTheme.put("name", themes.getName());
            dailyTheme.put("week", themes.getWeekNumber());
            dailyTheme.put("description",themes.getDescription());
            weeklyThemes.add(dailyTheme);
        }
        jT.put("themes", weeklyThemes);
        return jT;
    }
    /**
     * Uses functions from CampSiteManager to populate a JSONObject 
     * @param cM, an empty CampSiteManager object
     * @return jCM, a JSONObject containing CampSiteManager information
     */
    private static JSONObject getCampJson(CampSiteManager cM) {
        JSONObject jCM = new JSONObject();
        jCM.put("name", cM.getName());
        jCM.put("address", cM.getAddress());
        jCM.put("pricePerCamperPerDay", cM.getPricePerCamper());
        jCM.put("year", cM.getYear());
        jCM.put("startMonth", cM.getStartMonth());
        jCM.put("themeId", cM.getCurrentThemeID());
        return jCM;
    }

    /*
     * ***************************
     * JSON Writers
     * ***************************
     */

    /**
     * Writes a JSONObject to a file
     * @param jO, the String representation of the JSONObject you wish to write
     * @param filePath the Filepath that you wish to write to
     */
    private static void writeToJson(String jO,String filePath){
        try(FileWriter fW = new FileWriter(filePath)){
            fW.write(jO);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Corrects a missing feature in JsonParser.parseString() by adding brackets required in JSON file formatting
     * @param jsonFile the String representation of the JSONObject
     * @return fixedFormat, a String with brackets and new lines added to each end
     */
    private static String jsonFormatter(String jsonFile) {
        String fixedFormat = "[\n"+jsonFile+"\n]";
        return fixedFormat;
    }
    /**
     * Writes an ArrayList of Guardians to the respective JSON file
     * @param guardian the ArrayList of Guardians you wish to write
     */
    public static void writeGuardian(ArrayList<Guardian> guardian) {
        String guardJsonList = "";
        boolean isFirst = true;
        for(Guardian newGuard : guardian) {
            JSONObject guardInfo = getGuardianJson(newGuard);
            String guardInfoString = guardInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(guardInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                guardJsonList = guardJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                guardJsonList = guardJsonList+",\n"+formattedJsonString;
            }
        }
        String finalGuardString = jsonFormatter(guardJsonList);
        writeToJson(finalGuardString,DataConstants.GUARDIAN_FILE_NAME);
    }
    /**
     * Writes an ArrayList of CampAdmins to the respective JSON file
     * @param admin the ArrayList of CampAdmins you wish to write
     */
    public static void writeCampAdmin(ArrayList<CampAdmin> admin) {
        String adminJsonList = "";
        boolean isFirst = true;
        for(CampAdmin newAdmin : admin) {
            JSONObject adminInfo = getCampAdminJson(newAdmin);
            String adminInfoString = adminInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(adminInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                adminJsonList = adminJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                adminJsonList = adminJsonList+",\n"+formattedJsonString;
            }
        }
        String finalAdminString = jsonFormatter(adminJsonList);
        writeToJson(finalAdminString,DataConstants.CAMP_ADMIN_FILE_NAME);
    }
    /**
     * Writes an ArrayList of Dependents to their respective files
     * This functions checks each Dependent if it's Coordinator or not
     * If it is a Coordinator then it is written to the Coordinator JSON file
     * If is is not a Coordinator then it is written to the Camper JSON file
     * @param dependent the ArrayList of Dependents you wish to write
     */
    public static void writeDependent(ArrayList<Dependent> dependent) {
        String dependJsonList = "";
        String coordJsonList = "";
        boolean isFirstCoordinator = true;
        boolean isFirstCamper = true;
        for(Dependent newDepend : dependent) {
            if(newDepend.getIsCoordinator()) {
                JSONObject coordInfo = getCoordinatorJson(newDepend);
                String coordInfoString = coordInfo.toJSONString();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(coordInfoString);
                String formattedJsonString = gson.toJson(je);
                if(isFirstCoordinator) {
                    coordJsonList = coordJsonList+formattedJsonString;
                    isFirstCoordinator = false;
                }
                else {
                    coordJsonList = coordJsonList+",\n"+formattedJsonString;
                }
                String finalCoordinatorString = jsonFormatter(coordJsonList);
                writeToJson(finalCoordinatorString,DataConstants.COORDINATOR_FILE_NAME);
            }
            else {
                JSONObject dependInfo = getCamperJson(newDepend);
                String dependInfoString = dependInfo.toJSONString();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(dependInfoString);
                String formattedJsonString = gson.toJson(je);
                if(isFirstCamper) {
                    dependJsonList = dependJsonList+formattedJsonString;
                    isFirstCamper = false;
                }
                else {
                    dependJsonList = dependJsonList+",\n"+formattedJsonString;
                }
                String finalDependentString = jsonFormatter(dependJsonList);
                writeToJson(finalDependentString,DataConstants.CAMPER_FILE_NAME);
            }
        }
    }
    /**
     * Writes an ArrayList of Reviews to the respective JSON file
     * Check's each review for apostrophe's and replaces them accordingly as JSON cannot parse those correctly
     * @param guardian the ArrayList of Reviews you wish to write
     */
    public static void writeReview(ArrayList<Review> review) {
        String reviewJsonList = "";
        boolean isFirst = true;
        for(Review newReview : review) {
            JSONObject revInfo = getReviewJson(newReview);
            String reviewInfoString = revInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(reviewInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                reviewJsonList = reviewJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                reviewJsonList = reviewJsonList+",\n"+formattedJsonString;
            }
        }
        String finalCoordinatorString = jsonFormatter(reviewJsonList);
        String formattedCoordinatorString = finalCoordinatorString.replace("\\u0027", "\'");
        writeToJson(formattedCoordinatorString, DataConstants.REVIEW_FILE_NAME);
    }
    /**
     * Writes an ArrayList of EmergencyContacts to the respective JSON file
     * @param guardian the ArrayList of EmergencyContacts you wish to write
     */
    public static void writeEmergencyContact(ArrayList<EmergencyContact> emergencyC) {
        String emcJsonList = "";
        boolean isFirst = true;
        for(EmergencyContact newEMC : emergencyC) {
            JSONObject eCInfo = getEmergencyContactJson(newEMC);
            String contactInfoString = eCInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(contactInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                emcJsonList = emcJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                emcJsonList = emcJsonList+",\n"+formattedJsonString;
            }
        }
        String finalEmergencyContactString = jsonFormatter(emcJsonList);
        writeToJson(finalEmergencyContactString, DataConstants.EMERGENCY_CONTACT_FILE_NAME);
    }
    /**
     * Writes an ArrayList of Cabins to the respective JSON file
     * @param guardian the ArrayList of Cabins you wish to write
     */
    public static void writeCabin(ArrayList<Cabin> cabin) {
        String cabinJsonList = "";
        boolean isFirst = true;
        for(Cabin newCabin : cabin) {
            JSONObject cabinInfo = getCabinJson(newCabin);
            String cabinInfoString = cabinInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(cabinInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                cabinJsonList = cabinJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                cabinJsonList =cabinJsonList+",\n"+formattedJsonString;
            }
        }
        String finalCabinString = jsonFormatter(cabinJsonList);
        writeToJson(finalCabinString, DataConstants.CABIN_FILE_NAME);
    }
    /**
     * Writes an ArrayList of Schedules to the respective JSON file
     * @param guardian the ArrayList of Schedules you wish to write
     */
    public static void writeSchedule(ArrayList<Schedule> schedule) {
        String scheduleJsonList = "";
        boolean isFirst = true;
        for(Schedule newSchedule : schedule) {
            JSONObject scheduleInfo = getScheduleJson(newSchedule);
            String scheduleInfoString = scheduleInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(scheduleInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                scheduleJsonList = scheduleJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                scheduleJsonList =scheduleJsonList+",\n"+formattedJsonString;
            }
        }
        String finalScheduleString = jsonFormatter(scheduleJsonList);
        writeToJson(finalScheduleString, DataConstants.SCHEDULE_FILE_NAME);
    }
    /**
     * Writes an ArrayList of to the respective JSON file
     * 
     * @param guardian the ArrayList of you wish to write
     */
    public static void writeTheme(ArrayList<ThemeManager> theme) {
        String themeJsonList = "";
        boolean isFirst = true;
        for(ThemeManager newTheme : theme) {
            JSONObject themeInfo = getThemeManagerJson(newTheme);
            String themeInfoString = themeInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(themeInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                themeJsonList = themeJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                themeJsonList = themeJsonList+",\n"+formattedJsonString;
            }
        }
        String finalThemeString = jsonFormatter(themeJsonList);
        writeToJson(finalThemeString, DataConstants.THEME_FILE_NAME);
    }
    /**
     * Writes an ArrayList of CampSiteManagers to the respective JSON file
     * 
     * @param guardian the ArrayList of CampSiteManagers you wish to write
     */
    public static void writeCamp(CampSiteManager camp) {
        String campJsonList = "";
        boolean isFirst = true;
        JSONObject campInfo = getCampJson(camp);
        String campInfoString = campInfo.toJSONString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(campInfoString);
        String formattedJsonString = gson.toJson(je);
        if(isFirst) {
            campJsonList = campJsonList+formattedJsonString;
            isFirst = false;
        }
        else {
            campJsonList = campJsonList+",\n"+formattedJsonString;
        }

        String finalCampString = jsonFormatter(campJsonList);
        writeToJson(finalCampString, DataConstants.CAMP_FILE_NAME);
    }
    /**
     * Used by the readers in order to parse the information from the given filepath
     * @param filename, file path of the file you wish to parse
     * @return a JSONArray of objects containing the information from the filepath
     */
    private static JSONArray parseJsonFileArr(String filename) {
        JSONParser jsonP = new JSONParser();
        try(FileReader reader = new FileReader(filename)){
            Object obj = jsonP.parse(reader);
            JSONArray jsonFileList = (JSONArray) obj;
            return jsonFileList;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
    /**
     * Writes a String to a given file path
     * @param fileContents, a string representation of what you wish to be written to a text file
     * @param fileName the filepath you wish to write to
     */
    public static void writeToTxtFile(String fileContents, String fileName){
        try{
            Files.write(Paths.get(fileName),fileContents.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

    // singleton
    private static FileIO fileIO;

    private void populateData(){
        /*
         * Populates all arraylists of data
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
    private FileIO(){
        populateData();
    }
    public static FileIO getInstance(){
        if(fileIO == null){
            fileIO = new FileIO();
            return fileIO;
        }
        return fileIO;
    }

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

    /*
     * ***************************
     * Json Parsers
     * ***************************
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
        Schedule sched = new Schedule(hash, sessionNumber,id);
        // add to cabin
        for(Cabin c: cabins){
            if(c.getCabinName().equals(cabinName)){
                c.addSchedule(sched);
            }
        }
        return sched;
    }

    private static FAQ parseFaqObj(JSONObject jFaq){
        String question = (String) jFaq.get(DataConstants.FAQ_QUESTION);
        String answer = (String) jFaq.get(DataConstants.FAQ_ANSWER);
        return new FAQ(question,answer);
    }
    private ThemeManager parseThemeManager(JSONObject jTheme) {
        ThemeManager tm = new ThemeManager();
        UUID id = UUID.fromString((String) jTheme.get("id"));
        tm.setId(id);
        JSONArray jaThemes = (JSONArray) jTheme.get("themes");
        jaThemes.forEach(objTheme->{
            JSONObject joTheme = (JSONObject)objTheme;
            String name = (String) joTheme.get("name");
            int week = Math.toIntExact((long)joTheme.get("week"));
            tm.addTheme(new Theme(name, week));
        });
        return tm;
    }
    private Review parseReviewObj(JSONObject rev){
        
        String author= (String) rev.get(DataConstants.REVIEW_AUTHOR);
        int rating= Math.toIntExact((Long)rev.get(DataConstants.REVIEW_RATING));
        String title= (String) rev.get(DataConstants.REVIEW_TITLE);
        String body= (String) rev.get(DataConstants.REVIEW_BODY);

        return new Review(author,rating,title,body);
    }
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
        String phone = (String)  admin.get("phoneNumber");

        return(new CampAdmin(firstName, lastName, birthDate, address, id, password, username, email, phone));
    }
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
    private ArrayList<Schedule> readSchedules(){
        ArrayList<Schedule> scheds = new ArrayList<Schedule>();
        JSONArray jScheds = parseJsonFileArr(DataConstants.SCHEDULE_FILE_NAME);
        jScheds.forEach(jSched->{
            scheds.add(parseScheduleObj((JSONObject)jSched));
        });
        return scheds; 
    }
    private ArrayList<Review> readReviews(){
        ArrayList<Review> revs=  new ArrayList<Review>();
        JSONArray revList = parseJsonFileArr(DataConstants.REVIEW_FILE_NAME);
        revList.forEach(rev ->{
            revs.add(parseReviewObj((JSONObject)rev));
        });
        return revs;
    }
    private ArrayList<FAQ> readFaqs(){
        ArrayList<FAQ> faqs=  new ArrayList<FAQ>();
        JSONArray faqList= parseJsonFileArr(DataConstants.FAQ_FILE_NAME);
        faqList.forEach(faq->{
            faqs.add(parseFaqObj((JSONObject)faq));
        });
        return faqs;
    }
    private CampSiteManager readCamp(){
        JSONArray campObject = parseJsonFileArr(DataConstants.CAMP_FILE_NAME);

        // only deal with one camp for now
        return parseCampObj((JSONObject) campObject.get(0));
    }
    private ArrayList<EmergencyContact> readEmergencyContacts(){
        ArrayList<EmergencyContact> emContacts = new ArrayList<EmergencyContact>();
        JSONArray emList = parseJsonFileArr(DataConstants.EMERGENCY_CONTACT_FILE_NAME);
        emList.forEach(person->{
            emContacts.add(parseEmergencyContactObj((JSONObject) person));
        });
        return emContacts;
    }

    private ArrayList<Guardian> readGuardians() { 
        ArrayList<Guardian> guardians = new ArrayList<Guardian>();
        JSONArray guardianList = parseJsonFileArr(DataConstants.GUARDIAN_FILE_NAME);
        guardianList.forEach(guardian ->
            guardians.add(parseGuardianObj((JSONObject)guardian))
        );
        return guardians;
    }   
    private ArrayList<ThemeManager> readThemeManagers(){
        ArrayList<ThemeManager> ret = new ArrayList<ThemeManager>();
        JSONArray jaThemeManagerList = parseJsonFileArr("./json/Theme.json");
        jaThemeManagerList.forEach(jTheme ->
            ret.add(parseThemeManager((JSONObject) jTheme))
        );
        return ret;
    }
    private ArrayList<CampAdmin> readAdmins(){
        ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>();
        JSONArray adminList = parseJsonFileArr(DataConstants.CAMP_ADMIN_FILE_NAME);
        adminList.forEach(admin ->
            admins.add(parseAdminObj((JSONObject)admin))
        );
        return admins;
    }
    private ArrayList<Cabin> readCabins(){
        // cabins hold dependents, so need to read those in first
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        JSONArray cabinList = parseJsonFileArr(DataConstants.CABIN_FILE_NAME);
        cabinList.forEach(cabin ->
            cabins.add(parseCabinObj((JSONObject)cabin))
        );
        return cabins;
    }
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

    private JSONObject getPersonJson(Person p){
        JSONObject jP = new JSONObject();
        jP.put("id",p.getId().toString());
        jP.put("firstName",p.getFirstName());
        jP.put("lastName",p.getLastName());
        jP.put("address",p.getAddress());
        jP.put("birthDate",p.getBirthDate());

        return jP;
    }
    private JSONObject getPriorityPersonJson(Person p){
        PriorityBehavior pB = (PriorityBehavior) p.getAuthBehavior();
        JSONObject jP = getPersonJson(p);
        jP.put("password",pB.getPassword());
        jP.put("email",pB.getEmail());
        jP.put("phone",pB.getPhone());
        jP.put("username",pB.getUsername());

        return jP;
    }
    private JSONObject getGuardianJson(Guardian g){
        JSONObject jsonG = getPriorityPersonJson(g);
        ArrayList<JSONObject> ids = new ArrayList<>();
        for(Dependent regDependent : g.getRegisteredDependents()) {
            JSONObject dependent = new JSONObject();
            dependent.put("id", regDependent.id);
            ids.add(dependent);
        }
        jsonG.put("registeredDependents", ids);
        return jsonG;
    }
    private JSONObject getCampAdminJson(CampAdmin cA){
        JSONObject jO = getPriorityPersonJson(cA);
        return jO;
    }
    private JSONObject getCamperJson(Dependent d){
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
    private JSONObject getCoordinatorJson(Dependent c) {
        JSONObject jCo = getPriorityPersonJson(c);
        jCo.put("isCoordinator",c.getIsCoordinator());
        String jsonMedNotes = new Gson().toJson(c.getMedicalNotes());
        String jsonEmContacts = new Gson().toJson(c.getEmergencyContacts());
        jCo.put("medicalNotes",jsonMedNotes);
        jCo.put("emergencyContacts",jsonEmContacts);
        return jCo;
    }
    private JSONObject getReviewJson(Review r) {
        JSONObject jR = new JSONObject();
        jR.put("title", r.getTitle());
        jR.put("author", r.getAuthor());
        jR.put("body", r.getbody());
        jR.put("rating", r.getRating());
        return jR;
    }
    private JSONObject getEmergencyContactJson(EmergencyContact eC) {
        JSONObject jEC = getPersonJson(eC);
        return jEC;
    }
    private JSONObject getCabinJson(Cabin c) {
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
        //add coordinator info
        return jC;
    }
    private JSONObject getScheduleJson(Schedule s) {
        JSONObject jS = new JSONObject();
        jS.put("id", s.getScheduleID());
        ArrayList<JSONObject> schedules = new ArrayList<>();
        ArrayList<JSONObject> dailySchedule = new ArrayList<>();
        //for(Schedule cabinSchedule : s.get)
        return jS;
    }

    /*
     * ***************************
     * JSON Writers
     * ***************************
     */
    // write a JSON object to file
    private void writeToJson(String jO,String filePath){
        try(FileWriter fW = new FileWriter(filePath)){
            fW.write(jO);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private String jsonFormatter(String jsonFile) {
        String fixedFormat = "[\n"+jsonFile+"\n]";
        return fixedFormat;
    }
    private void writeGuardian(ArrayList<Guardian> guardian) {
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
    private void writeCampAdmin(ArrayList<CampAdmin> admin) {
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
    private void writeDependent(ArrayList<Dependent> dependent) {
        String dependJsonList = "";
        boolean isFirst = true;
        for(Dependent newDepend : dependent) {
            JSONObject dependInfo = getCamperJson(newDepend);
            String dependInfoString = dependInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(dependInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                dependJsonList = dependJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                dependJsonList = dependJsonList+",\n"+formattedJsonString;
            }
        }
        String finalDependentString = jsonFormatter(dependJsonList);
        writeToJson(finalDependentString,DataConstants.CAMPER_FILE_NAME);
    }
    private void writeCoordinator(ArrayList<Dependent> coordinator){
        String coordJsonList = "";
        boolean isFirst = true;
        for(Dependent newCoord : coordinator) {
            JSONObject coordInfo = getCoordinatorJson(newCoord);
            String coordInfoString = coordInfo.toJSONString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(coordInfoString);
            String formattedJsonString = gson.toJson(je);
            if(isFirst) {
                coordJsonList = coordJsonList+formattedJsonString;
                isFirst = false;
            }
            else {
                coordJsonList = coordJsonList+",\n"+formattedJsonString;
            }
        }
        String finalCoordinatorString = jsonFormatter(coordJsonList);
        writeToJson(finalCoordinatorString,DataConstants.COORDINATOR_FILE_NAME);
    }
    private void writeReview(ArrayList<Review> review) {
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
    private void writeEmergencyContact(ArrayList<EmergencyContact> emergencyC) {
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
    private void writeCabin(ArrayList<Cabin> cabin) {
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
                cabinJsonList =cabinJsonList+"\n"+formattedJsonString;
            }
        }
        String finalCabinString = jsonFormatter(cabinJsonList);
        writeToJson(finalCabinString, DataConstants.CABIN_FILE_NAME);
    }
    private void writeSchedule(ArrayList<Schedule> schedule) {
        String scheduleJsonList = "";
        boolean isFirst = true;
        for(Schedule newSchudule : schedule) {
            //JSONObject scheduleInfo 
        }
    }
    /*
     * TODO:
     *  - write EmergencyContact
     *  - write Cabin
     *  - write Schedule
     */
    private JSONArray parseJsonFileArr(String filename) {
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
    public static void writeToTxtFile(String fileContents, String fileName){
        try{
            Files.write(Paths.get(fileName),fileContents.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        FileIO fiO = FileIO.getInstance();
        ArrayList<EmergencyContact> test = fiO.readEmergencyContacts();
        fiO.writeEmergencyContact(test);
        System.out.println("here");

    }
}

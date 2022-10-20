import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class FileIO {
    static String guardianJsonPath = "./json/Guardian.json";
    static String adminJsonPath = "./json/CampAdmin.json";
    static String dependentJsonPath = "./json/Dependent.json";
    static String cabinJsonPath = "./json/Cabin.json";
    static String campJsonPath= "./json/Camp.json";
    static String faqJsonPath= "./json/Faq.json";
    static String scheduleJsonPath= "./json/Schedule.json";
    static String reviewJsonPath = "./json/Review.json";

    static ArrayList<Guardian> guardians;
    static ArrayList<CampAdmin> admins;
    static ArrayList<Cabin> cabins;
    static ArrayList<FAQ> faqs;
    static ArrayList<Review> reviews;
    static ArrayList<Schedule> schedules;
    static ArrayList<Dependent> dependents;

    static PersonManager pM;

    static boolean isInitiated = false;

    public static void populateData(){
        /*
         * Populates all arraylists of data
         * ORDER MATTERS! DEPENDENCIES FIRST!
         */
        faqs = readFaqs();
        reviews = readReviews();
        admins = readAdmins();
        guardians = readGuardians();
        dependents = readDependents();
        // init person manager
        pM = new PersonManager(admins, guardians, dependents);
        // to do: read schedules
        cabins = readCabins();
    }
    private static void checkIfInitiated(){
        if(!isInitiated){
            populateData();
        }
        isInitiated = true;
    }
    public static ArrayList<CampAdmin> getAdmins(){
        checkIfInitiated();
        return admins;
    }
    public static ArrayList<Dependent> getDependents(){
        checkIfInitiated();
        return dependents;
    }
    public static ArrayList<Guardian> getGuardians(){
        checkIfInitiated();
        return guardians;
    }
    public static ArrayList<Review> getReviews(){
        checkIfInitiated();
        return reviews;
    }
    public static ArrayList<FAQ> getFaqs(){
        checkIfInitiated();
        return faqs;
    }
    public static ArrayList<Cabin> getCabins(){
        checkIfInitiated();
        return cabins;
    }

    /*
     * ***************************
     * Json Parsers
     * ***************************
     */
    private static FAQ parseFaqObj(JSONObject jFaq){
        String question = (String) jFaq.get("question");
        String answer = (String) jFaq.get("answer");
        return new FAQ(question,answer);
    }
    private static Review parseReviewObj(JSONObject rev){
        
        String author= (String) rev.get("author");
        int rating= Math.toIntExact((Long)rev.get("rating"));
        String title= (String) rev.get("title");
        String body= (String) rev.get("body");

        return new Review(author,rating,title,body);
    }
    private static Guardian parseGuardianObj(JSONObject guardian){
        // get attributes
        String firstName = (String) guardian.get("firstName");
        String lastName = (String) guardian.get("lastName");
        String address = (String) guardian.get("address");
        UUID id = UUID.fromString((String)guardian.get("id"));
        String birthDate = (String) guardian.get("birthDate");
        String password = (String) guardian.get("password");
        String username = (String) guardian.get("username");
        String email = (String) guardian.get("email");
        String phoneNumber = (String) guardian.get("username");

        return(new Guardian(firstName, lastName, birthDate, address, id, password, username, email, phoneNumber));
    }
    private static Dependent parseDependentObj(JSONObject dependent){
        // get attributes
        String firstName = (String) dependent.get("firstName");
        String lastName = (String) dependent.get("lastName");
        String address = (String) dependent.get("address");
        UUID id = UUID.fromString((String) dependent.get("id"));
        String birthDate = (String) dependent.get("birthDate");
        boolean hasBeenPaidFor = (boolean) dependent.get("hasBeenPaidFor");
        boolean isCoordinator = (boolean) dependent.get("isCoordinator");

        return(new Dependent(firstName, lastName, birthDate, address, id));
    }
    private static CampAdmin parseAdminObj(JSONObject admin){
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
    private static Cabin parseCabinObj(JSONObject cabin){
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
                coordinators.add(pM.getDependentById(id));
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
    private static CampSiteManager parseCampObj(JSONObject camp){

        String name = (String) camp.get("name");
        String address = (String) camp.get("address");
        double price = (double) camp.get("pricePerCamperPerDay");
        String authCode = (String) camp.get("authCode");

        return CampSiteManager.getInstance(name,address, price,authCode);
    }

    /*
     * ***************************
     * Json Readers
     * ***************************
     */
    private static ArrayList<Review> readReviews(){
        ArrayList<Review> revs=  new ArrayList<Review>();
        JSONArray revList = parseJsonFileArr(reviewJsonPath);
        revList.forEach(rev ->{
            revs.add(parseReviewObj((JSONObject)rev));
        });
        return revs;
    }
    private static ArrayList<FAQ> readFaqs(){
        ArrayList<FAQ> faqs=  new ArrayList<FAQ>();
        JSONArray faqList= parseJsonFileArr(faqJsonPath);
        faqList.forEach(faq->{
            faqs.add(parseFaqObj((JSONObject)faq));
        });
        return faqs;
    }
    private static CampSiteManager readCamp(){
        JSONArray campObject = parseJsonFileArr(campJsonPath);

        // only deal with one camp for now
        return parseCampObj((JSONObject) campObject.get(0));
    }

    private static ArrayList<Guardian> readGuardians() { 
        ArrayList<Guardian> guardians = new ArrayList<Guardian>();
        JSONArray guardianList = parseJsonFileArr(guardianJsonPath);
        guardianList.forEach(guardian ->
            guardians.add(parseGuardianObj((JSONObject)guardian))
        );
        return guardians;
    }   
    private static ArrayList<CampAdmin> readAdmins(){
        ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>();
        JSONArray adminList = parseJsonFileArr(adminJsonPath);
        adminList.forEach(admin ->
            admins.add(parseAdminObj((JSONObject)admin))
        );
        return admins;
    }
    private static ArrayList<Cabin> readCabins(){
        // cabins hold dependents, so need to read those in first
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        JSONArray cabinList = parseJsonFileArr(cabinJsonPath);
        cabinList.forEach(cabin ->
            cabins.add(parseCabinObj((JSONObject)cabin))
        );
        return cabins;
    }
    private static ArrayList<Dependent> readDependents() {
        ArrayList<Dependent> deps= new ArrayList<Dependent>();
        JSONArray dependentList = parseJsonFileArr(dependentJsonPath);
            dependentList.forEach(dependent ->
                deps.add(parseDependentObj((JSONObject)dependent))
        );
        dependents =deps;
        return dependents;
    }
    /*
     * ***************************
     * JSON Getters
     * ***************************
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
    private static JSONObject getPriorityPersonJson(Person p){
        PriorityBehavior pB = (PriorityBehavior) p.getAuthBehavior();
        JSONObject jP = getPersonJson(p);
        jP.put("password",pB.getPassword());
        jP.put("email",pB.getEmail());
        jP.put("phone",pB.getPhone());
        jP.put("username",pB.getUsername());

        return jP;
    }
    private static JSONObject getGuardianJson(Guardian g){
        JSONObject jsonG = getPriorityPersonJson(g);
        return jsonG;
    }
    private static JSONObject getCampAdminJson(CampAdmin cA){
        JSONObject jO = getPriorityPersonJson(cA);
        return jO;
    }
    private static JSONObject getDependentJson(Dependent d){
        JSONObject jO = getPersonJson(d);
        jO.put("hasBeenPaidFor",d.getHasBeenPaidFor());
        jO.put("isCoordinator",d.getIsCoordinator());
        String jsonMedNotes = new Gson().toJson(d.getMedicalNotes());
        String jsonEmContacts = new Gson().toJson(d.getMedicalNotes());
        jO.put("medicalNotes",jsonMedNotes);
        jO.put("emergencyContacts",jsonEmContacts);

        return jO;
    }

    /*
     * ***************************
     * JSON Writers
     * ***************************
     */
    // write a JSON object to file
    private static void writeToJson(JSONObject jO,String filePath){
        try(FileWriter fW = new FileWriter(filePath)){
            fW.write(jO.toJSONString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void writeGuardian(Guardian guardian) {
        writeToJson(getGuardianJson(guardian),guardianJsonPath);
    }
    private static void writeCampAdmin(CampAdmin admin) {
        writeToJson(getCampAdminJson(admin),adminJsonPath);
    }
    private static void writeDependent(Dependent dependent) {
        writeToJson(getDependentJson(dependent),dependentJsonPath);
    }
    private static void writeCabin(Cabin cabin) {

    }
    private static void writeReview(Review review) {

    }
    private static void writeCoordinator(Dependent coordinator){

    }
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
    public static void main(String[] args){
        CampSiteManager cSM = readCamp();
        cSM.seeCabins();
    }
}

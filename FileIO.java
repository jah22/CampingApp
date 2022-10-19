import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
        String cabinId = (String) cabin.get("id");
        ArrayList<Dependent> coordinators = (ArrayList<Dependent>) cabin.get("coordinators");
        ArrayList<Dependent> campers = (ArrayList<Dependent>) cabin.get("campers");
        ArrayList<Schedule> schedules = (ArrayList<Schedule>) cabin.get("schedules");
        int camperCapacity = (int) cabin.get("camperCapacity");
        int coordinatorCapacity = (int) cabin.get("coordinatorCapacity");

        return(new Cabin(cabinId, coordinators, campers, schedules, camperCapacity, coordinatorCapacity));
    }
    private static CampSiteManager parseCampObj(JSONObject camp){

        String name = (String) camp.get("name");
        String address = (String) camp.get("address");
        double price = (double) camp.get("pricePerCamperPerDay");
        String authCode = (String) camp.get("authCode");

        return CampSiteManager.getInstance(name,address, price,readFaqs(),readReviews(),authCode);
    }

    /*
     * ***************************
     * Json Readers
     * ***************************
     */
    public static ArrayList<Review> readReviews(){
        ArrayList<Review> revs=  new ArrayList<Review>();
        JSONArray revList = parseJsonFileArr(reviewJsonPath);
        revList.forEach(rev ->{
            revs.add(parseReviewObj((JSONObject)rev));
        });
        return revs;
    }
    public static ArrayList<FAQ> readFaqs(){
        ArrayList<FAQ> faqs=  new ArrayList<FAQ>();
        JSONArray faqList= parseJsonFileArr(faqJsonPath);
        faqList.forEach(faq->{
            faqs.add(parseFaqObj((JSONObject)faq));
        });
        return faqs;
    }
    public static CampSiteManager readCamp(){
        JSONArray campObject = parseJsonFileArr(campJsonPath);

        // only deal with one camp for now
        return parseCampObj((JSONObject) campObject.get(0));
    }

    public static ArrayList<Guardian> readGuardians() { 
        ArrayList<Guardian> guardians = new ArrayList<Guardian>();
        JSONArray guardianList = parseJsonFileArr(guardianJsonPath);
        guardianList.forEach(guardian ->
            guardians.add(parseGuardianObj((JSONObject)guardian))
        );
        return guardians;
    }   
    public static ArrayList<CampAdmin> readAdmins(){
        ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>();
        JSONArray adminList = parseJsonFileArr(adminJsonPath);
        adminList.forEach(admin ->
            admins.add(parseAdminObj((JSONObject)admin))
        );
        return admins;
    }
    public static ArrayList<Cabin> readCabins(){
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        JSONArray cabinList = parseJsonFileArr(adminJsonPath);
        cabinList.forEach(cabin ->
        cabins.add(parseCabinObj((JSONObject)cabin))
        );
        return cabins;
    }
    public static ArrayList<Dependent> readDependents() {
        ArrayList<Dependent> dependents = new ArrayList<Dependent>();
        JSONArray dependentList = parseJsonFileArr(dependentJsonPath);
        dependentList.forEach(dependent ->
        dependents.add(parseDependentObj((JSONObject)dependent))
        );
        return dependents;
    }
    /*
     * ***************************
     * JSON Getters
     * ***************************
     */

    public static JSONObject getPersonJson(Person p){
        JSONObject jP = new JSONObject();
        jP.put("id",p.getId().toString());
        jP.put("firstName",p.getFirstName());
        jP.put("lastName",p.getLastName());
        jP.put("address",p.getAddress());
        jP.put("birthDate",p.getBirthDate());

        return jP;
    }
    public static JSONObject getPriorityPersonJson(Person p){
        PriorityBehavior pB = (PriorityBehavior) p.getAuthBehavior();
        JSONObject jP = getPersonJson(p);
        jP.put("password",pB.getPassword());
        jP.put("email",pB.getEmail());
        jP.put("phone",pB.getPhone());
        jP.put("username",pB.getUsername());

        return jP;
    }
    public static JSONObject getGuardianJson(Guardian g){
        JSONObject jsonG = getPriorityPersonJson(g);
        return jsonG;
    }
    public static JSONObject getCampAdminJson(CampAdmin cA){
        JSONObject jO = getPriorityPersonJson(cA);
        return jO;
    }
    public static JSONObject getDependentJson(Dependent d){
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
    public static void writeToJson(JSONObject jO,String filePath){
        try(FileWriter fW = new FileWriter(filePath)){
            fW.write(jO.toJSONString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void writeGuardian(Guardian guardian) {
        writeToJson(getGuardianJson(guardian),guardianJsonPath);
    }
    public static void writeCampAdmin(CampAdmin admin) {
        writeToJson(getCampAdminJson(admin),adminJsonPath);
    }
    public static void writeDependent(Dependent dependent) {
        writeToJson(getDependentJson(dependent),dependentJsonPath);
    }
    public static void writeCabin(Cabin cabin) {

    }
    public static void writeReview(Review review) {

    }
    public static void writeCoordinator(Dependent coordinator){

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
        System.out.println(cSM);
    }
}

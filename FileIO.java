import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class FileIO {
    static String guardianJsonPath = "./json/Gaurdian.json";
    static String adminJsonPath = "./json/admins.json";
    static String dependentJsonPath = "./json/dependents.json";
    static String cabinJsonPath = "./json/cabins.json";

    public static ArrayList<Gaurdian> readGaurdians() { ArrayList<Gaurdian> guardians = new ArrayList<Gaurdian>();
        ArrayList<Gaurdian> gaurdians = new ArrayList<>();
        JSONParser jsonP = new JSONParser();
        try(FileReader reader = new FileReader(guardianJsonPath)){
            Object obj = jsonP.parse(reader);
            JSONArray guardianList = (JSONArray) obj;
            guardianList.forEach(guardian ->
                guardians.add(parseGaurdianObj((JSONObject)guardian))
            );
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        // to do
        return guardians;
    }
    private static Gaurdian parseGaurdianObj(JSONObject guardian){
        // get attributes
        String firstName = (String) guardian.get("firstName");
        String lastName = (String) guardian.get("lastName");
        String address = (String) guardian.get("address");
        UUID id = UUID.fromString((String)guardian.get("id"));
        String birthDate = (String) guardian.get("birthDate");

        return(new Gaurdian(firstName, lastName, birthDate, address, id, firstName, lastName, address, birthDate));
    }
    public static ArrayList<Dependent> readDependents(){
        ArrayList<Dependent> dependents = new ArrayList<Dependent>();

        // to do
        return dependents;
    }
    public static ArrayList<CampAdmin> readAdmins(){
        ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>();

        // to do
        return admins;
    }
    public static ArrayList<Cabin> readCabins(){
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();

        // to do
        return cabins;
    }

    public static void writeGuardian(Gaurdian gaurdian){

    }
    public static void writeCampAdmin(CampAdmin admin){

    }
    public static void writeCamper(Dependent dependent){

    }
    public static void writeCabin(Cabin cabin){

    }
    public static void writeReview(Review review){

    }
    public static void writeCoordinator(Dependent coordinator){

    }
    public static void main(String[] args){
    }
}

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private String guardianJsonPath = "./json/gaurdians.json";
    private String adminJsonPath = "./json/admins.json";
    private String dependentJsonPath = "./json/dependents.json";
    private String cabinJsonPath = "./json/cabins.json";

    public static ArrayList<Gaurdian> readGaurdians() { ArrayList<Gaurdian> guardians = new ArrayList<Gaurdian>();
        JSONParser jsonP = new JSONParser();
        try(FileReader reader = new FileReader("./json/test.json")){
            Object obj = jsonP.parse(reader);
            JSONArray empList = (JSONArray) obj;
            System.out.println(empList);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // to do
        return guardians;
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
        readGaurdians();
    }

}

import java.util.ArrayList;
import java.util.UUID;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Gaurdian>  gaurdians = new ArrayList<Gaurdian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Gaurdian> gaurdians, ArrayList<Dependent> dependents){
        this.admins = admins;
        this.gaurdians = gaurdians;
        this.dependents = dependents;
    }

    public String getFullName(Person p){
        return p.getFirstName() + " " + p.getLastName();
    }

    public String getFirstName(Person p){
        return p.getFirstName();
    }

    public String getLastName(Person p){
        return p.getLastName();
    }

    public String getAddress(Person p){
        return p.getAddress();
    }

    public UUID getId(Person p){
        return p.getId();
    }

    public boolean getHasLoginBehavior(Person p){
        return p.getHasLoginBehavior();    
    }


    public void seeCoordinators(){
        // to do
        for(Dependent d:dependents){
            if(d.getIsCoordinator()){
                System.out.println(d);
            }
        }
    }

    public void seeAdmins(){
        for(CampAdmin c: admins){
            System.out.println(c);
        }
    }

    public boolean registerGuardian(){
        // to do
        return false;
    }
    public boolean registerDependent(){
        // to do
        return false;
    }
    public boolean removeDependent(String firstName, String lastName){
        // to do
        return false;
    }

    public boolean removeGuardian(String firstName, String lastName){
        // to do
        return false;
    }

    public boolean removeCamper(String firstName, String lastName){
        // to do
        return false;
    }
    public boolean payForCamper(String camperFirstName, String camperLastName){
        // to do
        return false;
    }
    public boolean refundGaurdian(String camperFirstName, String camperLastName){
        // to do
        return false;
    }
    public boolean signUpDependentForCabin(String guardianUsername, String guardianPassword){
        // to do
        return false;
    }
    public void viewDependents(String guardianUsername, String guardianPassword){
        // to do
    }
    public boolean addDependent(String guardianUsername, String guardianPassword){
        // to do
        return false;
    }
    public boolean getDependentCabin(String firstName, String lastName){
        // to do
        return false;
    }
    public boolean showDependentContacts(String firstName, String lastName){
        // to do
        return false;
    }
    public boolean getHasBeenPaidFor(String firstName, String lastName){
        /*
         * Assumes that all dependents have unique names
         */
        for(Dependent d:this.dependents){
            if(d.checkFullName(firstName, lastName)){
                return d.getHasBeenPaidFor();
            }
        }
        return false;
    }
    public boolean updateLoginInfo(String curUsername, String curPassword){
        // to do
        return false;
    }
    
}

import java.util.ArrayList;
import java.util.UUID;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Guardian>  guardians = new ArrayList<Guardian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();

    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> guardians, ArrayList<Dependent> dependents,ArrayList<EmergencyContact> emergencyContacts){
        this.admins = admins;
        this.guardians = guardians;
        this.dependents = dependents;
        this.emergencyContacts = emergencyContacts;
    }
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> guardians, ArrayList<EmergencyContact>emergencyContacts){
        this.admins = admins;
        this.guardians = guardians;
        this.emergencyContacts = emergencyContacts;
    }
    public PersonManager(){

    }
    public void setAdmins(ArrayList<CampAdmin> admins){
        this.admins = admins;
    }

    public void setEmergencyContacts(ArrayList<EmergencyContact> emergencyContacts){
        this.emergencyContacts = emergencyContacts;
    }
    public void setDependents(ArrayList<Dependent> dependents){
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
    public boolean refundGuardian(String camperFirstName, String camperLastName){
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

    public Dependent getDependentById(UUID id){
        for (Dependent dep :this.dependents) {
            if(dep.getId().equals(id)) {
                return dep;
            }
        }
        return null;
    }
    public EmergencyContact getEmergencyContactById(UUID id){
        for (EmergencyContact p:this.emergencyContacts) {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    public Guardian getGuardianById(UUID id){
        for (Guardian g:this.guardians) {
            if(g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }
    public Dependent GetCoordinatorByUserNamePassword(String username, String password) {
		for (Dependent dependent : dependents) {
			if(dependent.authBehavior.login(username,password)) {
				return dependent;
			}
		}
		return null;
	}
    public Guardian GetGuardianByUserNamePassword(String username, String password) {
		for (Guardian guardian :guardians) {
			if(guardian.authBehavior.login(username,password)) {
				return guardian;
			}
		}
		return null;
	}
    public CampAdmin GetCampAdminByUserNamePassword(String username, String password) {
		for (CampAdmin campAdmin : admins) {
            if(campAdmin.authBehavior.login(username,password)) {
                return campAdmin;
            }
        }
        return null;
	}

}

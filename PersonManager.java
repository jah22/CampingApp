import java.util.ArrayList;
import java.util.UUID;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Guardian>  gaurdians = new ArrayList<Guardian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
    
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> gaurdians, ArrayList<Dependent> dependents,ArrayList<EmergencyContact> ems){
        this.admins = admins;
        this.gaurdians = gaurdians;
        this.dependents = dependents;
        this.emergencyContacts = ems;
    }
    // empty ctor
    public PersonManager(){

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
        for(Dependent d: this.dependents){
            if(d.getIsCoordinator()){
                System.out.println(d);
            }
        }
    }

    public void seeAdmins(){
        for(CampAdmin a: this.admins){
            System.out.println(a);
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
    public void setDependents(ArrayList<Dependent> deps){
        this.dependents = deps;
    }
    public void setAdmins(ArrayList<CampAdmin> admins){
        this.admins = admins;
    }
    public void setGuardian(ArrayList<Guardian> gs){
        this.gaurdians = gs;
    }
    public void setEmergencyContacts(ArrayList<EmergencyContact> ems){
        this.emergencyContacts = ems;
    }
    public boolean removeDependent(UUID id){
        for(Dependent d:this.dependents){
            if(d.getId().equals(id)){
                this.dependents.remove(d);
                return true;
            }
        }
        return false;
    }

    public boolean removeGuardian(UUID id){
        for(Guardian g: this.gaurdians){
            if(g.getId().equals(id)){
                this.gaurdians.remove(g);
                return true;
            }
        }
        return false;
    }

    public boolean removeCamper(UUID id){
        for(Dependent d: this.dependents){
            if(d.getId().equals(id)){
                this.dependents.remove(d);
            }
            return true;
        }
        // to do
        return false;
    }
    public boolean signUpDependentForCabin(String guardianUsername, String guardianPassword){
        // to do
        return false;
    }
    public boolean viewDependents(UUID guardianId){
        for(Guardian g: this.gaurdians){
            if(g.getId().equals(guardianId)){
                g.viewDependents();
                return true;
            }
        }
        return false;
    }
    public void addDependent(Guardian g, String depFirstName, String depLastName, String birthDate, String address,ArrayList<String>medNotes, ArrayList<EmergencyContact> ems){
        Dependent newDep = new Dependent(depFirstName, depLastName, birthDate, address,medNotes,ems) ;
        g.addDependent(newDep);
    }
    public boolean getDependentCabin(String firstName, String lastName){
        // to do
        return false;
    }
    public boolean showDependentContacts(String firstName, String lastName){
        // to do
        return false;
    }
    public Dependent getDependentById(UUID id){
        for(Dependent d: this.dependents){
            if(d.getId().equals(id)){
                return d;
            }
        }
        return null;
    }
    public Guardian getGuardianById(UUID id){
        for(Guardian g: this.gaurdians){
            if(g.getId().equals(id)){
                return g;
            }
        }
        return null;
    }
    public EmergencyContact getEmergencyContactById(UUID id){
        for(EmergencyContact e: this.emergencyContacts){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
    public boolean updateLoginInfo(String curUsername, String curPassword){
        // to do
        return false;
    }
    public boolean getMedicalNotes(String username, String password){
        // to do
        return false;
    }
    public boolean getContactInformation(String username, String password){
        // to do
        return false;
    }
    public Guardian loginGuardian(String username, String password){
        for(Guardian g: this.gaurdians){
            if(g.getAuthBehavior().login(username, password)){
                return g;
            }
        }
        return null;
    }

    public Dependent loginDependent(String username, String password){
        for(Dependent d: this.dependents){
            if(d.getAuthBehavior().login(username, password)){
                return d;
            }
        }
        return null;
    }
    
    public CampAdmin loginAdmin(String username, String password){
        for(CampAdmin a: this.admins){
            if(a.getAuthBehavior().login(username, password)){
                return a;
            }
        }
        return null;
    }

    public boolean logout(String username, String password){
        // go thru admins
        for(CampAdmin admin:this.admins){
            if(admin.getAuthBehavior().login(username, password)){
                admin.getAuthBehavior().logout();
                return true;
            }
        }
        // go thru guardians
        for(Guardian g: this.gaurdians){
            if(g.getAuthBehavior().login(username, password)){
                g.getAuthBehavior().logout();
                return true;
            }
        }
        // go thru dependents
        for(Dependent dep: this.dependents){
            if(dep.getAuthBehavior().login(username,password)){
                dep.getAuthBehavior().logout();
                return true;
            }
        }
        return false;
    }
}

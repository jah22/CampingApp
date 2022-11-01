/**
 * Manager of People
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.UUID;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Guardian>  guardians = new ArrayList<Guardian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
    
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> guardians, ArrayList<Dependent> dependents,ArrayList<EmergencyContact> ems){
        this.admins = admins;
        this.guardians = guardians;
        this.dependents = dependents;
        this.emergencyContacts = ems;
    }
    public void reset(){
        // only admins can reset, so keep admins
        this.guardians = new ArrayList<Guardian>();
        this.dependents = new ArrayList<Dependent>();
        this.emergencyContacts = new ArrayList<EmergencyContact>();
    }
    // empty ctor
    public PersonManager(){

    }
    public void viewCamperNamesByGuardian(UUID id){
        Guardian g = this.getGuardianById(id);
        ArrayList<Dependent> reg = g.getRegisteredDependents();
        for(int i=0;i<reg.size();i++){
            if(!reg.get(i).getIsCoordinator()){
                System.out.println(reg.get(i).getFullName());
            }
        }
    }
    public Dependent getDependentByName(UUID guardianId,String firstName, String lastName){
        for(Dependent d: this.dependents){
            if(d.getFirstName().equals(firstName) && (d.getLastName().equals(lastName))){
                return d;
            }
        }

        return null;
    }

    public Dependent guardianGetDependentByInt(UUID guardianId, int dependentIndex){
        Guardian g = this.getGuardianById(guardianId);
        ArrayList<Dependent> reg = g.getRegisteredDependents();
        if(dependentIndex >= reg.size()){
            return null;
        }
        return reg.get(dependentIndex);
    }
    public int guardianGetNumberDependents(UUID gId){
        Guardian g = this.getGuardianById(gId);
        return g.getRegisteredDependents().size();
    }

    public boolean guardianHasDependents(Guardian g){
        return g.getRegisteredDependents().size() > 0;
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


    public void viewCoordinators(){
        for(Dependent d: this.dependents){
            if(d.getIsCoordinator()){
                System.out.println(d);
            }
        }
    }
    public Dependent registerCoordinator(String firstName, String lastName, String username, String password, String birthDate, String phone, String email){
        Dependent dep = new Dependent(firstName, lastName, birthDate, birthDate, username, password, email, phone);
        this.dependents.add(dep);
        return dep;
    }

    public void viewAdmins(){
        for(CampAdmin a: this.admins){
            System.out.println(a);
        }
    }

    public Guardian registerGuardian(String firstName, String lastName, String birthDate, String username, String password, String email, String phone,String address){
        Guardian g = new Guardian(firstName, lastName, birthDate, address, password, username, email, phone);
        // add guardian
        this.guardians.add(g);
        // to do
        return g;
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
        this.guardians = gs;
    }
    public void setEmergencyContacts(ArrayList<EmergencyContact> ems){
        this.emergencyContacts = ems;
    }
    public boolean removeDependent(Guardian parent, Dependent dep){
        boolean successfullyRemoved = false;
        for(Dependent d:this.dependents){
            if(d.equals(dep)){
                this.dependents.remove(dep);
                successfullyRemoved = true;
                break;
            }
        }
        for(Guardian g: this.guardians){
            g.removeDependent(dep);
        }
        return successfullyRemoved;
    }

    public boolean removeGuardian(UUID id){
        for(Guardian g: this.guardians){
            if(g.getId().equals(id)){
                this.guardians.remove(g);
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
        for(Guardian g: this.guardians){
            if(g.getId().equals(guardianId)){
                g.viewDependents();
                return true;
            }
        }
        return false;
    }
    public void addDependent(UUID guardianID, String depFirstName, String depLastName, String birthDate, String address,ArrayList<String>medNotes, ArrayList<EmergencyContact> ems){
        // need to be by reference
        Dependent newDep = new Dependent(depFirstName, depLastName, birthDate, address,medNotes,ems) ;
        for(int i=0;i<this.guardians.size();i++){
            if(this.guardians.get(i).getId().equals(guardianID)){
                this.guardians.get(i).addDependent(newDep);
            }
        }
        // dont forget to add the dependent
        this.dependents.add(newDep);
        // add the emergency contacts
        this.emergencyContacts.addAll(ems);
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
        for(Guardian g: this.guardians){
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
    public Guardian loginGuardian(String username, String password){
        for(Guardian g: this.guardians){
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
        for(Guardian g: this.guardians){
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
    public void viewEmergencyContacts(Dependent dep){
        for(Dependent d: this.dependents){
            if(d.equals(dep)){
                d.viewEmergencyContacts();
                return;
            }
        }
    }
    public CampAdmin registerAdmin(CampAdmin admin){
        this.admins.add(admin);
        return admin;
    }
    public void save(){
        // save all data to json
        FileIO.writeCampAdmin(this.admins);
        FileIO.writeEmergencyContact(this.emergencyContacts);
        FileIO.writeDependent(this.dependents);
        FileIO.writeGuardian(this.guardians);
    }
}

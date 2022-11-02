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
    
    /*
     * Param ctor
     * @param admins: array list of camp admins to add
     * @param guardians: array list of guardians to add
     * @param dependents: array list of dependents to add
     * @param ems: array list of emergency contacts to add
     */
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> guardians, ArrayList<Dependent> dependents,ArrayList<EmergencyContact> ems){
        this.admins = admins;
        this.guardians = guardians;
        this.dependents = dependents;
        this.emergencyContacts = ems;
    }
    /*
     * Reset the object by reiniting everything
     */
    public void reset(){
        // only admins can reset, so keep admins
        this.guardians = new ArrayList<Guardian>();
        this.dependents = new ArrayList<Dependent>();
        this.emergencyContacts = new ArrayList<EmergencyContact>();
    }
    // empty ctor
    public PersonManager(){

    }
    /*
     * View campers by guardian
     * @param id: UUID of guardian
     */
    public void viewCamperNamesByGuardian(UUID id){
        Guardian g = this.getGuardianById(id);
        ArrayList<Dependent> reg = g.getRegisteredDependents();
        for(int i=0;i<reg.size();i++){
            if(!reg.get(i).getIsCoordinator()){
                System.out.println(reg.get(i).getFullName());
            }
        }
    }
    /*
     * Get a dependent of a guardian by name
     * @param guardianId: UUID of guardian
     * @param firstName: String first name of dep
     * @param lastName: String last name of dep
     * @return Dependent: the dependent or null
     */
    public Dependent getDependentByName(UUID guardianId,String firstName, String lastName){
        for(Dependent d: this.dependents){
            if(d.getFirstName().equals(firstName) && (d.getLastName().equals(lastName))){
                return d;
            }
        }

        return null;
    }

    /*
     * Get a dependent by index
     * @param guardianId: UUID of guardian
     * @param depdentIndex: int index of dependent
     * @return Dependent: the dependent or null
     */
    public Dependent guardianGetDependentByInt(UUID guardianId, int dependentIndex){
        Guardian g = this.getGuardianById(guardianId);
        ArrayList<Dependent> reg = g.getRegisteredDependents();
        if(dependentIndex >= reg.size()){
            return null;
        }
        return reg.get(dependentIndex);
    }
    /*
     * Get Number of dependents
     * @param gId: UUID of guardian
     * @return int of the number of dependents
     */
    public int guardianGetNumberDependents(UUID gId){
        Guardian g = this.getGuardianById(gId);
        return g.getRegisteredDependents().size();
    }

    /*
     * Check if guardian has deps
     * @param g: the guardian of interest
     * @return bool: if guardian has dependents
     */
    public boolean guardianHasDependents(Guardian g){
        return g.getRegisteredDependents().size() > 0;
    }


    /*
     * Get full name of person
     * @param p: Person of interest
     * @return String: full name of person
     */
    public String getFullName(Person p){
        return p.getFirstName() + " " + p.getLastName();
    }

    /*
     * Get first name of person
     * @param p: Person of interest
     * @return String first name
     */
    public String getFirstName(Person p){
        return p.getFirstName();
    }

    /*
     * Get first name of person
     * @param p: Person of interest
     * @return String last name
     */
    public String getLastName(Person p){
        return p.getLastName();
    }

    /*
     * Get first name of person
     * @param p: Person of interest
     * @return String address
     */
    public String getAddress(Person p){
        return p.getAddress();
    }

    /*
     * Get first name of person
     * @param p: Person of interest
     * @return UUID id of person
     */
    public UUID getId(Person p){
        return p.getId();
    }

    /*
     * Get first name of person
     * @param p: Person of interest
     * @return bool if person has login behavior
     */
    public boolean getHasLoginBehavior(Person p){
        return p.getHasLoginBehavior();    
    }

    /*
     * View all coordinators
     */
    public void viewCoordinators(){
        for(Dependent d: this.dependents){
            if(d.getIsCoordinator()){
                System.out.println(d);
            }
        }
    }
    /*
     * Register a coordinator
     * @param firstName string first name
     * @param lastName string last name
     * @param username string username
     * @param password string password
     * @param birthDate string birthdate
     * @param phone string phone
     * @param email string email
     * @return Dependent the new coordinator
     */
    public Dependent registerCoordinator(String firstName, String lastName, String username, String password, String birthDate, String phone, String email){
        Dependent dep = new Dependent(firstName, lastName, birthDate, birthDate, username, password, email, phone);
        this.dependents.add(dep);
        return dep;
    }

    /*
     * View all admins
     */
    public void viewAdmins(){
        for(CampAdmin a: this.admins){
            System.out.println(a);
        }
    }

    /*
     * Register a guardian
     * @param firstName string first name
     * @param lastName string last name
     * @param username string username
     * @param password string password
     * @param phone string phone
     * @param email string email
     * @param birthDate string birthdate
     * @return Guardian registered guardian
     */
    public Guardian registerGuardian(String firstName, String lastName, String birthDate, String username, String password, String email, String phone,String address){
        Guardian g = new Guardian(firstName, lastName, birthDate, address, password, username, email, phone);
        // add guardian
        this.guardians.add(g);
        // to do
        return g;
    }
    /*
     * Set dependents
     * @param deps: ArrayList<Dependent> of dependents
     */
    public void setDependents(ArrayList<Dependent> deps){
        this.dependents = deps;
    }
    /*
     * Set the admins
     * @param admins: array list of admin objects
     */
    public void setAdmins(ArrayList<CampAdmin> admins){
        this.admins = admins;
    }
    /*
     * Set guardians
     * @param gs: array list of guardians
     */
    public void setGuardian(ArrayList<Guardian> gs){
        this.guardians = gs;
    }
    /*
     * Set the emergency contacts
     * @param ems: ArrayList of Emergency Contacts
     */
    public void setEmergencyContacts(ArrayList<EmergencyContact> ems){
        this.emergencyContacts = ems;
    }
    /*
     * Remove a dependent from guardian
     * @param dep the Dependent to remove
     * @param parent the Guardian to remove dpeendent from
     * @return bool: if dependent successfully removed
     */
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

    /*
     * Remove guardian
     * @param id: UUID of guardian
     * @return bool: if removal successful
     */
    public boolean removeGuardian(UUID id){
        for(Guardian g: this.guardians){
            if(g.getId().equals(id)){
                this.guardians.remove(g);
                return true;
            }
        }
        return false;
    }

    /*
     * Remove camper
     * @param id: UUID of camper
     * @return bool: if removal successful
     */
    public boolean removeCamper(UUID id){
        for(Dependent d: this.dependents){
            if(d.getId().equals(id)){
                this.dependents.remove(d);
            }
            return true;
        }
        return false;
    }
    /*
     * view dependents of guardian
     * @param guardianId: UUID of the guardian to view dependents
     * @return bool: if dependents exist
     */
    public boolean viewDependents(UUID guardianId){
        for(Guardian g: this.guardians){
            if(g.getId().equals(guardianId)){
                g.viewDependents();
                return true;
            }
        }
        return false;
    }
    /*
     * Add a dependent
     * @param guardianID: UUID of guardian
     * @param depFirstName: first name of the dependent
     * @param depLastName: last name of dep
     * @param birthDate: bday of dep
     * @param address: address of dep
     * @param medNotes: array list of medical notes of the dep
     * @param ems: array list of emergency contacts of dep
     */
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
    /*
     * Get dependent by id
     * @param id: UUID of dependent
     * @return Dependent: dependent of intereste
     */
    public Dependent getDependentById(UUID id){
        for(Dependent d: this.dependents){
            if(d.getId().equals(id)){
                return d;
            }
        }
        return null;
    }
    /*
     * Get guardian by ID
     * @param id: UUID of guardian
     * @return Guardian: guardian of interest
     */
    public Guardian getGuardianById(UUID id){
        for(Guardian g: this.guardians){
            if(g.getId().equals(id)){
                return g;
            }
        }
        return null;
    }
    /*
     * Get emergency contact by ID
     * @param id: UUID of emergency contact
     * @return EmergencyContact: contact of interest
     */
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

    /*
     * Login a dependent
     * @param username: username of dep
     * @param password: password of dep
     * @return Dependent: logged in dep
     */
    public Dependent loginDependent(String username, String password){
        for(Dependent d: this.dependents){
            if(d.getAuthBehavior().login(username, password)){
                return d;
            }
        }
        return null;
    }
    
    /*
     * Login a Camp Admin
     * @param username: username of admin
     * @param password: password of admin
     * @return Camp Admin: logged in admin
     */
    public CampAdmin loginAdmin(String username, String password){
        for(CampAdmin a: this.admins){
            if(a.getAuthBehavior().login(username, password)){
                return a;
            }
        }
        return null;
    }

    /*
     * Logout a user
     * @param username: String of username
     * @param password: String of password
     * @bool: if login was successful
     */
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
    /*
     * View the emergency contacts of the dependent
     * @param dep: the dependent of interest
     */
    public void viewEmergencyContacts(Dependent dep){
        for(Dependent d: this.dependents){
            if(d.equals(dep)){
                d.viewEmergencyContacts();
                return;
            }
        }
    }
    /*
     * Register admin
     * @param admin: admin of camp
     * @return CampAdmin: registered admin
     */
    public CampAdmin registerAdmin(CampAdmin admin){
        this.admins.add(admin);
        return admin;
    }
    /*
     * Save the people to json
     */
    public void save(){
        // save all data to json
        FileIO.writeCampAdmin(this.admins);
        FileIO.writeEmergencyContact(this.emergencyContacts);
        FileIO.writeDependent(this.dependents);
        FileIO.writeGuardian(this.guardians);
    }
}

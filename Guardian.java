
/**
 * Guardian class
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
import java.util.ArrayList;
import java.util.UUID;

public class Guardian extends Person {

    private ArrayList<Dependent> registeredDependents = new ArrayList<Dependent>();

    /*
     * Param ctor
     * @param firstName: string representing the first name
     * @param lastName: string representing the last name
     * @param birthDate: string representing the birthdate
     * @param address: string representing the address
     * @param id: UUID representing the id
     * @param password: string representing the password
     * @param username: UUID representing the username
     * @param email: string representing the email
     * @param phone: string representing the phone
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    /*
     * Param ctor
     * @param firstName: string representing the first name
     * @param lastName: string representing the last name
     * @param birthDate: string representing the birthdate
     * @param address: string representing the address
     * @param id: UUID representing the id
     * @param password: string representing the password
     * @param username: UUID representing the username
     * @param email: string representing the email
     * @param phone: string representing the phone
     * @param registeredDependents: array list of guardian's dependents
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone,ArrayList<Dependent> dependents) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.registeredDependents = dependents; 
        this.setAuthBehavior(behavior);
    }
    /*
     * Param ctor
     * @param firstName: string representing the first name
     * @param lastName: string representing the last name
     * @param birthDate: string representing the birthdate
     * @param address: string representing the address
     * @param password: string representing the password
     * @param username: UUID representing the username
     * @param email: string representing the email
     * @param phone: string representing the phone
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    /*
     * Set the authority behavior of person
     * @param behavior: AuthBehavior to be set
     */
    public void setAuthBehavior(AuthBehavior behavior) {
        super.setAuthBehavior(behavior);
    }
    /*
     * set the dependents
     * @param registeredDependents: ArrayList of dependents to be set
     */
    public void setRegisteredDependents(ArrayList<Dependent> registeredDependents) {
        this.registeredDependents = registeredDependents;
    }
    /*
     * get the dependents
     * @return ArrayList<Dependent> the dependents of the guardian
     */
    public ArrayList<Dependent> getRegisteredDependents() {
        return this.registeredDependents;
    }
    /*
     * View the guardian's dependents
     */
    public void viewDependents(){
        for(Dependent d: this.registeredDependents){
            System.out.println(d);
        }
    }
    /*
     * Check if a dependent is present
     * @param d: Dependent to check
     * @return bool: if the Dependent is there
     */
    public boolean hasDependent(Dependent d){
        for(Dependent dep: this.registeredDependents){
            if(dep.equals(d)){
                return true;
            }
        }
        return false;
    }
    /*
     * Get the type of person
     * @return String: represents the type of person
     */
    public String getPersonType(){
        return "Guardian";
    }
    /*
     * add a dependent
     * @param dep: The dependent to add
     */
    public void addDependent(Dependent dep){
        this.registeredDependents.add(dep);
    }
    /*
     * Remove a dependent
     * @param dep: the dependent to remove
     */
    public void removeDependent(Dependent dep){
        this.registeredDependents.remove(dep);
    }
}

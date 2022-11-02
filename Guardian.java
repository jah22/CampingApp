/**
 * Guardian
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.UUID;

public class Guardian extends Person {

    private ArrayList<Dependent> registeredDependents = new ArrayList<Dependent>();

    /**
     * Parameterized constructor
     * @param firstName the first name of the guardian
     * @param lastName the last name of the guardian
     * @param birthDate the birth date of the guardian
     * @param address the address of the guardian
     * @param id the id of the guardian
     * @param password the password of the guardian
     * @param username the user name of the guardian
     * @param email the email of the guardian
     * @param phone the phone of the guardian
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    /**
     * Parameterized constructor
     * @param firstName the first name of the guardian
     * @param lastName the last name of the guardian
     * @param birthDate the birth date of the guardian
     * @param address the address of the guardian
     * @param id the id of the guardian
     * @param password the password of the guardian
     * @param username the user name of the guardian
     * @param email the email of the guardian
     * @param phone the phone of the guardian
     * @param dependents an ArrayList of dependet
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone,ArrayList<Dependent> dependents) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.registeredDependents = dependents; 
        this.setAuthBehavior(behavior);
    }
    /**
     * Parameterized constructor
     * @param firstName the first name of the guardian
     * @param lastName the last name of the guardian
     * @param birthDate the birth date of the guardian
     * @param address the address of the guardian
     * @param password the password of the guardian
     * @param username the user name of the guardian
     * @param email the email of the guardian
     * @param phone the phone of the guardian
     */
    public Guardian(String firstName, String lastName,String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    /**
     * to set the Auth Behavior
     */
    public void setAuthBehavior(AuthBehavior behavior) {
        // TODO Auto-generated method stub
        super.setAuthBehavior(behavior);
    }
    /**
     * to set the Registered Dependents
     * @param registeredDependents a Registered Dependents
     */
    public void setRegisteredDependents(ArrayList<Dependent> registeredDependents) {
        this.registeredDependents = registeredDependents;
    }
    /**
     * to get the Registered Dependents
     * @return the Registered Dependents
     */
    public ArrayList<Dependent> getRegisteredDependents() {
        return this.registeredDependents;
    }
    /**
     * print out dependents
     */
    public void viewDependents(){
        for(Dependent d: this.registeredDependents){
            System.out.println(d);
        }
    }
    /**
     * chech for dependents
     * @param d a dependent
     * @return true if the dependent in a Registered Dependent
     */
    public boolean hasDependent(Dependent d){
        for(Dependent dep: this.registeredDependents){
            if(dep.equals(d)){
                return true;
            }
        }
        return false;
    }
    /**
     * get person type
     */
    public String getPersonType(){
        return "Guardian";
    }
    /**
     * to add a dependent
     * @param dep a denpendent
     */
    public void addDependent(Dependent dep){
        this.registeredDependents.add(dep);
    }
    /**
     * to remove a dependent
     * @param dep a denpendent
     */
    public void removeDependent(Dependent dep){
        this.registeredDependents.remove(dep);
    }
}

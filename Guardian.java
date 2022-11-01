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

    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone,ArrayList<Dependent> dependents) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.registeredDependents = dependents; 
        this.setAuthBehavior(behavior);
    }
    public Guardian(String firstName, String lastName,String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    public void setAuthBehavior(AuthBehavior behavior) {
        // TODO Auto-generated method stub
        super.setAuthBehavior(behavior);
    }
    public void setRegisteredDependents(ArrayList<Dependent> registeredDependents) {
        this.registeredDependents = registeredDependents;
    }
    public ArrayList<Dependent> getRegisteredDependents() {
        return this.registeredDependents;
    }
    public void viewDependents(){
        for(Dependent d: this.registeredDependents){
            System.out.println(d);
        }
    }
    public boolean hasDependent(Dependent d){
        for(Dependent dep: this.registeredDependents){
            if(dep.equals(d)){
                return true;
            }
        }
        return false;
    }
    public String getPersonType(){
        return "Guardian";
    }
    public void addDependent(Dependent dep){
        this.registeredDependents.add(dep);
    }
    public void removeDependent(Dependent dep){
        this.registeredDependents.remove(dep);
    }
}

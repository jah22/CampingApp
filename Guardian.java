import java.util.ArrayList;
import java.util.UUID;

public class Guardian extends Person{

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
    
    public void viewDependents(){
        for(Dependent d: this.registeredDependents){
            System.out.println(d);
        }
    }

}

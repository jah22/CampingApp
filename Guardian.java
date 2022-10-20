import java.util.UUID;

public class Guardian extends Person{


    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
<<<<<<< HEAD:Gaurdian.java
    public Gaurdian(String firstName, String lastName,String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    public boolean pay(double amount){
        // to do
        return false;
    }
=======
>>>>>>> main:Guardian.java
}

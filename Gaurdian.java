import java.util.UUID;

public class Gaurdian extends Person{


    public Gaurdian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    public boolean pay(double amount){
        // to do
        return false;
    }

}

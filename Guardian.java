import java.util.UUID;

public class Guardian extends Person{


    public Guardian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    public void pay(double amount){
        System.out.println("Charging for " + "$"+amount);
        System.out.println("Successfully charged.");
        System.out.println("Thank you!");
    }

}

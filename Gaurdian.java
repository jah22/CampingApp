import java.util.UUID;

public class Gaurdian extends Person{

    private Card card;

    public Gaurdian(String firstName, String lastName,String birthDate, String address, UUID id, String password, String username, String email, String phone, Card card) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);

        this.card = card;
    }
    public boolean pay(double amount){
        return this.card.charge(amount);
    }
}

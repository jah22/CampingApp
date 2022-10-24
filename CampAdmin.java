import java.util.UUID;

public class CampAdmin extends Person{

    private String authCode;

    public CampAdmin(String firstName, String lastName, String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    public CampAdmin(String firstName, String lastName, String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }

    public boolean checkAuthCode(String authCode){
        return this.authCode == authCode;
    }
     
    public String getPersonType(){
        return "CampAdmin";
    }
}

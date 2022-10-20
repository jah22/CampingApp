import java.util.UUID;

public class CampAdmin extends Person{

    private String authCode;

    public CampAdmin(String firstName, String lastName, String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        //TODO Auto-generated constructor stub
    }
    public CampAdmin(String firstName, String lastName, String birthDate, String address) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        //TODO Auto-generated constructor stub
    }

    public boolean checkAuthCode(String authCode){
        return this.authCode == authCode;
    }
     
}

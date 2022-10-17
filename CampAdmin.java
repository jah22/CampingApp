import java.util.UUID;

public class CampAdmin extends Person{

    private String authCode;

    public CampAdmin(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
        //TODO Auto-generated constructor stub
    }

    public boolean checkAuthCode(String authCode){
        return this.authCode == authCode;
    }
     
}

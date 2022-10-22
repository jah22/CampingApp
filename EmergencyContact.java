import java.util.UUID;

public class EmergencyContact extends Person {

    public EmergencyContact(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        //TODO Auto-generated constructor stub
    }
}

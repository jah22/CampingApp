import java.util.UUID;

public class EmergencyContact extends Person {
    private String phoneNumber;

    public EmergencyContact(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        //TODO Auto-generated constructor stub
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, UUID id, String phoneNumber) {
        super(firstName, lastName, birthDate, address, id);
        this.phoneNumber = phoneNumber;
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phoneNumber) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        this.phoneNumber = phoneNumber;
        //TODO Auto-generated constructor stub
    }
}

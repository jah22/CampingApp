import java.util.UUID;

public class EmergencyContact extends Person {
    private String phone;

    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phone,UUID id) {
        super(firstName, lastName, birthDate, address, id);
        this.phone= phone;
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phoneNumber) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        this.phone= phoneNumber;
    }
    public String getPhone(){
        return this.phone;
    }
    public String toString(){
        String out = "";
        out += "Full name: " + this.getFullName() + "\n";
        out += "Address: " + this.getAddress() + "\n";
        out += "Phone: " + this.getPhone() +"\n";
        return out;
    }
}

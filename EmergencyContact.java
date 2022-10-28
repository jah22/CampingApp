import java.util.UUID;

public class EmergencyContact extends Person {
    private String phone;
    private String relation;

    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phone,String relation,UUID id) {
        super(firstName, lastName, birthDate, address, id);
        this.phone= phone;
        this.relation = relation;
    }
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phoneNumber,String relation) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        this.phone= phoneNumber;
        this.relation = relation;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getRelation(){
        return this.relation;
    }
    public String toString(){
        String out = "";
        out += "Full name: " + this.getFullName() + "\n";
        out += "Address: " + this.getAddress() + "\n";
        out += "Phone: " + this.getPhone() +"\n";
        out += "Relation: " + this.getRelation() +"\n";
        return out;
    }
}

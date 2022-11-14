/**
 * EmergencyContact
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.UUID;

public class EmergencyContact extends Person {
    private String phone;
    private String relation;

    /*
     * @param firstName: String representing the first name of the contact
     * @param lastName: String representing the last name of the contact
     * @param birthDate: String representing the birthDate of the contact
     * @param address: String representing the address of the contact
     * @param phone: String representing the phone number of the contact
     * @param relation: String representing the relation of the contact
     * @param id: UUID representing the id of the contact
     */
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phone,String relation,UUID id) {
        super(firstName, lastName, birthDate, address, id);
        this.phone= phone;
        this.relation = relation;
    }
    /*
     * @param firstName: String representing the first name of the contact
     * @param lastName: String representing the last name of the contact
     * @param birthDate: String representing the birthDate of the contact
     * @param address: String representing the address of the contact
     * @param phone: String representing the phone number of the contact
     * @param relation: String representing the relation of the contact
     */
    public EmergencyContact(String firstName, String lastName, String birthDate, String address, String phoneNumber,String relation) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        this.phone= phoneNumber;
        this.relation = relation;
    }
    /*
     * @return String representing the phone number of the person
     */
    public String getPhone(){
        return this.phone;
    }
    /*
     * @return String representing the relation of the person
     */
    public String getRelation(){
        return this.relation;
    }
    /*
     * @return String representing the emergency contact
     */
    public String toString(){
        String out = "";
        out += "Full name: " + this.getFullName() + "\n";
        out += "Address: " + this.getAddress() + "\n";
        out += "Phone: " + this.getPhone() +"\n";
        out += "Relation: " + this.getRelation() +"\n";
        return out;
    }
    public boolean isEqual(EmergencyContact e) {
        if(!e.getFirstName().equals(this.firstName)) return false;
        if(!e.getAddress().equals(this.address)) return false;
        if(!e.getPhone().equals(this.phone)) return false;
        if(!e.getBirthDate().equals(this.birthDate)) return false;
        if(!e.getLastName().equals(this.lastName)) return false;
        if(!e.getRelation().equals(this.relation)) return false;
        if(!e.getId().equals(this.id)) return false;
        return true;
    }
}

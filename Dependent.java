/**
 * Dependent
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.UUID;

public class Dependent extends Person{

    private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
    private ArrayList<String> medicalNotes = new ArrayList<String>();

    // keep track of if you are coordinator or not
    private boolean isCoordinator = false;

    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param id: UUID representing the id of the user
     */
    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
        this.authBehavior = new NoPriorityBehavior();
    }
    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param username: String representing the username of the dependent
     * @param password: String representing the password of the dependent
     * @param email: String representing the email of the dependent
     * @param phone: String representing the phone of the dependent
     */
    public Dependent(String firstName, String lastName, String birthdate, String address, String username, String password, String email, String phone){
        super(firstName, lastName, birthdate, address, UUID.randomUUID());
        this.authBehavior = new PriorityBehavior(username,password, phone, email);
    }
    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param medNotes: ArrayList<String>
     * @param ems: ArrayList<EmergencyContact>
     */
    public Dependent(String firstName, String lastName, String birthDate, String address,ArrayList<String> medNotes, ArrayList<EmergencyContact> ems) {
        super(firstName, lastName, birthDate, address,UUID.randomUUID());
        this.authBehavior = new NoPriorityBehavior();
        this.medicalNotes = medNotes;
        this.emergencyContacts = ems;
    }

    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param id: UUID representing the id of the dependent
     * @param isCoordinator: boolean representing if coordinator or not
     * @param emergencyContacts: ArrayList<EmergencyContact>
     * @param medNotes: ArrayList<String>
     */
    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes){
        super(firstName, lastName, birthDate, address,id);
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
        this.authBehavior = new NoPriorityBehavior();
    }

    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param id: UUID representing the id of the dependent
     * @param isCoordinator: boolean representing if coordinator or not
     * @param emergencyContacts: ArrayList<EmergencyContact>
     * @param medNotes: ArrayList<String>
     * @param authBehavior: AuthBehavior behavior of the dependent
     */
    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes,AuthBehavior auth){
        super(firstName, lastName, birthDate, address,id);
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
        this.authBehavior = auth;
    }
    /*
     * @param firstName: String representing the first name of the dependent
     * @param lastName: String representing the last name of the dependent
     * @param birthDate: String representing the birthdate of the dependent
     * @param address: String representing the address of the dependent
     * @param id: UUID representing the id of the dependent
     * @param isCoordinator: boolean representing if coordinator or not
     * @param emergencyContacts: ArrayList<EmergencyContact>
     * @param medNotes: ArrayList<String>
     */
    public Dependent(String firstName, String lastName, String birthDate, String address, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes){
        super(firstName, lastName, birthDate, address,UUID.randomUUID());
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
    }
    public boolean isEqual(Dependent d) {
        if(!d.getFirstName().equals(this.firstName)) return false;
        if(!d.getLastName().equals(this.lastName)) return false;
        if(!d.getBirthDate().equals(this.birthDate)) return false;
        if(!d.getAddress().equals(this.address)) return false;
        if(d.getIsCoordinator() != (this.isCoordinator)) return false;
        if(!d.getId().equals(this.id)) return false;
        return true;
    }

    /*
     * @return ArrayList<String> representing medical notes of the user
     */
    public ArrayList<String> getMedicalNotes(){
        return this.medicalNotes;
    }

    /*
     * @return ArrayList<EmergencyContact> representing the emergency contacts of the user
     */
    public ArrayList<EmergencyContact> getEmergencyContacts(){
        return this.emergencyContacts;
    }
    /*
     * View the emergency contacts of a user
     */
    public void viewEmergencyContacts(){
        for (Person emergencyContact: this.emergencyContacts) {
            System.out.println(emergencyContact) ;
        }
    }
    /*
     * @return bool: get if the user is a coordinator or not
     */
    public boolean getIsCoordinator(){
        return this.isCoordinator;
    }

    /*
     * @return String: representing the dependent
     */
    public String toString(){
        String div = "------------\n";
        String out = div;
        out += (isCoordinator) ? "Coordinator\n":"Camper\n";
        out += super.toString() + " | Age: " + this.getAgeInt() + "\n";
        if(this.emergencyContacts != null){
            out += "Emergency Contacts: \n";
            for (Person person : emergencyContacts) {
                out += person.toString()+"\n";
            }
        }
        if(this.medicalNotes != null){
            out += "Medical Notes:\n";
            for(String note: medicalNotes){
                out += note +"\n";
            }
        }
        out += "Auth Information:\n";
        out += this.authBehavior.toString() +"\n";
        out += div;
        return out;
    }
    /*
     * @return String: representing the person type
     */
    public String getPersonType(){
        return "Dependent";
    }
}

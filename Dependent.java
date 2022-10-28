import java.util.ArrayList;
import java.util.UUID;

public class Dependent extends Person{

    private ArrayList<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
    private ArrayList<String> medicalNotes = new ArrayList<String>();

    private boolean isCoordinator = false;

    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
        this.authBehavior = new NoPriorityBehavior();
    }
    public Dependent(String firstName, String lastName, String birthdate, String address, String username, String password, String email, String phone){
        super(firstName, lastName, birthdate, address, UUID.randomUUID());
        this.authBehavior = new PriorityBehavior(username,password, phone, email);
    }
    public Dependent(String firstName, String lastName, String birthDate, String address,ArrayList<String> medNotes, ArrayList<EmergencyContact> ems) {
        super(firstName, lastName, birthDate, address,UUID.randomUUID());
        this.authBehavior = new NoPriorityBehavior();
        this.medicalNotes = medNotes;
        this.emergencyContacts = ems;
    }

    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes){
        super(firstName, lastName, birthDate, address,id);
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
        this.authBehavior = new NoPriorityBehavior();
    }
    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes,AuthBehavior auth){
        super(firstName, lastName, birthDate, address,id);
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
        this.authBehavior = auth;
    }
    public Dependent(String firstName, String lastName, String birthDate, String address, boolean isCoordinator,ArrayList<EmergencyContact> emergencyContacts, ArrayList<String> medNotes){
        super(firstName, lastName, birthDate, address,UUID.randomUUID());
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
    }

    public ArrayList<String> getMedicalNotes(){
        return this.medicalNotes;
    }

    public ArrayList<EmergencyContact> getEmergencyContacts(){
        return this.emergencyContacts;
    }
    public void viewEmergencyContacts(){
        for (Person emergencyContact: this.emergencyContacts) {
            System.out.println(emergencyContact) ;
        }
    }
    public boolean getIsCoordinator(){
        return this.isCoordinator;
    }

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
    public String getPersonType(){
        return "Dependent";
    }
}

import java.util.ArrayList;
import java.util.UUID;

public class Dependent extends Person{

    private ArrayList<Person> emergencyContacts = new ArrayList<Person>();
    private ArrayList<String> medicalNotes = new ArrayList<String>();

    private boolean hasBeenPaidFor = false;
    private boolean isCoordinator = false;

    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
    }
    public Dependent(String firstName, String lastName, String birthDate, String address) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
    }

    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id, boolean isCoordinator, boolean hasBeenPaidFor,ArrayList<Person> emergencyContacts, ArrayList<String> medNotes){
        super(firstName, lastName, birthDate, address,id);
        this.hasBeenPaidFor = hasBeenPaidFor;
        this.isCoordinator = isCoordinator;
        this.medicalNotes = medNotes;
        this.emergencyContacts = emergencyContacts;
    }

    public ArrayList<String> getMedicalNotes(){
        return this.medicalNotes;
    }

    public ArrayList<Person> getEmergencyContacts(){
        return this.emergencyContacts;
    }
    public boolean inCabin(Cabin cabin){
        // todo
        return false;
    }
    public boolean getHasBeenPaidFor(){
        return this.hasBeenPaidFor;
    }

    public boolean getIsCoordinator(){
        return this.isCoordinator;
    }

    public String toString(){
        return super.toString() + " | Age: " + this.getAgeInt();
    }
}

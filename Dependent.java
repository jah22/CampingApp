import java.util.ArrayList;
import java.util.UUID;

public class Dependent extends Person{

    private ArrayList<Person> emergencyContacts = new ArrayList<Person>();
    private ArrayList<String> medicalNotes = new ArrayList<String>();

    private ArrayList<Cabin> cabins = new ArrayList<Cabin>();
    private boolean hasBeenPaidFor = false;
    private boolean isCoordinator = false;

    public Dependent(String firstName, String lastName, String birthDate, String address, UUID id) {
        super(firstName, lastName, birthDate, address, id);
    }
    public Dependent(String firstName, String lastName, String birthDate, String address) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
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
     
}

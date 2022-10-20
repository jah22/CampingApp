import java.util.ArrayList;
import java.util.UUID;

public class Cabin {
<<<<<<< HEAD
    private UUID cabinId; 
=======
    private String name; 
>>>>>>> main
    private ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
    private ArrayList<Dependent> campers = new ArrayList<Dependent>();
    private ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
    // portia says that all camps have up to 8 campers
    private int camperCapacity = 8;
    // portia says that all camps have up to 1 coordinator
    private int coordinatorCapacity = 1;
    // portia says we need an age range
    private int lowerAgeBound;
    private int upperAgeBound;

<<<<<<< HEAD
    public String getCabinId(){

        return this.cabinId.toString();
=======
    public String getCabinName(){
        return this.name;
>>>>>>> main
    }

    public ArrayList<Dependent> getCoordinators(){
        return this.coordinators;
    }

    public ArrayList<Dependent> getCampers(){
        return this.campers;
    }
    public ArrayList<Schedule> getSchedules(){
        return this.schedules;
    }
<<<<<<< HEAD
    public Cabin(UUID cabinId){
        this.cabinId = cabinId;
    }
    public Cabin(UUID cabinId,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity){
        this.cabinId = cabinId;
=======
    public void seeActivities(){
        // to do:
        System.out.println("SEE ACTIVITIES");
    }
    public void seeCoordinators(){
        for(Dependent c : this.coordinators){
            System.out.println(c);
        }
    }
    public Cabin(String name){
        this.name = name;
    }
    public Cabin(String name,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity,int lowerAgeBound, int upperAgeBound){
        this.name = name;
>>>>>>> main
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
        this.lowerAgeBound = lowerAgeBound;
        this.upperAgeBound = upperAgeBound;
    }
    public Cabin(ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity){
        this.cabinId = UUID.randomUUID();
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
    }
    public String toString(){
        // to do
        String division = "----------------------";
        String out = division +"\n";
        out += "Cabin: " + this.name + "\n";
        out += division +"\n";
        out += "Age range: " + this.lowerAgeBound + "-" + this.upperAgeBound +"\n";
        out += division +"\n";
        out += "Coordinators: " + this.coordinators.size() +"/"+ this.coordinatorCapacity + "\n";
        for (Dependent dependent :this.coordinators) {
            out += dependent.toString()  + "\n";
        }
        out += division +"\n";
        out += "Campers: " + this.campers.size() +"/"+ this.camperCapacity+ "\n";
        for (Dependent dependent :this.campers) {
            out += dependent.toString()  + "\n";
        }
        out += division +"\n";

        return out;
    }
    public void addSchedule(Schedule schedule){
        this.schedules.add(schedule);
    }
    public int getTotalCampers(){
        return this.campers.size();
    }
    public int getTotalCoordinators(){
        return this.coordinators.size();
    }
    public int getRemainingCamperCapacity(){
        return this.camperCapacity - this.campers.size();
    }
    public int getRemainingCoordinatorCapacity(){
        return this.coordinatorCapacity - this.coordinators.size();
    }
    public int getCamperCapacity(){
        return this.camperCapacity;
    }
    public int getCoordinatorCapacity(){
        return this.coordinatorCapacity;
    }

    public boolean addCamperToCabin(Dependent camper){
        if(
            (this.lowerAgeBound <= camper.getAgeInt())
            &&
            (camper.getAgeInt() <= this.upperAgeBound)
            &&
            this.campers.size() < this.camperCapacity
            ){
            // call add function
            this.campers.add(camper);
            return true;
        }
        // else the camper either is not in age range or there are too many campers
        return false;

    }

}

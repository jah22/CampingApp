import java.util.ArrayList;

public class Cabin {
    private String cabinId; 
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

    public String getCabinId(){
        return this.cabinId;
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
    public void seeActivities(){
        // to do:
        System.out.println("SEE ACTIVITIES");
    }
    public void seeCoordinators(){
        for(Dependent c : this.coordinators){
            System.out.println(c);
        }
    }
    public Cabin(String cabinId){
        this.cabinId = cabinId;
    }
    public Cabin(String cabinId,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity){
        this.cabinId = cabinId;
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
    }
    public String toString(){
        // to do
        String division = "------------";
        String out = division +"\n";
        out += "Cabin: " + this.cabinId + "\n";
        out += division +"\n";
        out += "Coordinators: " + this.coordinators.size() +"/"+ this.coordinatorCapacity + "\n";
        out += division +"\n";
        out += "Campers: " + this.campers.size() +"/"+ this.camperCapacity+ "\n";
        out += division +"\n";
        out += "Age range: " + this.lowerAgeBound + "-" + this.upperAgeBound +"\n";

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

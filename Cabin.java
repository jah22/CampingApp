import java.util.ArrayList;
import java.util.UUID;

public class Cabin {
    private UUID cabinId; 
    private ArrayList<Dependent> coordinators = new ArrayList<Dependent>();
    private ArrayList<Dependent> campers = new ArrayList<Dependent>();
    private ArrayList<Schedule>  schedules= new ArrayList<Schedule>();
    private int camperCapacity;
    private int coordinatorCapacity;

    public String getCabinId(){

        return this.cabinId.toString();
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
    public Cabin(UUID cabinId){
        this.cabinId = cabinId;
    }
    public Cabin(UUID cabinId,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity){
        this.cabinId = cabinId;
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
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
        return "";
    }
    public boolean addSchedule(Schedule schedule){
        // to do
        return false;
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

}

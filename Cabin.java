import java.util.ArrayList;

public class Cabin {
    private String name; 
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

    public String getCabinName(){
        return this.name;
    }
    public boolean hasGuardianDependents(Guardian g){
        for(Dependent dep: this.campers){
            if(g.hasDependent(dep)){
                return true;
            }
        }
        return false;
    }
    public boolean hasDependent(Dependent d){
        for(Dependent dep: this.campers){
            if(d.equals(dep)){
                return true;
            }
        }
        for(Dependent dep: this.coordinators){
            if(d.equals(dep)){
                return true;
            }
        }
        return false;
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
    public void viewActivities(){
        System.out.println(this.schedules.size());
        for(Schedule s:this.schedules){
            System.out.println(s);
        }
    }
    public void viewCoordinators(){
        for(Dependent c : this.coordinators){
            System.out.println(c);
        }
    }
    public Cabin(String name){
        this.name = name;
    }
    public Cabin(String name,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity,int lowerAgeBound, int upperAgeBound){
        this.name = name;
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
        this.lowerAgeBound = lowerAgeBound;
        this.upperAgeBound = upperAgeBound;
    }
    public Cabin(String name,ArrayList<Dependent>coordinators, ArrayList<Dependent> campers,ArrayList<Schedule> schedules, int camperCapacity, int coordinatorCapacity,int lowerAgeBound, int upperAgeBound, ArrayList<String> themes, int campYear ){
        this.name = name;
        this.coordinators = coordinators;
        this.campers = campers;
        this.schedules = schedules;
        this.camperCapacity = camperCapacity;
        this.coordinatorCapacity = coordinatorCapacity;
        this.lowerAgeBound = lowerAgeBound;
        this.upperAgeBound = upperAgeBound;
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
            out += dependent.getFullName() + "\n";
        }
        out += division +"\n";
        out += "Campers: " + this.campers.size() +"/"+ this.camperCapacity+ "\n";
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
    public void viewCabinCouncelors(){
        for(Dependent d: coordinators){
            System.out.println(d.toString() + "\n");
        }
    }

    public boolean addCamperToCabin(Dependent camper){
        if(
            this.inAgeRange(camper.getAgeInt())
            &&
            this.hasSpace()
            &&
            !this.inCabin(camper)
            ){
            // call add function
            this.campers.add(camper);
            return true;
        }
        // else the camper either is not in age range or there are too many campers
        return false;

    }
    public boolean inCabin(Dependent dep){
        return (this.campers.contains(dep) || this.coordinators.contains(dep));
    }
    public boolean hasSpace(){
        return this.campers.size() < this.camperCapacity;
    }
    public boolean inAgeRange(int age){
        return ((this.lowerAgeBound <= age) && (this.upperAgeBound >= age));
    }
    public boolean checkCanAddDependent(Dependent d){
        return(hasSpace() && inAgeRange(d.getAgeInt()));
    }
    public String getCabinRoster(){
        String out = "Roster for \"" + this.name + "\"\n";
        out += "Age range: " + this.lowerAgeBound + " to " + this.upperAgeBound +" years old\n";
        out += "Coordinator count: " + this.coordinators.size()+ "/" + this.coordinatorCapacity + "\n";
        out += "Camper count: " + this.campers.size()+ "/" + this.camperCapacity +"\n";
        out += "Coordinator(s): \n";
        for(Dependent dep: this.coordinators){
            out += dep.getFullName() + "\n";
        }
        out += "Campers: \n";
        for(Dependent dep: this.campers){
            out += dep.getFullName() + "\n";
        }

        return out;
    }

    public String getVitalInfo(){
        String out = "Vital information for " + this.name + "\n";
        out += "Medical notes: \n";
        for(Dependent d: this.campers){
            out += "Camper: " + d.getFullName() +"\n";
            out += "    Age: " + d.getAgeInt();
            for(String note: d.getMedicalNotes()){
                out += "    " + note + "\n";
            }
            out += "    Emergency Contacts: \n";
            for(EmergencyContact contact: d.getEmergencyContacts()){
                out += "    " + contact.toString() + "\n";
            }
        }
        return out;
    }
    public String getSchedulesString(){
        String out = "";
        for(Schedule s: this.schedules){
            out += s.toString() + "\n";
        }
        return out;
    }
    public void setLowerAgeBound(int bound){
        this.lowerAgeBound = bound;
    }
    public void setUpperAgeBound(int bound){
        this.upperAgeBound = bound;
    }
}

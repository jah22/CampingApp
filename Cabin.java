/**
 * A Cabin class containing a String name, an ArrayList<Dependent> coordinators, an ArrayList<Dependent> campers,
 * an ArrayList<Schedule> schedules, an int camperCapacity, an int coordinatorCapacity, an int lowerAgeBound
 * and an int upperAgeBound
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Parameterized constructor
     * @param name the name of the cabin
     * @param coordinators the ArrayList<Dependent> of coordinators
     * @param campers ArrayList<Dependent> of campers
     * @param schedules ArrayList<Schedule> of schedules
     * @param camperCapacity an int camperCapacity
     * @param coordinatorCapacity an int coordinatorCapacity
     * @param lowerAgeBound an int lowerAgeBound
     * @param upperAgeBound an int upperAgeBound
     * @param campYear an int campYear
     */
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

    /**
     * to get the name of the cabin
     * @return the name of the cabin
     */
    public String getCabinName(){
        return this.name;
    }
    /**
     * check for guardian dependents
     * @param g a Guardian 
     * @return true if guardian has dependents
     */
    public boolean hasGuardianDependents(Guardian g){
        for(Dependent dep: this.campers){
            if(g.hasDependent(dep)){
                return true;
            }
        }
        return false;
    }
    /**
     * check for deependent
     * @param d a Depentent
     * @return ture if depentent d where found
     */
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
    /**
     * get the ArrayList<Dependent>
     * @return the ArrayList<Dependent>
     */
    public ArrayList<Dependent> getCoordinators(){
        return this.coordinators;
    }
    /**
     * get the ArrayList<Dependent>
     * @return get the ArrayList<Dependent>
     */
    public ArrayList<Dependent> getCampers(){
        return this.campers;
    }
    /**
     * get the ArrayList<Schedule>
     * @return get the ArrayList<Schedule>
     */
    public ArrayList<Schedule> getSchedules(){
        return this.schedules;
    }
    /**
     * print out the activities in the schedule
     */
    public void viewActivities(){
        for(Schedule s:this.schedules){
            System.out.println(s);
        }
    }
    /**
     * print out the coordinators
     */
    public void viewCoordinators(){
        for(Dependent c : this.coordinators){
            System.out.println(c);
        }
    }
    /**
     * Parameterized constructor
     * @param name the name of the cabin
     */
    public Cabin(String name){
        this.name = name;
    }
    // for new cabin generation
    public Cabin(String name, int lowerBound, int upperBound,int sessionCounts){
        this.name = name;
        this.lowerAgeBound = lowerBound;
        this.upperAgeBound = upperBound;
        this.generateRandomSchedulesForSession(sessionCounts);
    }

    public void generateRandomSchedulesForSession(int sessionCounts){
        // randomly generate round wake up, breakfast, lunch, dinner, sleep
        for(int i=0;i<sessionCounts;i++){
            // one for each day of week
            Schedule s = this.generateRandomSchedule(i);
            this.schedules.add(s);
        }
    }
    public Schedule generateRandomSchedule(int sessionNumber){
        Schedule s = new Schedule(sessionNumber,this.name);
        for(int i = 0 ; i < 7 ; i++){
            String day = "";
            if(i == 0){
                day = "Saturday" ;
                s.addDayActivityManager(day);
            }
            else if(i == 1){
                day = "Sunday";
                s.addDayActivityManager(day);
            }
            else if(i == 2){
                day = "Monday";
                s.addDayActivityManager(day);
            }
            else if(i == 3){
                day = "Tuesday";
                s.addDayActivityManager(day);
            }
            else if(i == 4){
                day = "Wednesday";
                s.addDayActivityManager(day);
            }
            else if(i == 5){
                day = "Thursday";
                s.addDayActivityManager(day);
            }
            else if(i == 6){
                day = "Friday";
                s.addDayActivityManager(day);
            }
            for(int j = s.getStartTime(); j <= s.getEndTime();j++){
                // some preset times
                if(j == ActivityManager.START_TIME) {
                    // wake up always first
                    s.addActivity(day,Activity.WAKEUP__CABIN.toString());
                }
                else if(j == ActivityManager.BREAKFAST_TIME){
                    // breakfast after wake up
                    s.addActivity(day,Activity.BREAKFAST__CABIN.toString());
                }
                else if(j == ActivityManager.LUNCH_TIME){
                    s.addActivity(day,Activity.LUNCH__CABIN.toString());
                }
                else if(j == ActivityManager.DINNER_TIME){
                    s.addActivity(day,Activity.DINNER__CABIN.toString());
                }
                else{
                    // randomize
                    String randActivity= Activity.values()[new Random().nextInt(Activity.values().length)].toString();
                    while(s.hasActivity(day,randActivity)){
                        randActivity = Activity.values()[new Random().nextInt(Activity.values().length)].toString();
                    }
                    s.addActivity(day,randActivity);
                }
            }
        }
        return s;
    }
    public String toString(){
        return this.getCabinRoster();
    }
    /**
     * to add Schedule
     * @param schedule a schedule
     */
    public void addSchedule(Schedule schedule){
        this.schedules.add(schedule);
    }
    /**
     * to get total campers
     * @return the size of campers
     */
    public int getTotalCampers(){
        return this.campers.size();
    }
    /**
     * to get total coordinators
     * @return the siez of coordinators
     */
    public int getTotalCoordinators(){
        return this.coordinators.size();
    }
    /**
     * to get remaining camper capacity
     * @return remaining camper capacity
     */
    public int getRemainingCamperCapacity(){
        return this.camperCapacity - this.campers.size();
    }
    /**
     * to get remaining Coordinator capacity
     * @returnremaining Coordinator capacity
     */
    public int getRemainingCoordinatorCapacity(){
        return this.coordinatorCapacity - this.coordinators.size();
    }
    /**
     * to get remaining Camper capacity
     * @returnremaining Camper capacity
     */
    public int getCamperCapacity(){
        return this.camperCapacity;
    }
    /**
     * to get Coordinator capacity
     * @return Coordinator capacity
     */
    public int getCoordinatorCapacity(){
        return this.coordinatorCapacity;
    }
    /**
     * print out cabin counselor
     */
    public void viewCabinCouncelors(){
        for(Dependent d: coordinators){
            System.out.println(d.toString() + "\n");
        }
    }
    public int getLowerAgeBound() {
        return this.lowerAgeBound;
    }
    public int getUpperAgeBound() {
        return this.upperAgeBound;
    }
    /**
     * to add camper to cabin
     * @param camper the camper to be added
     * @return true if added successfully
     */
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
    /**
     * check if dependent is in cabin
     * @param dep
     * @return true if the dependent is in cabin
     */
    public boolean inCabin(Dependent dep){
        return (this.campers.contains(dep) || this.coordinators.contains(dep));
    }
    /**
     * check if campers still has space
     * @return true if campers still has space 
     */
    public boolean hasSpace(){
        return this.campers.size() < this.camperCapacity;
    }
    /**
     * check if the age is in age bound
     * @param age an age
     * @return true if the age is in age bound
     */
    public boolean inAgeRange(int age){
        return ((this.lowerAgeBound <= age) && (this.upperAgeBound >= age));
    }
    /**
     * check if the dependent can be added successfully
     * @param d a Dependent
     * @return ture if dependent can be added successfully
     */
    public boolean checkCanAddDependent(Dependent d){
        return(hasSpace() && inAgeRange(d.getAgeInt()));
    }
    /**
     *get the cabin roster
     * @return a string of the cabin roster
     */
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
            out += dep.getFullName() + " age: " + dep.getAgeInt() + "\n";
        }

        return out;
    }
    /**
     * get the vital information
     * @return a string of the vital information 
     */
    public String getVitalInfo(){
        String out = "Vital information for " + this.name + "\n";
        out += "Medical notes: \n";
        for(Dependent d: this.campers){
            out += "Camper: " + d.getFullName() +"\n";
            out += "Age: " + d.getAgeInt() +"\n";
            for(String note: d.getMedicalNotes()){
                out += note + "\n";
            }
            out += "Emergency Contacts: \n";
            for(EmergencyContact contact: d.getEmergencyContacts()){
                out +=  contact.toString() + "\n";
            }
        }
        return out;
    }
    /**
     * get the schedules
     * @return a string of the schedules
     */
    public String getSchedulesString(){
        String out = "";
        for(Schedule s: this.schedules){
            out += s.toString() + "\n";
        }
        return out;
    }
    /**
     * set the lower age bound 
     * @param bound an age boung
     */
    public void setLowerAgeBound(int bound){
        this.lowerAgeBound = bound;
    }
    /**
     * set the upper age bound 
     * @param bound an age bound
     */
    public void setUpperAgeBound(int bound){
        this.upperAgeBound = bound;
    }
    public int getSessionCount(){
        return this.schedules.size();
    }
    public void viewSessionAtIndex(int sessionIndex){
        if(0<= sessionIndex && sessionIndex < this.schedules.size()){
            System.out.println(this.schedules.get(sessionIndex));
        }
    }
}

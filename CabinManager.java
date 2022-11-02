/**
 * Manager of Cabins
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;

public class CabinManager {
    private ArrayList<Cabin> cabins = new ArrayList<Cabin>();

    /*
     * Parameterized ctor
     * @param cabins: ArrayList<Cabin> of cabins
     */
    public CabinManager(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }
    /**
     * Empty ctor
     */
    public CabinManager(){
        // empty ctor
    }
    /**
     * Allows for viewing of cabins that have guardians
     * @param g is the Guardian passed in
     */
    public void viewGuardianRegisteredCabins(Guardian g){
        // the indices of the cabins that the gaurdians has ov
        for(int i=0;i<this.cabins.size();i++){
            if(this.cabins.get(i).hasGuardianDependents(g)){
                System.out.println("["+i+"]: " + this.cabins.get(i).getCabinName());
            }
        }
    }
    /**
     * Allows coordinator to view cabins
     * @param coordinator is a coordinator of the camp
     */
    public void viewCabinsByCoordinator(Dependent coordinator){
        for(int i=0;i<this.cabins.size();i++){
            if(this.cabins.get(i).hasDependent(coordinator)){
                System.out.println("[" + i + "] " + this.cabins.get(i).getCabinName());
            }
        }
    }
    /**
     * Allows you to get the number of cabins
     * @return the number of cabins
     */
    public int getCabinCount(){
        return this.cabins.size();
    }
    /**
     * Allows for viewing of cabin schedules per coordinator
     * @param coordinator is the coordinator who's cabin we want to view
     */
    public void viewCabinSchedulesByCoordinator(Dependent coordinator){
        for(Cabin c: this.cabins){
            if(c.hasDependent(coordinator)){
                c.viewActivities();
            }
        }
    }
    /**
     * Allows for viewing of all cabins
     */
    public void viewCabins(){
        for(Cabin c: this.cabins){
            System.out.println(c);
        }
    }
    /**
     * Returns cabin when an index is entered
     * @param index for the cabin
     * @return cabin based on index
     */
    public Cabin getCabinByIndex(int index){
        if(!(this.cabins.size() <= index)){
            return this.cabins.get(index);
        }
        return null;
    }
    /**
     * Allows for viewing of cabin session 
     * @param cabinIndex is the cabin index
     * @param sessionIndex is the session index
     */
    public void viewIndexCabinSession(int cabinIndex, int sessionIndex){
        if(
            (0 <= cabinIndex && cabinIndex < this.cabins.size())
            &&
            (0 <= sessionIndex && sessionIndex < this.cabins.get(cabinIndex).getSessionCount())
        ){
            this.cabins.get(cabinIndex).viewSessionAtIndex(sessionIndex);
        }
    }
    /**
     * Allows for viewing of Cabin Coordinators
     * @param cabinName is the name of the cabin
     * @return is true/false based on existence of cabim
     */
    public boolean viewCabinCoordinators(String cabinName){
        for(Cabin c: this.cabins){
            if(c.getCabinName().equals(cabinName)){
                c.viewCoordinators();
                return true;
            }
        }
        return false;
    }
    /**
     * Adds a camper to a cabin
     * @param camper is the camper to add
     * @param cabin is the cabin camper is added too
     * @return true without exceptions
     */
    public boolean addCamperToCabin(Dependent camper,Cabin cabin){
        return cabin.addCamperToCabin(camper);
    }
    /**
     * Check cabins dependent based on guardian
     * @param g is the guardian
     * @return true if the dependent can be added
     */
    public boolean checkCabinsForDependents(Guardian g){
        // check if there is space
        for(Cabin c: this.cabins){
            for(Dependent d: g.getRegisteredDependents()){
                if(c.checkCanAddDependent(d)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if the guradian has a camper registered
     * @param g
     * @return
     */
    public boolean guardianHasCampersRegistered(Guardian g){
        for(Cabin c: this.cabins){
            for(Dependent d: g.getRegisteredDependents()){
                if(c.hasDependent(d)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Allows for viewing the cabins names
     */
    public void viewCabinNames(){
        for(int i=0;i<this.cabins.size();i++){
            System.out.println("[" + i + "]: " + this.cabins.get(i).getCabinName());
        }
    }
    /**
     * Allows for viewing of cabins by index
     * @param index is the index of the cabin you want to look at
     */
    public void viewCabinByIndex(int index){
        if(index < 0 || index >= this.cabins.size()){
            return;
        }
        System.out.println(this.cabins.get(index));
    }
    /**
     * Gets the cabins by coordinator
     * @param coordinator is the coordinator we want to look at
     * @return list of cabins attached to the coordinator
     */
    public ArrayList<Cabin> getCabinsByCoordinator(Dependent coordinator){
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        for(Cabin c: this.cabins){
            if(c.hasDependent(coordinator)){
                cabins.add(c);
            }
        }
        return cabins;
    }
    /**
     * Gets camp rosters 
     * @param coordinator is the coordiantor of the cabins
     * @return the rosters for cabins by coordinator
     */
    public String getCampRosters(Dependent coordinator) {
        String out = "";
        ArrayList<Cabin> coordCabins= getCabinsByCoordinator(coordinator);
        for(Cabin c: coordCabins){
            out += c.getCabinRoster() + "\n";
        }
        return out;
    }
    /**
     * Get Cabins with dependent
     * @param user is the user checking cabins
     * @return list of cabins that are attached to user
     */
    public ArrayList<Cabin> getDependentCabins(Dependent user){
        ArrayList<Cabin> ret = new ArrayList<Cabin>();
        for(Cabin c: this.cabins){
            if(c.hasDependent(user)){
                ret.add(c);
            }
        }
        return ret;
    }
    /**
     * Gets count of cabins that contain a dependent
     * @param user is the dependent
     * @return the number of associated cabins
     */
    public int getCabinCountByDependent(Dependent user){
        int ret = 0;
        for(Cabin c: this.cabins){
            if(c.hasDependent(user)){
                ret +=1;
            }
        }
        return ret;
    }
    /**
     * gets the cabin roster
     * @param c is the cabin
     * @return the roster
     */
    public String getCabinRoster(Cabin c){
        return c.getCabinRoster();
    }
    /**
     * add the cabin to list of cabins
     * @param c
     */
    public void addCabin(Cabin c){
        this.cabins.add(c);
    }
    /**
     * saves the cabins
     */
    public void save(){
        // save cabins
        FileIO.writeCabin(this.cabins);
        // save schedules
        FileIO.writeSchedule(this.getAllSchedules());
    }
    /**
     * Collects all schedules
     * @return list of schedules
     */
    public ArrayList<Schedule> getAllSchedules(){
        ArrayList<Schedule> ret = new ArrayList<>();
        for(Cabin c: this.cabins){
            ret.addAll(c.getSchedules());
        }
        return ret;
    }
}
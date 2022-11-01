/**
 * Manager of Cabins
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;

public class CabinManager {
    private ArrayList<Cabin> cabins = new ArrayList<Cabin>();
    
    public CabinManager(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }
    public CabinManager(){
        // empty ctor
    }
    public void viewGuardianRegisteredCabins(Guardian g){
        // the indices of the cabins that the gaurdians has ov
        for(int i=0;i<this.cabins.size();i++){
            if(this.cabins.get(i).hasGuardianDependents(g)){
                System.out.println("["+i+"]: " + this.cabins.get(i).getCabinName());
            }
        }
    }
    public void viewCabinsByCoordinator(Dependent coordinator){
        for(int i=0;i<this.cabins.size();i++){
            if(this.cabins.get(i).hasDependent(coordinator)){
                System.out.println("[" + i + "] " + this.cabins.get(i).getCabinName());
            }
        }
    }
    public int getCabinCount(){
        return this.cabins.size();
    }
    public void viewCabinSchedulesByCoordinator(Dependent coordinator){
        for(Cabin c: this.cabins){
            if(c.hasDependent(coordinator)){
                c.viewActivities();
            }
        }
    }

    public void viewCabins(){
        for(Cabin c: this.cabins){
            System.out.println(c);
        }
    }
    public Cabin getCabinByIndex(int index){
        if(!(this.cabins.size() <= index)){
            return this.cabins.get(index);
        }
        return null;
    }

    public void viewIndexCabinSession(int cabinIndex, int sessionIndex){
        if(
            (0 <= cabinIndex && cabinIndex < this.cabins.size())
            &&
            (0 <= sessionIndex && sessionIndex < this.cabins.get(cabinIndex).getSessionCount())
        ){
            this.cabins.get(cabinIndex).viewSessionAtIndex(sessionIndex);
        }
    }
    public boolean viewCabinCoordinators(String cabinName){
        for(Cabin c: this.cabins){
            if(c.getCabinName().equals(cabinName)){
                c.viewCoordinators();
                return true;
            }
        }
        return false;
    }
    public boolean addCamperToCabin(Dependent camper,Cabin cabin){
        return cabin.addCamperToCabin(camper);
    }
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
    public void viewCabinNames(){
        for(int i=0;i<this.cabins.size();i++){
            System.out.println("[" + i + "]: " + this.cabins.get(i).getCabinName());
        }
    }
    public void viewCabinByIndex(int index){
        if(index < 0 || index >= this.cabins.size()){
            return;
        }
        System.out.println(this.cabins.get(index));
    }
    public ArrayList<Cabin> getCabinsByCoordinator(Dependent coordinator){
        ArrayList<Cabin> cabins = new ArrayList<Cabin>();
        for(Cabin c: this.cabins){
            if(c.hasDependent(coordinator)){
                cabins.add(c);
            }
        }
        return cabins;
    }
    public String getCampRosters(Dependent coordinator) {
        String out = "";
        ArrayList<Cabin> coordCabins= getCabinsByCoordinator(coordinator);
        for(Cabin c: coordCabins){
            out += c.getCabinRoster() + "\n";
        }
        return out;
    }
    public ArrayList<Cabin> getDependentCabins(Dependent user){
        ArrayList<Cabin> ret = new ArrayList<Cabin>();
        for(Cabin c: this.cabins){
            if(c.hasDependent(user)){
                ret.add(c);
            }
        }
        return ret;
    }
    public int getCabinCountByDependent(Dependent user){
        int ret = 0;
        for(Cabin c: this.cabins){
            if(c.hasDependent(user)){
                ret +=1;
            }
        }
        return ret;
    }
    public String getCabinRoster(Cabin c){
        return c.getCabinRoster();
    }
    public void addCabin(Cabin c){
        this.cabins.add(c);
    }
    public void save(){
        // save cabins
        FileIO.writeCabin(this.cabins);
        // save schedules
        FileIO.writeSchedule(this.getAllSchedules());
    }
    public ArrayList<Schedule> getAllSchedules(){
        ArrayList<Schedule> ret = new ArrayList<>();
        for(Cabin c: this.cabins){
            ret.addAll(c.getSchedules());
        }
        return ret;
    }
}
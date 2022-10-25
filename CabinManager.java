import java.util.ArrayList;

public class CabinManager {
    private ArrayList<Cabin> cabins;
    
    public CabinManager(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }
    public void viewGuardianRegisteredCabins(Guardian g){
        // the indices of the cabins that the gaurdians has ov
        for(int i=0;i<this.cabins.size();i++){
            if(this.cabins.get(i).hasGuardianDependents(g)){
                System.out.println("["+i+"]: " + this.cabins.get(i).getCabinName());
            }
        }
    }
    public void viewCabinByCoordinator(Dependent coordinator){
        for(Cabin c: this.cabins){
            if(c.hasDependent(coordinator)){
                System.out.println(c);
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

    public boolean viewCabinActivities(String cabinName){
        for(Cabin c: this.cabins){
            if(c.getCabinName().equals(cabinName)){
                c.viewActivities();
                return true;
            }
        }
        return false;
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
        if(index <=0 || index >= this.cabins.size()){
            return;
        }
        System.out.println(this.cabins.get(index));
    }
    public boolean removeCamperFromCabin(Dependent camper,String cabinName){
        // to do
        return false;
    }
    public boolean addCoordinatorToCabin(Dependent coordinator,String cabinName){
        // to do
        return false;
    }
    public boolean removeCoordinatorFromCabin(Dependent coordinator,String cabinName){
        // to do
        return false;
    }
}

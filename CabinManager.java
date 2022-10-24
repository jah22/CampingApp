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

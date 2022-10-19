import java.util.ArrayList;

public class CabinManager {
    private ArrayList<Cabin> cabins;
    
    public CabinManager(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }

    public void seeCabins(){
        for(Cabin c: this.cabins){
            System.out.println(c);
        }
    }

    public boolean seeCabinActivities(String cabinName){
        for(Cabin c: this.cabins){
            if(c.getCabinName().equals(cabinName)){
                c.seeActivities();
                return true;
            }
        }
        return false;
    }
    public boolean seeCabinCoordinators(String cabinName){
        for(Cabin c: this.cabins){
            if(c.getCabinName().equals(cabinName)){
                c.seeCoordinators();
                return true;
            }
        }
        return false;
    }
    public boolean addCamperToCabin(Dependent camper,String cabinName){
        // to do
        return false;
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

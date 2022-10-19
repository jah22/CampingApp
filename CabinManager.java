import java.util.ArrayList;

public class CabinManager {
    private ArrayList<Cabin> cabins = new ArrayList<Cabin>() ;
    
    public CabinManager(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }

    public void seeCabins(){
        for(Cabin c: this.cabins){
            System.out.println(c);
        }
    }

    public boolean seeCabinActivities(String cabinId){
        for(Cabin c: this.cabins){
            if(c.getCabinId().equals(cabinId)){
                c.seeActivities();
                return true;
            }
        }
        return false;
    }
    public boolean seeCabinCoordinators(String cabinId){
        for(Cabin c: this.cabins){
            if(c.getCabinId().equals(cabinId)){
                c.seeCoordinators();
                return true;
            }
        }
        return false;
    }
    public boolean addCamperToCabin(Dependent camper,String cabinId){
        // to do
        return false;
    }
    public boolean removeCamperFromCabin(Dependent camper,String cabinId){
        // to do
        return false;
    }
    public boolean addCoordinatorToCabin(Dependent coordinator,String cabinId){
        // to do
        return false;
    }
    public boolean removeCoordinatorFromCabin(Dependent coordinator,String cabinId){
        // to do
        return false;
    }
}

import java.util.ArrayList;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Guardian>  guardians = new ArrayList<Guardian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Guardian> guardians, ArrayList<Dependent> dependents){
        this.admins = admins;
        this.guardians = guardians;
        this.dependents = dependents;
    }
}

import java.util.ArrayList;

public class PersonManager {
    private ArrayList<CampAdmin> admins = new ArrayList<CampAdmin>(); 
    private ArrayList<Gaurdian>  gaurdians = new ArrayList<Gaurdian>(); 
    private ArrayList<Dependent>  dependents = new ArrayList<Dependent>(); 
    
    public PersonManager(ArrayList<CampAdmin> admins, ArrayList<Gaurdian> gaurdians, ArrayList<Dependent> dependents){
        this.admins = admins;
        this.gaurdians = gaurdians;
        this.dependents = dependents;
    }
}

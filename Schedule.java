import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Schedule {
    private HashMap<String,ActivityManager> scheduledActivities = new HashMap<>();
    private UUID id;

    public Schedule(HashMap<String,ActivityManager> scheduledActivities,UUID id){
        this.scheduledActivities = scheduledActivities;
        this.id = id;
    }
    public Schedule(){
        // empty ctor
    }

    public HashMap<String,ActivityManager> getScheduledActivities(){
        return this.scheduledActivities;
    }
    public String toString(){
        System.out.println("HERE");
        String out = "Schedule: " + this.id.toString() + "\n";
        for(Map.Entry<String,ActivityManager> set: this.scheduledActivities.entrySet()){
            out += set.getKey() + "\n";
            out += set.getValue().toString();
        }
        return out;
    }
}

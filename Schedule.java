import java.sql.Time;
import java.util.HashMap;

public class Schedule {
    private HashMap<String,TimedActivity> scheduledActivities = new HashMap<>();

    public Schedule(HashMap<String, TimedActivity> scheduledActivities){
        this.scheduledActivities = scheduledActivities;
    }

    public HashMap<String,TimedActivity> getScheduledActivities(){
        return this.scheduledActivities;
    }
    public boolean addActivity(String timing, TimedActivity activity){
        // to do
        return false;
    }
    public String toString(){
        // to do
        return "";
    }
    public static void main(String args[]){
        
    }
}

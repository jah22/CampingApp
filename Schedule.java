import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Schedule {
    private HashMap<String,ActivityManager> scheduledActivities = new HashMap<>();
    private UUID id;
    private int sessionNumber;

    public Schedule(HashMap<String,ActivityManager> scheduledActivities,int sessionNumber,UUID id){
        this.scheduledActivities = scheduledActivities;
        this.id = id;
        this.sessionNumber = sessionNumber;
    }
    public Schedule(int sessionNumber){
        this.sessionNumber = sessionNumber;
    }
    public void addDayActivityManager(String day){
        this.scheduledActivities.put(day,new ActivityManager());
    }

    public HashMap<String,ActivityManager> getScheduledActivities(){
        return this.scheduledActivities;
    }
    public String toString(){
        String out = "";
        for(Map.Entry<String,ActivityManager> set: this.scheduledActivities.entrySet()){
            out += set.getKey() + "\n";
            out += set.getValue().toString();
        }
        return out;
    }
    public void addActivity(String day,String activity){
        this.scheduledActivities.get(day).addActivityToEnd(activity);
    }
    public int getStartTime(){
        return ActivityManager.START_TIME;
    }
    public int getEndTime(){
        return ActivityManager.END_TIME;
    }
    public int getSessionNumber(){
        return this.sessionNumber;
    }
    public int getLunchTime(){
        return ActivityManager.LUNCH_TIME;
    }
    public boolean hasActivity(String day,String activity){
        return this.scheduledActivities.get(day).hasActivity(activity);
    }
}

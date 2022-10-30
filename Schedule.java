import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Schedule {
    private HashMap<String,ActivityManager> scheduledActivities = new HashMap<>();
    private UUID id;
    private int sessionNumber;
    private String cabinName;

    public Schedule(HashMap<String,ActivityManager> scheduledActivities,int sessionNumber,UUID id, String cabinName){
        this.scheduledActivities = scheduledActivities;
        this.id = id;
        this.sessionNumber = sessionNumber;
        this.cabinName = cabinName;
    }
    public Schedule(int sessionNumber,String cabinName){
        this.sessionNumber = sessionNumber;
        this.cabinName = cabinName;
        this.id = UUID.randomUUID();
    }
    public void addDayActivityManager(String day){
        this.scheduledActivities.put(day,new ActivityManager());
    }
    
    public HashMap<String,ActivityManager> getScheduledActivities(){
        return this.scheduledActivities;
    }
    public UUID getScheduleID() {
        return this.id;
    }
    public String getCabinName() {
        return this.cabinName;
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

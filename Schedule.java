/**
 * Schedule class
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.HashMap;
import java.util.UUID;
/**
 * Default variables for Schedule
 */
public class Schedule {
    private HashMap<String,ActivityManager> scheduledActivities = new HashMap<>();
    private UUID id;
    private int sessionNumber;
    private String cabinName;
    /**
     * Default constructor for Schedule
     * @param scheduledActivities, a hashMap containing a day of the week and list of activities
     * @param sessionNumber, the session number attributed to the schedule
     * @param id, the unique ID attributed to the schedule
     * @param cabinName, the cabin that uses the schedule 
     */
    public Schedule(HashMap<String,ActivityManager> scheduledActivities,int sessionNumber,UUID id, String cabinName){
        this.scheduledActivities = scheduledActivities;
        this.id = id;
        this.sessionNumber = sessionNumber;
        this.cabinName = cabinName;
    }
    /**
     * Creates an empty Schedule with a random UUID
     * @param sessionNumber, the session number attributed to the schedule
     * @param cabinName, the cabin that uses the schedule 
     */
    public Schedule(int sessionNumber,String cabinName){
        this.sessionNumber = sessionNumber;
        this.cabinName = cabinName;
        this.id = UUID.randomUUID();
    }
    public boolean isEqual(Schedule s) {
        if(!s.getScheduleID().equals(this.id)) return false;
        if(s.getSessionNumber() != (this.sessionNumber)) return false;
        if(!s.getCabinName().equals(this.cabinName)) return false;
        return true;
    }
    /**
     * Creates a new HashMap of scheduled activities using ActivityManager
     * @param day, a day of the week that you wish to create the activity list for
     */
    public void addDayActivityManager(String day){
        this.scheduledActivities.put(day,new ActivityManager());
    }
    /**
     * @return the scheduled activites of this isntance of Schedule
     */
    public HashMap<String,ActivityManager> getScheduledActivities(){
        return this.scheduledActivities;
    }
    /**
     * @return the id of this isntance of Schedule
     */
    public UUID getScheduleID() {
        return this.id;
    }
    /**
     * @return the cabin name of this isntance of Schedule
     */
    public String getCabinName() {
        return this.cabinName;
    }
    /**
     * Dictionaries have no order, we need to make this ordered
     * thus we have to iterate over days
     * @return out, a String representation of the scheduled activites for each day
     */
    public String toString(){
        String out = "Monday's Schedule\n";
        out += this.scheduledActivities.get("Monday");
        out += "Tuesday's Schedule\n";
        out += this.scheduledActivities.get("Tuesday");
        out += "Wednesday's Schedule\n";
        out += this.scheduledActivities.get("Wednesday");
        out += "Thursday's Schedule\n";
        out += this.scheduledActivities.get("Thursday");
        out += "Friday's Schedule\n";
        out += this.scheduledActivities.get("Friday");
        out += "Saturday's Schedule\n";
        out += this.scheduledActivities.get("Saturday");
        out += "Sunday's Schedule\n";
        out += this.scheduledActivities.get("Sunday");
        // for(Map.Entry<String,ActivityManager> set: this.scheduledActivities.entrySet()){
        //     out += "Day: " + set.getKey() + "\n";
        //     out += set.getValue().toString();
        // }
        return out;
    }
    /**
     * Adds a single activity to the list of scheduled activites
     * @param day the day of the week you want to add to
     * @param activity the activity you want to add
     */
    public void addActivity(String day,String activity){
        this.scheduledActivities.get(day).addActivityToEnd(activity);
    }
    /**
     * @return the start time tied to the activity
     */
    public int getStartTime(){
        return ActivityManager.START_TIME;
    }
    /**
     * @return the end time tied to the activity
     */
    public int getEndTime(){
        return ActivityManager.END_TIME;
    }
    /**
     * @return the session number attributed to this Schedule
     */
    public int getSessionNumber(){
        return this.sessionNumber;
    }
    /**
     * @return the time of the lunch time activity
     */
    public int getLunchTime(){
        return ActivityManager.LUNCH_TIME;
    }
    /**
     * Checks if the scheduledActivites contains the activity parameterized
     * @param day, the day of the week you are checking for the activity
     * @param activity, the activity you wish to search for 
     * @return true/false if the activity is present in scheduledActivities
     */
    public boolean hasActivity(String day,String activity){
        return this.scheduledActivities.get(day).hasActivity(activity);
    }
}

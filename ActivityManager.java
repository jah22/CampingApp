/*
 * CSCE 247
 * October 28, 2022
 * Activity Manager.
 * Manages activities.
 */
import java.util.ArrayList;

public class ActivityManager {
    // array list of strings representing the activities
    private ArrayList<String> activities = new ArrayList<>();

    // all activities start at 8 oclock morning
    // use military time
    // 8 am military
    static int START_TIME = 8;
    // 8 pm military
    static int END_TIME = 20;
    static int LUNCH_TIME = 12;
    static int BREAKFAST_TIME = 9;
    static int DINNER_TIME = 18;

    // all activities last one hour
    private int ACTIVITY_LENGTH = 1;

    /*
     * ctor
     * Set activities
     */
    public ActivityManager(ArrayList<String> activities){
        this.activities = activities;
    }

    /*
     * @param activities ArrayList<String> : an array list of activities
     * sets the activities of the manager.
     */
    public void setActivities(ArrayList<String> activities){
        this.activities = activities;
    }
    /*
     * Empty ctor only used for initialize objects
     */
    public ActivityManager(){
    }

    /*
     * Get the start time of an activity.
     * @param activity: String representing activity
     * @ return int: representing start time of activity
     */
    public int getActivityStartTime(String activity){
        // get time of start
        int index = this.activities.indexOf(activity);
        if(index == -1){
            return -1;
        }
        return index + START_TIME;
    }

    public int getActivityEndTime(String activity){
        return this.getActivityStartTime(activity) + ACTIVITY_LENGTH;
    }
    public ArrayList<String> getActivityList() {
        return this.activities;
    }
    public boolean isBefore(String activityOne, String activityTwo){
        /*
         * If act 1 is before act 2
         */
        if(getActivityEndTime(activityOne)<=getActivityStartTime(activityTwo)){
            // act 1 ends before act 2 starts, thus before
            return true;
        }
        return false;
    }
    public boolean isAfter(String act1, String act2){
        /*
         * If act 1 is after act 2
         * AKA
         * If act 2 before act 1
         */
        return isBefore(act2,act1);
    }
    public String getActivity(String activity){
        int index = this.activities.indexOf(activity);
        if(index == -1){
            // not found
            return "Activity not found.\n";
        }
        String out = "Activity: " + activity + "\n";
        out += "Time: " + this.getActivityStartTime(activity) + "-" + this.getActivityEndTime(activity) + "\n";

        return out;
    }

    // has more room for activities
    private boolean hasRoom(){
        // size must be at most 
        // END_TIME - START_TIME + 1
        return (this.activities.size() <= (END_TIME-START_TIME + 1));
    }

    public boolean addActivityToEnd(String activity){
        if(!hasRoom()){
            // no more room, cannot add
            return false;
        }
        this.activities.add(activity);
        return true;
    }
    public String toString() {
        String out = "";
        for (String activity: activities) {
            out += this.getActivityStartTime(activity) + "-" + this.getActivityEndTime(activity)+ ": " + activity +"\n";
        }
        return out;
    }
    public boolean hasActivity(String activity){
        return this.activities.contains(activity);
    }
}

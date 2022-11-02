/**
 * Manager of Activities
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */

import java.util.ArrayList;

public class ActivityManager {
    // array list of strings representing the activities
    private ArrayList<String> activities = new ArrayList<>();

    // all activities start at 8 oclock morning
    // use military time
    // 8 am military
    public static int START_TIME = 8;
    // 8 pm military
    public static int END_TIME = 20;
    public static int LUNCH_TIME = 12;
    public static int BREAKFAST_TIME = 9;
    public static int DINNER_TIME = 18;

    // all activities last one hour
    public static int ACTIVITY_LENGTH = 1;

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

    /*
     * Get the end time of an activity
     * @param activity: String representing the activity
     * @return int the end time of the activity
     */
    public int getActivityEndTime(String activity){
        return this.getActivityStartTime(activity) + ACTIVITY_LENGTH;
    }
    /*
     * Get the list of activities
     * @return ArrayList<String> the activities
     */
    public ArrayList<String> getActivityList() {
        return this.activities;
    }
    /*
     * Check if one activity is before another
     * @param activityOne: String of the first activity
     * @param activitiyTwo: String of the second activity
     * @return bool if activity 1 is before activity 2
     */
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
    /*
     * Check if one activity is after another
     * @param activityOne: String of the first activity
     * @param activitiyTwo: String of the second activity
     * @return bool if activity 1 is after activity 2
     */
    public boolean isAfter(String act1, String act2){
        /*
         * If act 1 is after act 2
         * AKA
         * If act 2 before act 1
         */
        return isBefore(act2,act1);
    }
    /*
     * Get the activity and its time
     * @param activity: String of the activity
     * @return String: a formatted activity string
     */
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

    /*
     * If room for activities
     * @return bool: representing if the activities has room for more
     */
    private boolean hasRoom(){
        // size must be at most 
        // END_TIME - START_TIME + 1
        return (this.activities.size() <= (END_TIME-START_TIME + 1));
    }

    /*
     * Add an activity to end of list
     * @param activity: String of the activity
     * @return bool: if the activity can be added
     */
    public boolean addActivityToEnd(String activity){
        if(!hasRoom()){
            // no more room, cannot add
            return false;
        }
        this.activities.add(activity);
        return true;
    }
    /*
     * Representing the manager as a str
     * @return String the manager as a str
     */
    public String toString() {
        String out = "";
        for (String activity: activities) {
            out += "Time: " + this.getActivityStartTime(activity) + " to " + this.getActivityEndTime(activity)+ " | Activity: " + activity +"\n";
        }
        return out;
    }
    /*
     * Check if contains an activity
     * @param activity: String representing the activity
     * @return bool: if have activity
     */
    public boolean hasActivity(String activity){
        return this.activities.contains(activity);
    }
}

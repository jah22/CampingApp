public class TimedActivity {
    private Activity activity; 
    private int hourStart;

    public TimedActivity(Activity activity, int hourStart){
        this.activity = activity;
        this.hourStart = hourStart;
    }

    public String getActivity(){
        // to do: check if makes sense
        return this.activity.toString();
    }

    public int getStartTime(){
        return this.hourStart;
    }

    public int getEndTime(){
        // all activities last one hour
        return this.hourStart + 1;
    }

    public boolean isBefore(TimedActivity act){
        return this.getEndTime() < act.getStartTime();
    }
    public boolean isAfter(TimedActivity act){
        return this.getStartTime() > act.getEndTime();
    }
    public boolean isOverlapping(TimedActivity act){
        return this.getStartTime() == act.getEndTime();
    }

    public String toString(){
        // to do
        return "";
    }

}

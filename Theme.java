import java.util.ArrayList;

public class Theme {
    private String name; 
    private int weekNumber;

    public Theme(String name, int weekNumber){
        this.name = name;
        this.weekNumber = weekNumber;
    }
    public String getName(){
        return this.name;
    }
    public int getWeekNumber(){
        return this.weekNumber;
    }
    public String toString(){
        return "Theme name: " + this.name + "\nTheme week number: " + this.weekNumber;
    }
}

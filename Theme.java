/**
 * Theme Class
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
public class Theme {
    private String name; 
    private int weekNumber;
    private String description;

    public Theme(String name, int weekNumber, String description){
        this.name = name;
        this.weekNumber = weekNumber;
        this.description = description;
    }
    public String getName(){
        return this.name;
    }
    public int getWeekNumber(){
        return this.weekNumber;
    }
    public String getDescription(){
        return this.description;
    }
    public String toString(){
        return "Week: " + this.weekNumber + " -- Theme: " + this.name + "\nDescription: " + this.description;
    }
}

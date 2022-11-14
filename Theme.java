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

    /**
     * Default constructor for Theme
     * @param name, the name of the theme
     * @param weekNumber, the week that the theme will be used at the camp
     * @param description, the description of the theme
     */
    public Theme(String name, int weekNumber, String description){
        this.name = name;
        this.weekNumber = weekNumber;
        this.description = description;
    }
    /**
     * @return the name of the theme
     */
    public String getName(){
        return this.name;
    }
    /**
     * @return the number of the week 
     */
    public int getWeekNumber(){
        return this.weekNumber;
    }
    /**
     * @return the description of the theme
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * @return a string representation of the Theme object
     */
    public String toString(){
        return "Week: " + this.weekNumber + " -- Theme: " + this.name + "\nDescription: " + this.description;
    }
    public boolean isEqual(Theme t) {
        if(!t.getName().equals(this.name)) return false;
        if(!t.getDescription().equals(this.description)) return false;
        if(t.getWeekNumber() != (this.weekNumber)) return false;
        return true;
    }
}

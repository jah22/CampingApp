/**
 * ThemeManager
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.UUID;

public class ThemeManager {
    private ArrayList<Theme> themes = new ArrayList<Theme>(); 
    private UUID id;
    /**
     * Empty constructor to set a random UUID
     */
    public ThemeManager(){
        this.id = UUID.randomUUID();
    }
    /**
     * Adds a Theme to an ArrayList of Theme objects
     * @param theme, the Theme you wish to add to the ArrayList
     */
    public void addTheme(Theme theme){
        this.themes.add(theme);
    }
    /**
     * Defualt constructor for ThemeManager
     * @param themes, an ArrayList of Themes
     */
    public void ThemeManager(ArrayList<Theme> themes){
        this.themes = themes;
        this.id = UUID.randomUUID();
    }
    /**
     * @return out, a string representation of the list of Themes
     */
    public String toString(){
        String out = "";
        for(Theme theme:this.themes){
            out += theme.toString() +"\n";
        }
        return out;
    }
    /**
     * @return the ID of the list of Themes for that camp
     */
    public UUID getId(){
        return this.id;
    }
    /**
     * Sets the id for this list of Themes
     */
    public void setId(UUID id){
        this.id = id;
    }
    /**
     * @return the length of the ArrayList of Themes
     */
    public int getThemeCount(){
        return this.themes.size();
    }
    /**
     * @return the ArrayList of Themes
     */
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }
    /**
     * Prints out the list of Themes to the user
     */
    public void viewThemes(){
        for(Theme theme: this.themes){
            System.out.println(theme);
        }
    }
    /**
     * Saves the list of Themes to the Theme.json file
     */
    public void save(){
        ArrayList<ThemeManager> tmList = new ArrayList<ThemeManager>();
        tmList.add(this);
        FileIO.writeTheme(tmList);
    }
}

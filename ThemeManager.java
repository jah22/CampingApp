import java.util.ArrayList;
import java.util.UUID;

public class ThemeManager {
    private ArrayList<Theme> themes = new ArrayList<Theme>(); 
    private UUID id;

    public ThemeManager(){
        // empty ctor
        this.id = UUID.randomUUID();
    }
    public void addTheme(Theme theme){
        this.themes.add(theme);
    }
    public void ThemeManager(ArrayList<Theme> themes){
        this.themes = themes;
        this.id = UUID.randomUUID();
    }
    public String toString(){
        String out = "";
        for(Theme theme:this.themes){
            out += theme.toString() +"\n";
        }
        return out;
    }
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }
    public int getThemeCount(){
        return this.themes.size();
    }
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }
    public void viewThemes(){
        for(Theme theme: this.themes){
            System.out.println(theme);
        }
    }
    public void save(){
        // save the themes
        ArrayList<ThemeManager> tmList = new ArrayList<ThemeManager>();
        tmList.add(this);
        FileIO.writeTheme(tmList);
    }
}

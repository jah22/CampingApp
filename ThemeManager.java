import java.util.ArrayList;
import java.util.UUID;

public class ThemeManager {
    private ArrayList<Theme> themes = new ArrayList<Theme>(); 
    private UUID id;

    public ThemeManager(){
        // empty ctor
    }
    public void addTheme(Theme theme){
        this.themes.add(theme);
    }
    public void ThemeManager(ArrayList<Theme> themes){
        this.themes = themes;
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
}
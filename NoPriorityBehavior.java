import java.lang.annotation.Repeatable;

/**
 * A NoPriorityBehavior implementing AuthBehavior
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public class NoPriorityBehavior implements AuthBehavior{

    
    /**
     * NoPriorityBehavior cannot login
     * @return false,
     */
    public boolean login(String username, String password) {
        return false;
    }

    /**
     * NoPriorityBehavior cannot logout
     * @return false;
     */
    public boolean logout() {
        return false;
    }
    /**
     * NoPriorityBehavior cannot login
     * @return false;
     */
    public boolean canLogin() {
        // cannot login
        return false;
    }

    /**
     * print account info
     * @return Non-priority user. 
     */
    public String toString(){
        return "Non-priority user.";
    }
     
}

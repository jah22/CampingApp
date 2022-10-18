public class NoPriorityBehavior implements AuthBehavior{

    // cannot login
    public boolean login(String username, String password) {
        return false;
    }

    // cannot logout
    public boolean logout() {
        return false;
    }

    public boolean canLogin() {
        // cannot login
        return false;
    }
     
}

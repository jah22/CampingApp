public class PriorityBehavior implements AuthBehavior{
    private String password;
    private String username;
    private String phone;
    private String email;
    private boolean isLoggedIn;

    PriorityBehavior(String username, String password, String phone, String email){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public boolean login(String username, String password) {
        if((this.username == username) && (this.password == password)){
            // can log in
            this.isLoggedIn = true;
            return true;
        }
        else{
            // incorrect combo
            return false;
        }
    }

    public boolean logout() {
        // if logged in, logout
        if(this.isLoggedIn){
            this.isLoggedIn = false;
            return true;
        }
        // else never logged in 
        return false;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
     
}

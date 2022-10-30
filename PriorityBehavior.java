/**
 * A PriorityBehavior implementing AuthBehavior, containing a String password, a String username, a String phone
 * a String phone, a String email, a boolean isLoggedin
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public class PriorityBehavior implements AuthBehavior{
    private String password;
    private String username;
    private String phone;
    private String email;
    private boolean isLoggedIn;

    /**
     * Parameterized constructor
     * @param username the username of the account
     * @param password the password of the account
     * @param phone the phone of the account
     * @param email the email of the account
     */
    public PriorityBehavior(String username, String password, String phone, String email){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Log in the system
     * @param username the username of an account
     * @param password the password of an account
     * @return true if successfully logged in, or else return false
     */
    public boolean login(String username, String password) {
        if((this.username.equals(username)) && (this.password.equals(password))){
            // can log in
            this.isLoggedIn = true;
            return true;
        }
        else{
            // incorrect combo
            return false;
        }
    }

    /**
     * Log out the system
     * @return true if successfully logged out, false if were never logged in
     */
    public boolean logout() {
        // if logged in, logout
        if(this.isLoggedIn){
            this.isLoggedIn = false;
            return true;
        }
        // else never logged in 
        return false;
    }

    /**
     * get user name
     * @return username
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * get phone 
     * @return phone
     */
    public String getPhone(){
        return this.phone;
    }
    /**
     * get email
     * @return email
     */
    public String getEmail(){
        return this.email;
    }
    /**
     * check if the account can login
     * @return true 
     */
    public boolean canLogin() {
        return true;
    }
    /**
     * get password
     * @return password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * set password
     * @param password the password
     */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     * set username
     * @param username the username
     */
    public void setUserName(String username){
        this.username = username;
    }
    /**
     * set email
     * @param email the email
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * set phone
     * @param phone the phone
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * print the account infomation
     * @return pritn the username and the password and the email and the phone 
     */
    public String toString(){
        String out = "Priority user.\n";
        out += "Username: " + this.username +"\n";
        out += "Password: " + this.password + "\n";
        out += "Email: " + this.email + "\n";
        out += "Phone: " + this.phone + "\n";

        return out;
    }
     
}

/**
 * An interface of authBehavior
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public interface AuthBehavior{
    /**
     * Log in the system
     * @param username the username of an account
     * @param password the password of an account
     * @return true if successfully logged in, or else return false
     */
    public boolean login(String username, String password);

    /**
     * Log out the system
     * @return true if successfully logged out, false if were never logged in
     */
    public boolean logout();

    /**
     * check if the account can login
     * @return true if the account is a priority account, false if the account is a no priority account
     */
    public boolean canLogin();

    /**
     * print the account infomation
     * @return account info
     */
    public String toString();
}
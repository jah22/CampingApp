/*
 * CSCE 247
 * October 28, 2022
 */
public interface AuthBehavior{
    public boolean login(String username, String password);
    public boolean logout();
    public boolean canLogin();
    public String toString();
}
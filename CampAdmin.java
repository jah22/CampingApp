/**
 * Camp Admin
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.UUID;

public class CampAdmin extends Person{
    /**
     * Constructor for Camp Admin
     * @param firstName is admin's first name
     * @param lastName is admin's last name
     * @param birthDate is admin's birthday
     * @param address is admin's address
     * @param id is admin's ID
     * @param password is admin's password
     * @param username is admin's username
     * @param email is admin's password
     * @param phone is admin's phone number
     */
    public CampAdmin(String firstName, String lastName, String birthDate, String address, UUID id, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, id);
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    /**
     * Costructor for camp admin with random UUID
     * @param firstName is admin's first name
     * @param lastName is admin's last name
     * @param birthDate is admin's birthday
     * @param address is admin's address
     * @param password is admin's password
     * @param username is admin's username
     * @param email is admin's password
     * @param phone is admin's phone number
     */
    public CampAdmin(String firstName, String lastName, String birthDate, String address, String password, String username, String email, String phone) {
        super(firstName, lastName, birthDate, address, UUID.randomUUID());
        PriorityBehavior behavior = new PriorityBehavior(username,password,phone,email);
        this.setAuthBehavior(behavior);
    }
    /**
     * Gets the type of person
     * @return "CampAdmin"
     */
    public String getPersonType(){
        return "CampAdmin";
    }
}

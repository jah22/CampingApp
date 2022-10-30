import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
/**
 * A Person class containing a String firstName, a String lastname, a String birthDate, a String address and 
 * a UUID id and a Authbehavior authBehaviro
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 */
public class Person {
    protected String firstName;    
    protected String lastName;
    // to do: change string to date 
    protected String birthDate;
    protected String address;
    protected  UUID id;
    
    protected AuthBehavior authBehavior;

    /**
     * Parameterized constructor
     * @param firstName the first name of the person
     * @param lastName the last name iof the person
     * @param birthDate the birthDate of the person
     * @param address the address of the person
     * @param id the UUID of the person
     */
    public Person(String firstName, String lastName, String birthDate, String address, UUID id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.id = id;
    }

    /**
     * Parameterized constructor
     * @param firstName the first name of the person
     * @param lastName the last name iof the person
     * @param birthDate the birthDate of the person
     * @param address the address of the person
     * @param id the string id of the person
     */
    public Person(String firstName, String lastName, String birthDate, String address, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.id = UUID.fromString(id);
    }

    /**
     * set authBehavior
     * @param behavior the behavior of the person
     */
    public void setAuthBehavior(AuthBehavior behavior){
        this.authBehavior = behavior;
    }
    /**
     * check if the person can login
     * @return true if can, false if cannot
     */
    public boolean getHasLoginBehavior(){
        return this.authBehavior.canLogin();
    }
    /**
     * get last name
     * @return the last name
     */
    public String getLastName(){
        return this.lastName;
    }
    /**
     * get first name
     * @return the first name
     */
    public String getFirstName(){
        return this.firstName;
    }
    /**
     * get address
     * @return the address
     */
    public String getAddress(){
        return this.address;
    }
    /**
     * get birth date
     * @return the birth date
     */
    public String getBirthDate(){
        return this.birthDate;
    }
    /**
     * get the age of the person
     * @return the years the person have lived
     */
    public int getAgeInt(){
        /*
         * IMPORTANT NOTE:
         * LocalDate assumes dates of form:
         * yyyy-mm-dd
         * For example, October 18, 2022 is
         * 2022-10-18
         */
        // returns an integer based on the age of the user
        int yearsAlive = -1;

        LocalDate now = LocalDate.now();
        
        try{
            LocalDate birthLocalDate = LocalDate.parse(this.birthDate);
            yearsAlive = (int) ChronoUnit.YEARS.between(birthLocalDate,now);
        }
        catch(DateTimeParseException e){
            e.printStackTrace();
        }
        return yearsAlive;
    }
    /**
     * get UUID
     * @return the UUID
     */
    public UUID getId(){
        return this.id;
    }
    /**
     * print out first name +　last name
     * @return  first name +　last name
     */
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
    /**
     * check if the full name entered match the fullname saved
     * @param firstName firstname entered
     * @param lastName lastname entered
     * @return ture if matched, false if not
     */
    public boolean checkFullName(String firstName, String lastName){
        return this.firstName == firstName && this.lastName == lastName;
    }
    /**
     * print out full name
     * @return first name +　last name
     */
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    /**
     * get auth behavior
     * @return the auth behavior
     */
    public AuthBehavior getAuthBehavior(){
        return this.authBehavior;
    }
    /**
     * get person type
     * @return Person
     */
    public String getPersonType(){
        return "Person";
    }

}

import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Person {
    protected String firstName;    
    protected String lastName;
    // to do: change string to date 
    protected String birthDate;
    protected String address;
    protected  UUID id;
    
    protected AuthBehavior authBehavior;

    public Person(String firstName, String lastName, String birthDate, String address, UUID id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.id = id;
    }
    public Person(String firstName, String lastName, String birthDate, String address, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.id = UUID.fromString(id);
    }

    public void setAuthBehavior(AuthBehavior behavior){
        this.authBehavior = behavior;
    }

    public boolean getHasLoginBehavior(){
        return this.authBehavior.canLogin();
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getAddress(){
        return this.address;
    }

    public String getBirthDate(){
        return this.birthDate;
    }

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

    public UUID getId(){
        return this.id;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }

    public boolean checkFullName(String firstName, String lastName){
        return this.firstName == firstName && this.lastName == lastName;
    }
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public AuthBehavior getAuthBehavior(){
        return this.authBehavior;
    }

    public String getPersonType(){
        return "Person";
    }

}

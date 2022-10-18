import java.util.UUID;

public abstract class Person {
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

    public UUID getId(){
        return this.id;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}

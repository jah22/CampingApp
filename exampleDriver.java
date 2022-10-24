import java.util.ArrayList;
import java.util.Scanner;

// just for testing
public class exampleDriver {
    CampSiteManager csm = FileIO.getInstance().getCamp();
    Scanner USER_INPUT = new Scanner(System.in);

    public void runDriver(){
        printWelcome();
        printLoginRegViewOptions();
        Person user = handleLoginRegView(); 
        welcomeAuthUser(user);
        switch(user.getPersonType()){
            case "Dependent":
                // coordinator
                user = (Dependent) user;
                break;
            case "Guardian":
                user = (Guardian) user;
                runAuthorizedGuardian((Guardian)user);
                break;
            case "CampAdmin":
                user = (CampAdmin) user;
                break;
            
        }
    }
    public void welcomeAuthUser(Person user){
        System.out.println("Welcome, " + user.getFirstName() +"\n");
    }
    public void runAuthorizedGuardian(Guardian user){
        int selection = getGuardianOptionSelection();
        while(true){
            handleGuardianOptionSelected(selection,user);
            selection = getGuardianOptionSelection();
        }
    }
    public void handleGuardianOptionSelected(int option,Guardian user){
        switch(option) {
            case 1:
                // show dependents
                this.csm.viewDependentsFromGuardian(user.id);
                break;
            case 2:
                // register new dependent
                this.promptNewDependent(user);
                break;
            case 3:
                // view cabins
                this.csm.viewCabins();
                break;
            case 4:
                // add camper to cabin
                handleAddDependentToCabin(user);
                break;
            case 5:
                this.exit();
                break;
        }
    }
    public void handleAddDependentToCabin(Guardian guardian){
        // check if dependents
        if(!this.csm.guardianHasDependents(guardian)){
            System.out.println("No dependents found. Please first add a dependent.");
            return;
        }
        // check if space
        if(!this.csm.checkCabinsForDependents(guardian)){
            System.out.println("There is either no space for your camper, or your campers are not in the age ranges.") ;
            return;
        }
        Cabin cabinToBeAddedTo = null;
        while(cabinToBeAddedTo == null){
            System.out.println("Which cabin? ");
            this.csm.viewCabinNames();
            System.out.println("Select a cabin by entering it's number: ");
            int cabinIndex = this.promptForIntResponse();
            cabinToBeAddedTo = this.csm.getCabinByIndex(cabinIndex);
            if(cabinToBeAddedTo == null){
                System.out.println("Sorry, please enter the cabin name correctly.");
            }
        }


        Dependent camperToAdd = null;
        while(camperToAdd == null){
            System.out.println("Which camper? ");
            this.csm.viewCamperNamesByGuardian(guardian.getId());
            System.out.println("Type the first name of the camper: ");
            String camperNameFN = this.promptForStringResponse();
            System.out.println("Type last name of camper: ");
            String camperNameLN = this.promptForStringResponse();
            camperToAdd = this.csm.getDependentByName(camperNameFN,camperNameLN);
            if(camperToAdd == null){
                System.out.println("Invalid name. Try again.");
            }
        }
        this.csm.addCamperToCabin(camperToAdd,cabinToBeAddedTo);
    }
    public void promptNewDependent(Guardian user){
        System.out.println("First name: ");
        String firstName = this.promptForStringResponse();
        System.out.println("Last name (or \"same\"if your last name): ");
        String lastName = this.promptForStringResponse();
        if(lastName.equals("same")){
            lastName = user.getLastName();
        }
        System.out.println("Birthdate: [YYYY-MM-DD]");
        String birthDate = this.promptForStringResponse();
        System.out.println("Address (or \"same\" if your address): ");
        String address = this.promptForStringResponse();
        if(address.equals("")){
            address = user.getAddress();
        }
        ArrayList<String> medNotes = promptMedNotes();
        ArrayList<EmergencyContact> emContacts = promptEmContacts();
        this.csm.addDependent(user, firstName, lastName, birthDate, address,medNotes,emContacts);
    }
    public ArrayList<EmergencyContact> promptEmContacts(){
        ArrayList<EmergencyContact> ems = new ArrayList<>();
        boolean running = true;
        while(running){
            System.out.println("[1] Add emergency contact\n[2] Done adding");
            int command = promptForIntResponse();
            switch(command){
                case 1:
                    EmergencyContact em = promptEmContact();
                    ems.add(em);
                    break;
                case 2:
                    System.out.println("Done adding emergency contacts.");
                    running = false;
                    break;
            }
        }
        return ems;
    }
    public EmergencyContact promptEmContact(){
        System.out.println("First name: ");
        String firstName = promptForStringResponse();
        System.out.println("Last name: ");
        String lastName = promptForStringResponse();
        System.out.println("Birthdate: ");
        String birthdate = promptForStringResponse();
        System.out.println("Address: ");
        String address = promptForStringResponse();
        return new EmergencyContact(firstName, lastName, birthdate, address);
    }
    public ArrayList<String> promptMedNotes(){
        ArrayList<String> notes = new ArrayList<>();
        while(true){
            String medNote = promptMedNote();
            if(medNote.equals("done")){
                return notes;
            }
            notes.add(medNote);
        }
    }
    public String promptMedNote(){
        System.out.println("Please enter any medical information, or \"done\" if done: ");
        return promptForStringResponse();
    }
    public int getGuardianOptionSelection(){
        showGuardianOptions();
        int intUserChoice = -1;
        while(!isValidIntInput(intUserChoice,1,5)){
            intUserChoice = promptForIntResponse();
            if(!isValidIntInput(intUserChoice,1,5)){
                showGuardianOptions();
            }
        }
        return intUserChoice;
    }
    public void showGuardianOptions(){
        System.out.println("[1] Show your dependents");
        System.out.println("[2] Register new dependent");
        System.out.println("[3] View cabins");
        System.out.println("[4] Add camper to cabin");
        System.out.println("[5] Exit");
    }
    public boolean isValidIntInput(int input,int lower, int upper){
        // if between lower and upper, valid
        return (input >= lower && input <= upper);
    }
    public Person handleLogin(){
        System.out.println("Are you a\n[1] Guardian\n[2] Coordinator\n[3] Camp Admin\nOr [4] to exit.\n");
        int intUserChoice = -1;
        while(!isValidIntInput(intUserChoice,1,4)){
            intUserChoice = promptForIntResponse();
            if(!isValidIntInput(intUserChoice,1,4)){
                System.out.println("Are you a\n[1] Guardian\n[2] Coordinator\n[3] Camp Admin\nOr [4] to exit.\n");
            }
        }
        Person retPerson = null;
        while(retPerson == null){
            ArrayList<String> userPassCombo = promptLoginInfo();
            switch(intUserChoice){
                case 1:
                    retPerson = this.csm.loginGuardian(userPassCombo.get(0),userPassCombo.get(1));
                    break;
                case 2:
                    retPerson = this.csm.loginDependent(userPassCombo.get(0),userPassCombo.get(1));
                    break;
                case 3:
                    retPerson = this.csm.loginAdmin(userPassCombo.get(0),userPassCombo.get(1));
                    break;
                case 4:
                    this.exit();
            }
            if(retPerson == null){
                System.out.println("Incorrect login credentials. Please try again.\n");
            }
            else{
                System.out.println("Successfully logged in.\n");
            }
        }

        return retPerson;
    }
    public ArrayList<String> promptLoginInfo(){
        // first entry is username, second is password
        ArrayList<String> ret = new ArrayList<>();
        System.out.println("Please enter your username: ");
        String username = promptForStringResponse();
        System.out.println("Please enter your password: ");
        String password = promptForStringResponse();
        ret.add(username);
        ret.add(password);
        return ret;
    }
    public Person handleRegister(){
        Person user = null;
        System.out.println("Are you a\n[1] Guardian\n[2] Coordinator\n[3] Camp Admin\nOr [4] to exit.\n");
        return user;
    }
    public Person handleLoginRegView(){
        int command = -1;
        while(!isValidIntInput(command, 1, 4)){
            command = promptForIntResponse();
        }
        switch(command){
            case 1:
                // register
                return handleRegister();
            case 2:
                // login
                return handleLogin();
            case 3:
                // view camp
                this.csm.viewCamp();
                break;
            case 4:
                // exit
                exit();
                break;
        }
        return null;
    }
    public void exit(){
        System.out.println("Goodbye!");
        // TO DO:
        // REWRITE ALL THE JSON HERE
        System.exit(0);
    }
    public void printLoginRegViewOptions(){
        // prints options for login, register, or view
        System.out.println("Would you like to \n[1] Register \n[2] Login \n[3] View Camp Site\n[4] Exit\n");
    }
    public int promptForIntResponse(){
        System.out.print("> ");
        int command = USER_INPUT.nextInt();
        return command;
    }
    public String promptForStringResponse(){
        System.out.print("> ");
        String response = USER_INPUT.next();
        return response;
    }

    public void printWelcome(){
        System.out.println("Welcome to the camp system! ");
    }
    public static void main(String[] args) {
        exampleDriver driver = new exampleDriver();
        driver.runDriver();
    }
}

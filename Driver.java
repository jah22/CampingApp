import java.util.ArrayList;
import java.util.Scanner;

// just for testing
public class Driver {
    CampSiteManager csm = FileIO.getInstance().getCamp();
    Scanner USER_INPUT = new Scanner(System.in);

    public void runDriver(){
        boolean running = true;
        while(running){
            printWelcome();
            Person user = null;
            while(user == null){
                printLoginRegViewOptions();
                user = handleLoginRegView(); 
            }
            welcomeAuthUser(user);
            switch(user.getPersonType()){
                case "Dependent":
                    // coordinator
                    user = (Dependent) user;
                    runCoordinator((Dependent)user);
                    break;
                case "Guardian":
                    user = (Guardian) user;
                    runAuthorizedGuardian((Guardian)user);
                    break;
                case "CampAdmin":
                    user = (CampAdmin) user;
                    runCampAdmin((CampAdmin)user);
                    break;

            }
        }
    }
    public void runCampAdmin(CampAdmin user){
        boolean running = true;
        while(running){
            viewCampAdminOptions();
            int selection = getValidSelection(1,3);
            switch(selection){
                case 1:
                    // view camp site info
                    this.handleViewCampSection();
                    break;
                case 2:
                    this.handleEditCampSection();
                    break;
                case 3:
                    return;
            }
        }
    }
    public void handleEditCampSection(){
        boolean running = true;
        while(running){
            viewHandleEditCampSectionOptions();
            int selection = getValidSelection(1, 4);
            switch(selection){
                case 1:
                    // camp name 
                    this.handleEditCampName();
                    break;
                case 2:
                    // camp themes
                    this.handleEditCampThemes();
                    break;
                case 3:
                    // camp year
                    this.handleEditCampYear();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
            }
        }
    }
    public void handleEditCampThemes(){
        System.out.println("The current themes are: ");
        this.csm.viewCampThemes();
        System.out.println("Would you like to edit any themes?");
    }
    public void handleEditCampName(){
        System.out.println("The current name is: " + this.csm.getName());
        System.out.println("Would you like to edit the name?");
        int response = getYesNoResponse();
        switch(response){
            case 1:
                System.out.println("Enter a name: ");
                this.csm.setName(promptForStringResponse());
                break;
            case 2:
                System.out.println("Returning to edit camp menu...");
                return;
        }
    }
    public void handleEditCampYear(){
        System.out.println("The current year for this camp is: "+ this.csm.getYear());
        System.out.println("Would you like to edit the year?");
        int response = getYesNoResponse();
        switch(response){
            case 1:
                System.out.println("Enter a year: ");
                this.csm.setYear(promptForIntResponse());
                break;
            case 2:
                System.out.println("Returning to edit camp menu...");
                return;
        }


    }

    public void viewHandleEditCampSectionOptions(){
        System.out.println("[1] Edit camp name");
        System.out.println("[2] Edit camp themes");
        System.out.println("[3] Edit camp year");
        System.out.println("[4] Exit");
    }
    public void viewCampAdminOptions(){
        System.out.println("[1] View Camp Site Information");
        System.out.println("[2] Edit Camp Site Information");
        System.out.println("[3] Exit");
    }
    public void runCoordinator(Dependent user){
        boolean running = true;
        while(running){
            printCoordinatorOptions();    
            int selection = getValidSelection(1, 3);
            switch(selection){
                case 1:
                    this.handleCabinsCoordinator(user);
                    break;
                case 2:
                    this.handleEmergencyContactsCoordinator(user);
                    break;
                case 3:
                    exit();
                    break;
            }
        }
    }
    public void handleEmergencyContactsCoordinator(Dependent user){
        boolean running = true;
        while(running){
            printEmergencyContactsCoordinatorOptions();
            int selection = getValidSelection(1, 2);
            switch(selection){
                case 1:
                    this.csm.viewEmergencyContacts(user);
                    break;
                case 2:
                    System.out.println("Returning to main menu...");
                    return;
            }
        }
    }
    public void printEmergencyContactsCoordinatorOptions(){
        System.out.println("Emergency Contacts Menu");
        System.out.println("[1] view your emergency contacts");
        System.out.println("[2] Exit");
    }
    public void handleCabinsCoordinator(Dependent coordinator){
        boolean running = true;
        while(running){
            printCabinsSectionCoordinatorOptions();
            int selection = getValidSelection(1,3);
            switch(selection){
                case 1:
                    this.csm.viewCabinByCoordinator(coordinator);
                    break;
                case 2:
                    this.csm.viewCabinSchedulesByCoordinator(coordinator);
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    return;
            }
        }
    }
    public void printCabinsSectionCoordinatorOptions(){
        System.out.println("Cabin Menu");
        System.out.println("[1] View Your Cabin");
        System.out.println("[2] View Your Cabin Schedules");
        System.out.println("[3] Exit");
    }
    public void printCoordinatorOptions(){
        System.out.println("[1] Cabins");
        System.out.println("[2] Emergency Contacts");
        System.out.println("[3] Exit");
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
                // print dependents
                this.handleGuardianDependentSection(user);
                break;
            case 2:
                // register new dependent
                this.handleGuardianCabinSection(user);
                break;
            case 3:
                handleReviewSection(user);
                break;
            case 4:
                break;
        }
    }
    public void handleGuardianCabinSection(Guardian user){
        printGuardianCabinSectionOptions();
        int selection = getValidSelection(1,3);
        switch(selection){
            case 1:
                this.csm.viewCabins();
                break;
            case 2:
                this.handleAddDependentToCabin(user);
                break;
            case 3:
                System.out.println("Returning to main menu...");
                return;
        }
    }
    public void printGuardianCabinSectionOptions(){
        System.out.println("[1] View cabins");
        System.out.println("[2] Add dependent to cabin");
        System.out.println("[3] Exit");
    }
    public void handleReviewSection(Guardian guardian){
        while(true){
            System.out.println("[1] Add review");
            System.out.println("[2] See your reviews");
            System.out.println("[3] See all reviews");
            System.out.println("[4] See reviews by rating");
            System.out.println("[5] Exit");
            int selection = getValidSelection(1,5);
            switch(selection){
                case 1:
                    this.handleReviewCamp(guardian);
                    break;
                case 2:
                    this.csm.viewReviewsByAuthor(guardian.getFullName());
                    break;
                case 3:
                    this.csm.viewAllReviews();
                    break;
                case 4:
                    handleViewReviewsByRating();
                    break;
                case 5:
                    System.out.println("Returning to main screen...");
                    return;
            }
        }
    }
    public void handleViewReviewsByRating(){
        System.out.println("Enter rating: ");
        int rating = getValidSelection(1,5);
        this.csm.viewReviewsByRating(rating);
    }
    public void handleReviewCamp(Guardian guardian){
        if(!this.csm.guardianHasDependents(guardian)){
            System.out.println("No dependents found. You cannot review if you have no campers.");
            return;
        }
        if(!this.csm.guardianHasCampersRegistered(guardian)){
            System.out.println("None of your dependents are registered. You cannot register a camper for a cabin that they have not been to.");
            return;
        }
        System.out.println("You are able to review this camp.");
        System.out.println("Enter the rating (1-5)");
        int rating = getValidSelection(1,5);
        System.out.println("Please enter a title for your review: ");
        String title = promptForStringResponse();
        System.out.println("Please enter the body of your review. Type \"done\" when complete.");
        String body = "";
        String userString = "";
        // TO DO: 
        // FIX THIS. PRINTS TOO MANY >
        while(!userString.equals("done")){
            userString = promptForStringResponse();
            if(!userString.equals("done")){
                body += userString + "\n";
            }
        }
        this.csm.addReview(guardian.getFullName(),rating,title,body);
    }
    public void handleGuardianDependentSection(Guardian user){
        while(true){
            printGuardianDependentSectionOptions();
            int selection = getValidSelection(1,4);
            switch(selection){
                case 1:
                    this.csm.viewDependentsFromGuardian(user.getId());
                    break;
                case 2:
                    this.handleAddNewDependent(user);
                    break;
                case 3:
                    this.handleRemoveDependentFromGuardian(user);
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
            }
        }
    }
    public void handleRemoveDependentFromGuardian(Guardian user){
        System.out.println("Here are your dependents: ");
        this.csm.viewDependentsFromGuardian(user.getId());
        System.out.println("Would you like to remove one?");
        int response = getYesNoResponse();
        if(response == 2){
            System.out.println("Canceling removal.");
            return;
        }
        System.out.println("Enter the first name of the dependent: ");
        String firstName = promptForStringResponse();
        System.out.println("Enter the last name of the dependent: ");
        String lastName = promptForStringResponse();
        if(this.csm.removeDependent(user,this.csm.getDependentByName(user.getId(), firstName, lastName))){
            System.out.println("Successfully removed camper.");
            return;
        }
        System.out.println("No camper with that name present.");
    }
    public int getYesNoResponse(){
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        return getValidSelection(1, 2);
    }
    public void printGuardianDependentSectionOptions(){
        System.out.println("Dependent Information Menu");
        System.out.println("[1] View your dependents");
        System.out.println("[2] Add new dependent");
        System.out.println("[3] Remove dependent");
        System.out.println("[4] Exit");
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
            camperToAdd = this.csm.getDependentByName(guardian.getId(),camperNameFN,camperNameLN);
            if(camperToAdd == null){
                System.out.println("Invalid name. Try again.");
            }
        }
        if(this.csm.addCamperToCabin(camperToAdd,cabinToBeAddedTo)){
            System.out.println("Successfully added camper to cabin.");
        }
        else{
            System.out.println("Could not add camper to cabin.");
        }
    }
    public void handleAddNewDependent(Guardian user){
        System.out.println("First name: ");
        String firstName = this.promptForStringResponse();
        System.out.println("Last name (or \"same\"if your last name): ");
        String lastName = this.promptForStringResponse();
        if(lastName.equals("same")){
            lastName = user.getLastName();
        }
        String birthDate = this.promptForBirthDate();
        System.out.println("Address (or \"same\" if your address): ");
        String address = this.promptForStringResponse();
        if(address.equals("")){
            address = user.getAddress();
        }
        ArrayList<String> medNotes = promptMedNotes();
        ArrayList<EmergencyContact> emContacts = promptEmContacts();
        this.csm.addDependent(user.getId(), firstName, lastName, birthDate, address,medNotes,emContacts);
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
        System.out.println("Birthdate: [YYYY-MM-DD]");
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
        printGuardianOptions();
        int intUserChoice = -1;
        while(!isValidIntInput(intUserChoice,1,4)){
            intUserChoice = promptForIntResponse();
            if(!isValidIntInput(intUserChoice,1,4)){
                printGuardianOptions();
            }
        }
        return intUserChoice;
    }

    public void printGuardianOptions(){
        System.out.println("[1] Dependents");
        System.out.println("[2] Cabins");
        System.out.println("[3] Reviews");
        System.out.println("[4] Exit");
    }
    public boolean isValidIntInput(int input,int lower, int upper){
        // if between lower and upper, valid
        return (input >= lower && input <= upper);
    }
    public Person handleLogin(){
        boolean running = true;
        int userChoice = -1;
        System.out.println("Are you a\n[1] Guardian\n[2] Coordinator\n[3] Camp Admin\nOr [4] to exit.");
        userChoice = getValidSelection(1, 4);
        if(userChoice == 4){
            return null;
        }
        Person retPerson = null;
        while(retPerson == null){
            ArrayList<String> userPassCombo = promptLoginInfo();
            switch(userChoice){
                case 1:
                    retPerson = this.csm.loginGuardian(userPassCombo.get(0),userPassCombo.get(1));
                    break;
                case 2:
                    retPerson = this.csm.loginDependent(userPassCombo.get(0),userPassCombo.get(1));
                    break;
                case 3:
                    retPerson = this.csm.loginAdmin(userPassCombo.get(0),userPassCombo.get(1));
                    break;
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
    public Guardian handleRegisterGuardian(){
        // register a guardian
        System.out.println("Enter your first name: ");
        String firstName = promptForStringResponse();
        System.out.println("Enter your last name: ");
        String lastName= promptForStringResponse();
        System.out.println("Create your username: ");
        String username= promptForStringResponse();
        String password = createPassword();
        String birthDate = promptForBirthDate();
        String phone = promptForPhone();
        String email = promptForEmail();
        String address = promptForAddress();

        return this.csm.registerGuardian(firstName,lastName,birthDate,username,password,email,phone, address);
    }
    public String promptForPhone(){
        // to do: perform checks here
        System.out.println("Enter your phone number [XXX-XXX-XXXX]: ");
        return promptForStringResponse();
    }
    public String promptForBirthDate(){
        // to do: perform checks here
        System.out.println("Enter your birth date [YYYY-MM-DD]: ");
        return promptForStringResponse();
    }
    public String promptForAddress(){
        System.out.println("Enter your address: ");
        return promptForStringResponse();
    }
    public String promptForEmail(){
        // to do: perform checks here
        System.out.println("Enter your email: ");
        return promptForStringResponse();
    }
    public String createPassword(){
        String password = "";
        boolean isPasswordMatch = false;
        while(!isPasswordMatch){
            System.out.println("Create your password: ");
            String pass1 = promptForStringResponse();
            System.out.println("Enter your password again: ");
            password = promptForStringResponse();
            isPasswordMatch = (pass1.equals(password));
            if(!isPasswordMatch){
                System.out.println("Passwords didn't match. Try again.");
            }
        }
        return password;
    }
    public Dependent handleRegisterCoordinator(){
        // register a guardian
        System.out.println("Enter your first name: ");
        String firstName = promptForStringResponse();
        System.out.println("Enter your last name: ");
        String lastName= promptForStringResponse();
        System.out.println("Create your username: ");
        String username= promptForStringResponse();
        String password = createPassword();
        String birthDate = promptForBirthDate();
        String phone = promptForPhone();
        String email = promptForEmail();

        return this.csm.registerCoordinator(firstName, lastName, username, password, birthDate, phone, email);
    }

    public Person handleRegister(){
        Person user = null;
        System.out.println("Are you a");
        System.out.println("[1] Guardian");
        System.out.println("[2] Coordinator");
        System.out.println("[3] Camp Admin");
        System.out.println("Or [4] to exit.");
        int response = getValidSelection(1, 4);
        switch(response){
            case 1:
                user = handleRegisterGuardian();
                break;
            case 2:
                user = handleRegisterCoordinator();
                break;
            case 3:
                user = handleRegisterCampAdmin();
                break;
        }
        return user;
    }
    public CampAdmin handleRegisterCampAdmin(){
        // register a guardian
        System.out.println("Enter your first name: ");
        String firstName = promptForStringResponse();
        System.out.println("Enter your last name: ");
        String lastName= promptForStringResponse();
        System.out.println("Create your username: ");
        String username= promptForStringResponse();
        String password = createPassword();
        String birthDate = promptForBirthDate();
        String phone = promptForPhone();
        String email = promptForEmail();
        String address = promptForAddress();
        CampAdmin admin = new CampAdmin(firstName, lastName, birthDate, address, password, username, email, phone);
        return this.csm.registerAdmin(admin);
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
                this.handleViewCampSection();
                break;
            case 4:
                // exit
                exit();
                break;
        }
        return null;
    }
    public void handleViewCampSection(){
        boolean running = true;
        while(running){
            printViewCampSectionOptions(); 
            int selection = getValidSelection(1, 4);
            switch(selection){
                case 1:
                    this.csm.viewCamp();
                    break;
                case 2:
                    this.handleReviewSectionNonAuth();
                    break;
                case 3:
                    this.handleCabinSectionNonAuth();
                    break;
                case 4:
                    running = false;
                    System.out.println("Returning to main menu...");
                    break;
            }
        }
    }
    public void handleCabinSectionNonAuth(){
        boolean running = false;
        while(running){
            printCabinSectionNonAuthOptions(); 
            int selection = getValidSelection(1,3);
            switch(selection){
                case 1:
                    this.csm.viewCabins();
                    break;
                case 2:
                    this.handleCabinSpecificSectionNonAuth();
                    break;
                case 3:
                    System.out.println("Returning to camp menu.");
                    return;
            }
        }
    }
    public void handleCabinSpecificSectionNonAuth(){
        boolean running = true;
        int upperBoundCabinIndex = this.csm.getCabinCount();
        while(running){
            printCabinSpecificSectionNonAuthOptions();
            int selection = getValidSelection(-1,upperBoundCabinIndex);
            if(selection == -1){
                System.out.println("Returning to cabin section...");
                return;
            }
            this.csm.viewCabinByIndex(selection);
        }
    }
    public void printCabinSpecificSectionNonAuthOptions(){
        System.out.println("Below are the available cabins: ");
        this.csm.viewCabinNames();
        System.out.println("Or [-1] to exit.");
    }
    public void printCabinSectionNonAuthOptions(){
        System.out.println("[1] View all Cabins");
        System.out.println("[2] View specific Cabin");
        System.out.println("[3] Exit");
    }
    public void handleReviewSectionNonAuth(){
        boolean running = true;
        while(running){
            printReviewSectionNonAuthOptions();
            int selection = getValidSelection(1,3);
            switch(selection){
                case 1:
                    this.csm.viewAllReviews();
                    break;
                case 2:
                    this.handleViewReviewsByRating();
                    break;
                case 3:
                    running = false;
                    System.out.println("Returning to Camp View Menu...");
                    break;
            }
        }
    }
    public void printReviewSectionNonAuthOptions(){
        System.out.println("Review Section Menu");
        System.out.println("[1] See all reviews");
        System.out.println("[2] See reviews by rating");
        System.out.println("[3] Exit");
    }
    public void printViewCampSectionOptions(){
        System.out.println("Camp View Menu");
        System.out.println("[1] See Camp Site");
        System.out.println("[2] Reviews");
        System.out.println("[3] Cabins");
        System.out.println("[4] Exit");
    }
    public void exit(){
        System.out.println("Goodbye!");
        // TO DO:
        // REWRITE ALL THE JSON HERE
        System.exit(0);
    }
    public void printLoginRegViewOptions(){
        // prints options for login, register, or view
        System.out.println("Would you like to");
        System.out.println("[1] Register");
        System.out.println("[2] Login");
        System.out.println("[3] View Camp Site");
        System.out.println("[4] Exit");
    }
    public int promptForIntResponse(){
        boolean running = true;
        int command = -1;
        while(running){
            try{
                System.out.print("> ");
                command = USER_INPUT.nextInt();
                running = false;
            }catch(java.util.InputMismatchException e){
                System.out.println("Please input an integer.");
            }
            USER_INPUT.nextLine();
        }

        return command;
    }
    public String promptForStringResponse(){
        // TO DO: 
        // READ SPACES TOO
        System.out.print("> ");
        String response = USER_INPUT.nextLine();
        response = response.trim();
        return response;
    }
    public int getValidSelection(int lower, int upper){
        int selection = -1;
        while(!isValidIntInput(selection, lower, upper)){
            selection = promptForIntResponse();
            if(!isValidIntInput(selection, lower, upper)){
                System.out.println("Please try again.");
            }
        }
        return selection;
    }

    public void printWelcome(){
        System.out.println("Welcome to the camp system! ");
    }
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runDriver();
    }
}

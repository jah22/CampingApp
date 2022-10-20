import java.util.Scanner;

public class CampDriver {
    public void runCampDriver(){
        // read the files (to do: this should be in CampSiteManager)

        Scanner userInput = new Scanner(System.in);

        // running the program
        boolean isRunning = true;

        while(isRunning){
            System.out.println("Welcome to the camping app!");
            System.out.println("Select: \n [1] Login \n [2] Register \n [3] About \n [4] Cabins \n [5] Admins \n [6] Coordinator \n [7] Exit");
            String command = userInput.nextLine();
            if(command.equals("1")) {
                System.out.println("Select:\n [1] to login to existing account \n [2] to register a new account");
                String input = userInput.nextLine();
                if(input.equals("1")) {
                    System.out.println("Please enter your username.");
                    String username = userInput.nextLine();

                    System.out.print("Please enter your password.");
                    String password = userInput.nextLine();

                    //Implment to campsite manager login when ready
                    System.out.println("You have sucesssfully logged in");
                }
                else if(input.equals("2")) {
                    boolean registerQuit = true;
                    while(registerQuit) { 
                    System.out.println("Select: \n [1] Guardian \n [2] Coordinator \n [3] Administrator");
                    String registerInput = userInput.nextLine();
                        if(registerInput.equals("1")) {
                            registerQuit = false;
                            //Create a Guardian in Guardian Class

                            //Ask for Guardian Information
                            System.out.println("Please enter your first name");
                            String userFirstName = userInput.nextLine();

                            System.out.print("Please enter your last name");
                            String userLastName = userInput.nextLine();

                            System.out.println("Please enter your birthdate [input format]");
                            String userBirthDate = userInput.nextLine();

                            System.out.println("Please enter your home address");
                            String userAddress = userInput.nextLine();

                            System.out.println("Please enter your phone number");
                            String phoneNumber = userInput.nextLine();

                            System.out.println("Please enter your email address");
                            String email = userInput.nextLine();

                            //Give an ID

                            System.out.println("Please enter a username you would like to use");
                            String username = userInput.nextLine();

                            System.out.println("Please enter a password you would like to use");
                            String password = userInput.nextLine();

                        }
                        else if(registerInput.equals("2")) {
                            registerQuit = false;
                            //Create a Coordinator in Dependent Class

                            //Ask for Coordinator Information
                            System.out.println("Please enter your first name");
                            String userFirstName = userInput.nextLine();

                            System.out.print("Please enter your last name");
                            String userLastName = userInput.nextLine();

                            System.out.println("Please enter your birthdate [input format]");
                            String userBirthDate = userInput.nextLine();

                            System.out.println("Please enter your home address");
                            String userAddress = userInput.nextLine();

                            System.out.println("Please enter your phone number");
                            String phoneNumber = userInput.nextLine();

                            System.out.println("Please enter your email address");
                            String email = userInput.nextLine();

                            //Give an ID

                            System.out.println("Please enter a username you would like to use");
                            String username = userInput.nextLine();

                            System.out.println("Please enter a password you would like to use");
                            String password = userInput.nextLine();

                            boolean ECBool = true;
                            while(ECBool) {
                                System.out.println("Do you want to add an Emergency Contact \n Select: [Y]es or [N]o");
                                String EC = userInput.nextLine();
                            }
                            


                        }
                        else if(registerInput.equals("3")) {
                            registerQuit = false;
                            //Ask for Administrator Information
                        }
                        else {
                            System.out.println("Try again");
                        }
                    }
                }
                else {
                    System.out.println("Try again");
                }

            }
            else if(command.equals("2")) {

            }
            else if(command.equals("3")) {

            }
            else if(command.equals("4")) {

            }
            else if(command.equals("5")) {

            }
            else if(command.equals("6")) {

            }
            else if(command.equals("7")) {
                isRunning = false;
            }
            else {
                System.out.println("Try again");
            }
        };
        userInput.close();
    }

    public static void main(String[] args)    {
        CampDriver campDriver = new CampDriver();
        campDriver.runCampDriver();
    }
}

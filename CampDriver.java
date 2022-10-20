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
                    
                }
                else if(input.equals("2")) {

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

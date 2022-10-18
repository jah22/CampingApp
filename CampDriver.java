import java.util.Scanner;

public class CampDriver {
    public void runCampDriver(){
        Scanner userInput = new Scanner(System.in);

        // running the program
        boolean isRunning = true;

        while(isRunning){
            String command = userInput.nextLine();
            if(command.equals("quit")){
                isRunning = false;
            }
        };
        userInput.close();
    }

    public static void main(String[] args)    {
        CampDriver campDriver = new CampDriver();
        campDriver.runCampDriver();
    }
}

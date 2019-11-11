/**
 * ATMDriver.java
 * 
 * This class is the driver for the ATM object class
 * 
 * @author Ryan Regier
 * Wheaton College, CSCI 235, Fall 2019
 * Project 5
 * Date 10/31/2019
 */
import java.util.Scanner; //for Scanner object in main method
import java.util.InputMismatchException; //for input mismatch exception
public class ATMDriver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); //create scanner object
        ATM myATM = new ATM(); //creates ATM object
        String input = ""; //used for getting input on menu selection
        int amount = 0; //used for user input on withdrawal amount
        boolean cont = true; //initialized as true to force while loop to run
        boolean done = false; //variable used for the try catch loop 
        //user interface
        while(cont) {
            menu(); //prints menu at start of each while loop iteration
            input = scan.next();
            if(input.equalsIgnoreCase("W")){
                done = false;
                while(!done)
                {
                    try
                    {
                        System.out.println("How much do you want to withdraw? Please enter a multiple of 5.");
                        amount = scan.nextInt();
                        if(amount % 5 == 0){
                            myATM.withdraw(amount);
                            System.out.println(myATM);
                            done = true;
                        } 
                    }
                    catch(InputMismatchException e)
                    {
                        scan.nextLine();
                        System.out.println("Not a correctly entered number.");
                        System.out.println("Try again.");
                    }
                }              
            } else if(input.equalsIgnoreCase("T")){
                done = false;
                while(!done)
                {
                    try
                    {
                        System.out.println("How many twenties do you want to add?");
                        amount = scan.nextInt();
                        myATM.addTwenters(amount);
                        System.out.println(myATM);
                        done = true;
                    }
                    catch(InputMismatchException e)
                    {
                        scan.nextLine();
                        System.out.println("Not a correctly entered number.");
                        System.out.println("Try again.");
                    }
                }   
            } else if(input.equalsIgnoreCase("F")){
                done = false;
                while(!done)
                {
                    try
                    {
                        System.out.println("How many fives do you want to add?");
                        amount = scan.nextInt();
                        myATM.addFivers(amount);
                        System.out.println(myATM);
                        done = true;
                    }
                    catch(InputMismatchException e)
                    {
                        scan.nextLine();
                        System.out.println("Not a correctly entered number.");
                        System.out.println("Try again.");
                    }
                } 
            } else if(input.equalsIgnoreCase("Q")){
                System.out.println("Goodbye!");
                cont = false;
            }
        }
        System.out.println();
        System.out.println(myATM); //print output at end
    }

    /**
     * Prints menu to the console
     * Helps avoid excessive print statements in main method
     */
    public static void menu(){
        System.out.println("Please select an option:");
        System.out.println("\tW: withdraw money from the ATM");
        System.out.println("\tT: add twenties to the ATM");
        System.out.println("\tF: add fives to the ATM");
        System.out.println("\tQ: quit");
    }
}
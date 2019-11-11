/**
 * ATM.java
 * 
 * This class represents an ATM
 * 
 * @author Ryan Regier
 * Wheaton College, CSCI 235, Fall 2019
 * Project 5
 * Date 10/31/2019
 */

public class ATM {
    /*
     * Amount of money in ATM
     */
    private double money;
    /*
     * Number of five dollar bills
     */
    private int fiverNum;
    /*
     * Number of twenty dollar bills
     */
    private int twentNum;

    /*
     * Constructor
     * Initializes money to 0.0, fiverNum to 0, and twentNum to 0
     * Creates new ATM object
     */
    public ATM(){
        this.money = 0.0;
        this.fiverNum = 0;
        this.twentNum = 0;
    }

    /*
     * Withdraw method
     * POSTCONDITION: Amount has either been withdrawn from ATM, or error message printed
     * to console
     * @param int amount, the amount to try and withdraw from the ATM
     * 
     */
    public void withdraw(int amount){
        if(adjustBills(amount)){
            this.money -= amount;
            System.out.println("$" + amount + " withdrawn.");
        } else {
            System.out.println("Sorry, the transaction cannot be made.");
        }
    }

    /*
     * Add five dollar bills method
     * POSTCONDITION: Number of five dollar bills has been incremented by num
     * @param int num, the amount of five dollar bills to add to fiverNum
     * 
     */
    public void addFivers(int num){
        this.money += (num * 5);
        this.fiverNum+=num;
    }

    /*
     * Add twenty dollar bills method
     * POSTCONDITION: Number of twenty dollar bills has been incremented by num
     * @param int num, the amount of twenty dollar bills to add to twentNum
     * 
     */
    public void addTwenters(int num){
        this.money += (num * 20);
        this.twentNum += num;
    }

    /*
     * toString method
     * @return String that displays the bank account information
     */
    public String toString(){
        return("ATM now has\n\t" + this.twentNum + " twenty dollar bills and " + this.fiverNum + " five dollar bills: " + "$" + this.money);
    }

    /*
     * Adjust the bills method
     * Goes through the different possible cases
     * Case 1: ATM has both 5 & 20s
     * Case 2: Only has fives
     * Case 3: Only has 20s
     * Will only check bill makeup if amount is divisble by 5 and <= to money in ATM
     *
     * PRECONDITION: receives amount from withdraw method
     * POSTCONDITION: a boolean is returned depending on what bills are in the ATM
     * @param int amount, the amount to test if available to withdraw from money
     * @return boolean, true if able to withdraw, false if unable to withdraw
     */
    private boolean adjustBills(int amount){
        int num = 0; //will serve as variable for while loop
        //will be used to find bill makeup for case 1
        int five = 0; //holds number of fives needed to be decremented if neccessary
        int twent = 0; //holds number of 20s needed to be decremented if neccessary
        if(amount % 5 == 0 && amount <= money){
            if(amount == money){ //if amount == money, reset both variables to 0
                fiverNum = 0;
                twentNum = 0;
                return true;
            }
            if(fiverNum > 0 && twentNum > 0){ //case 1
                //if statements makes sure that it has enough of whichever bill 
                while(num != amount){ //loop until num == amount
                    if((num + 20) <= amount && (twentNum - twent) >= 1){
                        twent++;
                        num+=20;
                    } else if((num + 5) <= amount && (fiverNum - five) >= 1){
                        five++;
                        num+=5;
                    } else {
                        return false;
                    }
                }
                /* update bill numbers by subtracting the amount of bills
                 * counted in the while loop with five and twent from
                 * their respective instance variables
                 */
                fiverNum -= five; 
                twentNum -= twent;
                return true;
            } else if(fiverNum > 0 && twentNum == 0){ //case 2
                if(amount / 5 <= fiverNum){
                    fiverNum -= (amount / 5);
                    return true;
                }
                return false;
            } else if(twentNum > 0 && fiverNum == 0){ //case 3
                if(amount % 20 == 0 && amount / 20 <= twentNum){
                    twentNum -= (amount / 20);
                    return true;
                }
                return false;
            }
        }
        //case 4
        //if didn't pass initial requirements of divisble by 5 and <= money, return false
        return false; 
    }
}
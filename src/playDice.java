import java.util.Scanner;

public class playDice {

    Scanner sc = new Scanner(System.in);
    String keepPlaying;

    double[] multiplier = {0.02,0.04,0.06,0.08,0.10,0.12};
    double mult ,winnings,accBalance = 100.00;
    boolean validBet,validOdd,validMult;
    int bet,odds, dice, count;

    public void playARound(){
        // Placing a bet
        bet = placeBet();
        // Picking a number
        dice = guessSide();
        // Shows a list of available multiplies
        System.out.println("Here are your odd multipliers: ");
        while(count < multiplier.length){
            System.out.println(count + " : "+multiplier[count] + "%");
            count ++;
        }
        // picking a multiplier
        mult = pickMultiplier();

        // rolling the dice
        System.out.println("\nRolling Dice!..... ");
        dice = diceRoll();
        System.out.println("\nDice roll landed on: " + dice+ ". You chose "+ (dice != odds? "Wrong :(": "Right :)"));


        //....................................................
        //Comparing numbers to see if Odds match the dice roll.
        //....................................................

        if ( dice != odds){
            accBalance -= bet;
            System.out.println("Sorry your bet was not successful! :(");
            System.out.println("\nYou now hava a balance of: "+ accBalance + "$");

        }else {
            winnings = Math.round(bet * mult);
            accBalance += winnings;
            System.out.println("Congratulations your bet was successful you won: "+ winnings + "$.");
            System.out.println("\nYou now hava a balance of: "+ accBalance + "$");
        }

    }

    //......................................................
    //Validation Steps to Ensure the user inputs are correct
    //.......................................................

    // validates the bet amount
    public int placeBet(){
        do{
            System.out.println("Account Balance: " + accBalance);
            System.out.print("Place your betting amount: ");
            bet = sc.nextInt();
            validBet = true; // this sets the valid bet to true before checking if it's correct
            if ((bet <= 0) || (bet > accBalance)){
                validBet = false; // this sets the validBet to false after checking and the check fails
                                  // and this is what will cause the loop to happen.
                System.out.println("\nInvalid bet, " + (bet > accBalance? "insufficient balance":"bet amount to low") + "! Try again");
            }
        }
        while(!validBet);
        return bet;
    }

    // Validates the odds selection
    public int guessSide(){
        do{
            System.out.print("\nWhat number do you think the dice will land on between 1 and 6: ");
            odds = sc.nextInt();
            validOdd = true;
            if( (odds <= 0) || (odds >6)){
                validOdd = false;
                System.out.println("\nOut of ranch, Try again");
            }
        }while( !validOdd);
        return odds;
    }

    // validates multiplier selection
    public double pickMultiplier(){
        do {
            try{
                System.out.print("\nSelect a number for the corresponding multiplier: ");
                mult = multiplier[sc.nextInt()];
                System.out.println("\nYou hava chosen: " + mult + "% as your multiplier.");
                validMult = true;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nSorry you selection is out of bounds!, Try again!.");
                validMult = false;
            }
        }
        while(!validMult);
        return mult;
    }

    //...............................................
    //Random Generation step to play the betting game
    //...............................................

    public int diceRoll(){
        return (int)(Math.random() * 6) + 1; // this simulates a standard six sided dice roll
    }

    //.................................................................................
    // Checking the current accBalance to determine if the game should end or continue.
    //.................................................................................


    public boolean askAnotherRound(){
        if (accBalance <= 0) {
            accBalance = 0;
            System.out.println("\nYour current account balance is: " + accBalance);
            System.out.println("\nYou can't place any new bets! The Game has ended Try a new game. :)");
            return false;
        } else {
            System.out.println("Would you like to keep playing: " + "Y or N");
            keepPlaying = sc.next();
            if (keepPlaying.equalsIgnoreCase("N")) {
                System.out.println("Thank you for playing the DiceGame. Hope you enjoyed it. :)");
                return false;
            } else if (keepPlaying.equalsIgnoreCase("Y")) {
                System.out.println("Yeh!!. Let's Keep Playing.");
                return true;
            } else {
                System.out.println("Incorrect input! Try a new game :)");
                return false;
            }

        }
    }
}

import java.util.Scanner;

public class playDice {

    Scanner sc = new Scanner(System.in);
    String keepPlaying;

    public void playARound(){
        double accBalance = 100.00;
        double[] multiplier = {0.02,0.04,0.06,0.08,0.10,0.12};
        double mult =0;
        double winnings;
        int bet;
        int odds;
        int dice;
        int diceMax= 6;
        int diceMin= 1;



        do{
            //......................................................
            //Validation Steps to Ensure the user inputs are correct
            //.......................................................
            int count= 0;
            boolean validBet;
            boolean validOdd;
            boolean validMult;

            do{
                System.out.println("Account Balance: " + accBalance);
                System.out.print("Place your betting amount: ");
                bet = sc.nextInt();
                validBet = true;
                if ((bet <= 0) || (bet > accBalance)){
                    validBet = false;
                    System.out.println("\nInvalid bet, " + (bet > accBalance? "insufficient balance":"bet amount to low") + "! Try again");
                }
            }
            while(!validBet);

            do{
                System.out.print("\nWhat number do you think the dice will land on between 1 and 6: ");
                odds = sc.nextInt();
                validOdd = true;
                if( (odds <= 0) || (odds >6)){
                    validOdd = false;
                    System.out.println("\nOut of ranch, Try again");
                }
            }while( !validOdd);

            System.out.println("Here are your odd multipliers: ");
            while(count < multiplier.length){
                System.out.println(count + " : "+multiplier[count] + "%");
                count ++;
            }
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

            //...............................................
            //Random Generation step to play the betting game
            //...............................................

            System.out.println("\nRolling Dice!..... ");
            dice = (int)(Math.random() * (diceMax-diceMin+1)) + diceMin;
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

            //.................................................................................
            // Checking the current accBalance to determine if the game should end or continue.
            //.................................................................................


            if (accBalance <= 0){
                accBalance = 0;
                System.out.println("\nYour current account balance is: " + accBalance);
                System.out.println("\nYou can't place any new bets! The Game has ended Try a new game. :)");
//                validPlay = false;
                break;
            }else{
                System.out.println("Would you like to keep playing: " + "Y or N");
                keepPlaying = sc.next();
                if (keepPlaying.equalsIgnoreCase("N")){
                    System.out.println( "Thank you for playing the DiceGame. Hope you enjoyed it. :)");
                    break;
                }else if (keepPlaying.equalsIgnoreCase("Y")){
                    System.out.println("Yeh!!. Let's Keep Playing.");
                }else{
                    System.out.println("Incorrect input! Try a new game :)");
                    break;
                }
//                validPlay = true;
            }



//            validPlay = true;
        }
        while(keepPlaying.equalsIgnoreCase("Y"));
    }
}

import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

public class Yatze {      
    static int numberOfPlayers;
    static Scanner in = new Scanner(System.in);
    static LinkedList<Yatze_spiller> players = new LinkedList<>();
    static int dices = 5;

    public static void main(String[] args) {

        // Welcome msg and number of players selection
        System.out.println("Welcome to Yatze. Please select number of players (2-4). 0 will exit");
        try {
            numberOfPlayers = Integer.parseInt(in.nextLine());
        
            while (numberOfPlayers < 2 || numberOfPlayers > 4) {
                if (numberOfPlayers == 0) {
                    System.out.println("Bye :)");
                    return;
                }
                System.out.println("Players can only be 2, 3 or 4!");
                numberOfPlayers = Integer.parseInt(in.nextLine());
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Only digits must be entered. Try again.");
            numberOfPlayers = Integer.parseInt(in.nextLine());
        }


        // Get playernames and generate player objects
        for (int i = 0; i < numberOfPlayers; i++) {
            String name;
            int playerNumber = i + 1;
            System.out.print("Type name of player " + playerNumber + ": ");
            name = in.nextLine();
            players.add(new Yatze_spiller (name, i));
        }
        
        //Starting first round
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < players.size(); j++) {
                roundOne(j, i);
            }
        }
        // bonus check
        System.out.println("BONUS CHECK. SCORE 61 OR ABOVE EQUALS 50pts");
        for (int i= 0; i < players.size(); i++) {
            System.out.println("Player --" + players.get(i).getName() + "-- has " + players.get(i).getPoints() + " points!");
            if (bonusCheck(players.get(i).getPoints())) {
                players.get(i).addPoints(50);
                System.out.println("This results in a bonus! 50 pts added to player --" + players.get(i).getName() + "--");
                System.out.println("Score is now " + players.get(i).getPoints() + " for --" + players.get(i).getName() + "--!" );
            }
            else {
                System.out.println("No bonus for --" + players.get(i).getName() + "-- :(");
            }
        }
        System.out.println();
        System.out.println("Current score is as follows:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player --" + players.get(i).getName() + "-- has " + players.get(i).getPoints() + " points.");
        }
        


        // Starting second round
        System.out.println("Round two starting. Press [Enter] to continue.");


    }

    // turn round one
    public static void roundOne(int playerId, int dicesToCollect) {
            System.out.println("Round one " + "Collect " + dicesToCollect + "'s. Player --" + players.get(playerId).getName() + "-- press [Enter] to roll.");
            System.out.println("Current score for player --" + players.get(playerId).getName() + "-- is " + players.get(playerId).getPoints() + " points! ");
            in.nextLine();
            int numberOfDice = 5;
            int numberOfCorrect = 0;
            for (int i = 1; i <= numberOfDice; i++) {
                int diceValue = rollDice();
                System.out.println("Dice " + i + ": " + diceValue);
                if (diceValue == dicesToCollect) {
                    players.get(playerId).addPoints(diceValue);
                    numberOfCorrect ++;
                } 
            }
            System.out.println("Totalt points for player " + players.get(playerId).getName() + ": " + players.get(playerId).getPoints());

            numberOfDice =  numberOfDice - numberOfCorrect;
            if(numberOfDice == 0) {
                return;
            }
            numberOfCorrect = 0;
            System.out.println("Press [Enter] to roll again");
            in.nextLine();
            
            for (int i = 1; i <= numberOfDice; i++) {
                int diceValue = rollDice();
                System.out.println("Dice " + i + ": " + diceValue);
                if (diceValue == dicesToCollect) {
                    players.get(playerId).addPoints(diceValue);
                    numberOfCorrect++;
                } 
            }

            System.out.println("Totalt points for player " + players.get(playerId).getName() + ": " + players.get(playerId).getPoints());

            numberOfDice =  numberOfDice - numberOfCorrect;
            if(numberOfDice == 0) {
                return;
            }
            numberOfCorrect = 0;

            System.out.println("Press [Enter] to roll again");
            in.nextLine();
            
            for (int i = 1; i <= numberOfDice; i++) {
                int diceValue = rollDice();
                System.out.println("Dice " + i + ": " + diceValue);
                if (diceValue == dicesToCollect) {
                    players.get(playerId).addPoints(diceValue);
                } 
            }
            System.out.println("Totalt points for player " + players.get(playerId).getName() + ": " + players.get(playerId).getPoints());
        }

    // Roll a dice
    public static int rollDice() {
        Random rand = new Random();
        int dice = 1 + rand.nextInt(6);
        return dice;
    }

    // bonus check
    public static boolean bonusCheck(int points) {
        if (points >= 61) {
            return true;
        }
        else {
            return false;
        }
    }
}
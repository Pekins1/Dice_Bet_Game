

public class diceBet {

    public static void main(String[] args) {
        playDice play = new playDice();
        do{
            play.playARound();
        }
        while(play.askAnotherRound());

    }
}
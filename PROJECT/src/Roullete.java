import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Roullete extends Game{
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    private ArrayList<Integer> rouletteNumbers;
    public Roullete(String name) {
    setName(name);
    }
    @Override
    public void playGame(Player player,Casino casino) {
    if(getPlayerPlaying().isEmpty()){setPlayerPlaying(player);}
    boolean addNewGame = true;

    for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
            if(gameH.getGame().getName().equals(this.getName())){addNewGame = false;}
        }

    if(addNewGame){player.getPlayerGameHistories().add(new PlayerGameHistory(this));}
    runGame(player,casino);

    }
    private void runGame(Player player,Casino casino){
        // **** //
        IO.println("How much do you wanna bet we pay 35:1 any numb");
        int bet = input.nextInt();
        input.nextLine();
        int numb = 37;

        while (numb > 0) {
            this.setTotalBets();
            if(numb > 35){
                do {
                    IO.println("What number do you wanna bet on from 1 to 35;");
                    numb = input.nextInt();
                    input.nextLine();
                } while (numb < 0 || numb > 35);
            }
            int ball = rollBall();
            if(ball == numb){

                IO.println("You have won +$" +(bet *35)+bet);
                IO.println("The ball fell at :"+ball + " Your guess was :"+numb);
                IO.println("Current balance :"+player.getBalance());
                player.give((bet *35)+bet);
                for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
                    if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("win");}
                }
                casino.getBank().subsBalance((bet *35)+bet);
            }
            else {
            IO.println("You have lost -$"+bet);
            IO.println("The ball fell at :"+ball +" /"+ "Your guess bet at :" +numb);
            IO.println("Current balance :"+player.getBalance());
            player.take(bet);
            for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
            if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("lose");}
            }
            this.setTotalPayouts(bet);
            casino.getBank().setBalance(bet);
            }
            numb = changeBet(numb);
        }

    }
    private boolean keepPlaying(){
    String answer;
    do {
        IO.println("Do you wanna keep betting yes or not");
        answer = input.nextLine();
        if(answer.equals("not")){return false;}
        else if(answer.equals("yes")){return true;}

    }while(!answer.equals("not") && !answer.equals("yes"));
    return true;
    }
    private int changeBet(int n){
    if(!keepPlaying()){return -1;}
    while(true){
    IO.println("#1 Change bet number");
    IO.println("#2 Keep bet");
    String answer = input.nextLine();
    switch (answer){
        case "1"->{return 36;}
        case "2"->{return n;}
        }
    }

    }
    private int rollBall(){
    return random.nextInt(1,36);
    }
}

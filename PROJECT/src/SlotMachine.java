import java.util.ArrayList;
import java.util.Scanner;

public class SlotMachine extends Game{
    static Scanner scanner = new Scanner(System.in);
    ArrayList<slotsSimbols> simbols;
    public SlotMachine(String name) {
    simbols = new ArrayList<>(3);
    setName(name);
    }

    @Override
    public void playGame(Player player,Casino casino) {
        boolean addNewGame = true;
        if(getPlayerPlaying().isEmpty()){setPlayerPlaying(player);}
        player.setState("Playing at table");

        for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
            if(gameH.getGame().getName().equals(this.getName())){addNewGame = false;}
        }

        if(addNewGame){player.getPlayerGameHistories().add(new PlayerGameHistory(this));}

        slotMachineRun(player,casino);
        getPlayerPlaying().remove(player);
        slotMachineRun(player,casino);
        getPlayerPlaying().remove(player);

    }
    private void setSlots(){
        for(int i = 0; i < 3; i++){
        simbols.add(new slotsSimbols());
        }

    }
    private void print(){
        IO.println("***".repeat(3));
        for(slotsSimbols simbolsIn : simbols){
            IO.print(simbolsIn.getSimbol()+ " ");
        }
        IO.println();
        IO.println("***".repeat(3));
    }
    private double checkValue(double bet){
    double value = 0;
    int index = 0;
    int timesFound = 0;
    slotsSimbols currentS = simbols.get(index);
    while(true){

    for(int i = 0; i < simbols.size(); i++){
    if(simbols.get(i).getSimbol() == currentS.getSimbol()){timesFound++;}
    }

    if(timesFound < 1){index++; currentS = simbols.get(index); timesFound = 0; continue;}


    value = calculateValue(timesFound,currentS,bet);
    break;
    }


    return value;
    }
    private double calculateValue(int times,slotsSimbols simbol,double bet){
    if(times == 2){

switch (simbol.getSimbol()){
    case '&'->{return (bet * 0.7) * 2;}
    case '@'->{return (bet * 0.9) * 2;}
    case '#'->{return (bet * 1.2) * 2;}
    case '$'->{return (bet * 1.3) * 2;}
    case '7'->{return (bet * 1.5) * 2;}
}

}

    else if (times > 2){

    switch (simbol.getSimbol()){
        case '&'->{return (bet * 0.7) * 3;}
        case '@'->{return (bet * 0.9) * 3;}
        case '#'->{return (bet * 1.2) * 3;}
        case '$'->{return (bet * 1.3) * 3;}
        case '7'->{return (bet * 1.5) * 3;}
    }
}

return 0;
}
    private void slotMachineRun(Player player,Casino casino){
        IO.println("How much do you wanna bet");
        double bet = scanner.nextDouble();
        scanner.nextLine();
        boolean keepBetting = true;

        while(keepBetting){
        setTotalBets();
            if(player.getBalance() < bet){IO.println("Please recharge to play more"); return;}
            if(simbols.size() > 1){simbols.clear();}
            setSlots();
            print();
            double value = checkValue(bet);
            if(value <= 0){
                player.take(bet);
                IO.println("You have lost -" +bet);
                IO.println("Current balance :" +player.getBalance());
                for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
                if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("lose");}
                }
                setTotalPayouts(bet);
                casino.getBank().setBalance(bet);

            }
            else if (value > 0){
                player.give(value);
                IO.println("You have won +" +value);
                IO.println("Current balance :" +player.getBalance());
                for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
                    if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("win");}
                }
                casino.getBank().subsBalance(value);
            }
            boolean correctAnswer = false;
            while (!correctAnswer) {
                IO.println("#1 Keep betting");
                IO.println("#2 Leave");
                String playerAnswer = scanner.nextLine();

                switch (playerAnswer) {
                    case "1" -> {correctAnswer = true;}
                    case "2" -> {keepBetting = false; correctAnswer = true;}
                    default -> {IO.print("Try again");}
                }
            }
        }
    }
}

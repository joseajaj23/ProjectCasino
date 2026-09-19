import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    private static Scanner input = new Scanner(System.in);
    private ArrayList<Player> playersAtTable = new ArrayList<>(3);
    private ArrayList<Game> games;
    private static int ID=1;
    private String tableID;


    public Table() {
    tableID="Table00"+ID;
    ID++;
    games = new ArrayList<>();
    setTableGames();

    }

    private void setTableGames(){
    games.add(new Roullete("Roulette"));
    games.add(new SlotMachine("SlotMachine"));
    games.add(new BlackJack("BlackJack"));
    }

    public void showTableCapacity(){
    if(playersAtTable.size() == 3){IO.println("Table full");}
    IO.print(" With space for " + (3 - playersAtTable.size()));
    }

    public String getTableID() {
        return tableID;
    }


    public void playGamesPanel(Player player,Casino casino){
        playersAtTable.add(player);
        boolean run = true;
        while (run) {
            IO.println("Games to play at " + getTableID());
            IO.println("1# Slot machine");
            IO.println("2# Roullete");
            IO.println("3# BlackJack");
            IO.println("4# Leave NOT TABLE");
            IO.println("#5 Leave table;");

            try {

            String answerP= input.nextLine();


            switch (answerP){

                case "1"->{games.get(1).playGame(player,casino);}
                case "2"->{games.get(0).playGame(player,casino);}
                case "3"->{games.get(2).playGame(player,casino);}
                case "4" ->{run = false;}
                case "5" ->{playersAtTable.remove(player); player.setState("Left table"); run = false;}


            }

            } catch (Exception e) {
                IO.println("Invalid output");
            }
        }
    }
    public void playGamesPanelPlayerIn(Player player,Casino casino){
        boolean run = true;
        while (run) {
            IO.println("Games to play at " + getTableID());
            IO.println("1# Slot machine");
            IO.println("2# Roulette");
            IO.println("3# BlackJack");
            IO.println("4# Leave NOT TABLE");
            IO.println("#5 Leave table;");

            try {

                String answerP= input.nextLine();


                switch (answerP){

                    case "1"->{games.get(1).playGame(player,casino);}
                    case "2"->{games.get(0).playGame(player,casino);}
                    case "3"->{games.get(2).playGame(player,casino);}
                    case "4" ->{run = false;}
                    case "5" ->{
                        playersAtTable.remove(player);
                        player.setState("Left table");
                        removePlayer(this,player);
                        run = false;}


                }

            } catch (Exception e) {
                IO.println("Invalid output");
            }
        }
    }




    public ArrayList<Player> getPlayersAtTable() {
        return playersAtTable;
    }
    private void removePlayer(Table table,Player player){
    for(Game gameIn : table.getGames()){
    gameIn.getPlayerPlaying().remove(player);
    }
    }
    protected void getInfo(){
    IO.println(tableID);
    IO.println("PlayerAtTables");
    for(Player playerIn : playersAtTable){
    IO.print(playerIn.getPlayerID() + " "); IO.print(playerIn.getPlayerName());
    IO.println();
    }
    IO.println("Game");
    for(Game gameIn : games){
    IO.println(gameIn.toString());
    }

    }

    public ArrayList<Game> getGames() {
        return games;
    }
}

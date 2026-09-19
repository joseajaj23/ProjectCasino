import java.util.*;

public class Casino {
    private static final Scanner input = new Scanner(System.in);
    private final ArrayList<Table> tables;
    private final ArrayList<Player> players;
    private CasinoBank bank;
    private final String CasinoName;
    private ArrayList<GameStats> GamesStats;

    public Casino() {
        CasinoName = "Casino01";
        bank = new CasinoBank();
        players = new ArrayList<>();
        tables = new ArrayList<>();
        GamesStats = new ArrayList<>();
        makeTables();
    }


    public ArrayList<Table> getTables() {
        return tables;
    }
    private void makeTables() {
        tables.add(new Table());
        tables.add(new Table());
        tables.add(new Table());
        setStatsField();
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void seeCassinoPanel() {

        boolean run = true;
        while (run) {
            IO.println("*****".repeat(5));
            IO.println("Welcome " + CasinoName);
            IO.println("#1 Enter player");
            IO.println("#2 See Casino balance");
            IO.println("#3 See tables");
            IO.println("#4 See list of players");
            IO.println("#5 See top 5 best players");
            IO.println("#6 See games which give more money to the Casino");

            String answer = input.nextLine();
            switch (answer) {

                case "1" -> {enterPlayer();}
                case "2" -> {getBalanceC();}
                case "3" -> {seeTables();}
                case "4" -> {seePlayers();}
                case "5" -> {seeTopFive();}
                case "6" -> {seeStats();}


            }
        }
    }
    public void setPlayers(Player player) {
        this.players.add(player);
    }
    private void enterPlayer() {
        Player player = new Player();
        player = player.playerPanel(this);
        if (player == null) {return;}
        playerChooseTable(player);
    }
    private void playerChooseTable(Player player) {
        boolean run = true;
        if(seeIfPlayerAtTable(player,this)){return;}

        while (run) {

            IO.println("What table do you wanna be in we have these many");
            IO.println("Check if they are available");
            for (int i = 0; i < tables.size(); i++) {
                IO.println("#" + i + " Table");
            }
            for (Table tablesAT : tables) {
                IO.print(tablesAT.getTableID());
                tablesAT.showTableCapacity();if(seeIfPlayerAtTable(player,this)){return;}
                IO.println();
            }
            IO.println("#"+tables.size() +" Leave");
            String answerOfPlayer = input.nextLine();

            if(answerOfPlayer.equals(String.valueOf(tables.size()))){run = false;}

            for(int i =0; i < tables.size(); i++){
                if(String.valueOf(i).equals(answerOfPlayer)){
                    if(checkIfTableFull(tables.get(i))){IO.println("Table Picked FUll");continue;}
                    player.setCurrentTable(tables.get(i));
                    tables.get(i).playGamesPanel(player,this);
                return;}
            }

        }
    }
    private boolean seeIfPlayerAtTable(Player player,Casino casino){
    for(Table tableOfCassino : tables){
        for(Player playerInTable : tableOfCassino.getPlayersAtTable()){
            if(playerInTable == player){tableOfCassino.playGamesPanelPlayerIn(player,this);return true;}
        }
    }
    return false;
    }
    private boolean checkIfTableFull(Table table){
        return table.getPlayersAtTable().size() >= 3;
    }
    private void seeTables(){
    for(Table tableIn : tables){
    tableIn.getInfo();
    }
    }
    private void seeStats(){
    calculateStats();
    for(GameStats gamesStatsIn : GamesStats){
    IO.println(gamesStatsIn.toString());
    }

    }
    private void sortStats(){
    double highPay;
    int indexOfHigh = 0;
    int index = 0;
    boolean highFound;

    do {
        highFound = false;
        highPay = GamesStats.get(index).getTotalPayOut();

      for(int i = index; i < GamesStats.size(); i++){
      if(GamesStats.get(i).getTotalPayOut() > highPay){indexOfHigh = i; highPay = GamesStats.get(i).getTotalPayOut(); highFound = true;}}

        if(highFound){
            Collections.swap(GamesStats,index,indexOfHigh);
            index++;
            continue;
        }
        index++;

    }while (index < GamesStats.size());



    }
    private void calculateStats(){
    int index = 0;
    while(index < 3){
    for(int i = index; i < tables.size(); i++){

        GamesStats.get(index).setTotalBets(tables.get(i).getGames().get(index).getTotalBets());
        GamesStats.get(index).setTotalPayOut(tables.get(i).getGames().get(index).getTotalPayouts());


    }
    index++;
    sortStats();


    }

    }
    private void setStatsField(){
    for(Game gameIn : tables.getFirst().getGames()){
        GamesStats.add(new GameStats(gameIn.getName()));
    }
    }
    private void getBalanceC(){
    getBank().getBalance();
    }
    private void seePlayers(){

    for(Player playerIn : getPlayers()){
    IO.println(playerIn.toString());
    }

    }
    private void seeTopFive(){
    ArrayList<Player> topFive = setTopFive();

    for(Player playerTop : topFive){
    IO.println(playerTop.toString() +" /:"+ playerTop.getBalance());
    }
    }
    private ArrayList<Player> setTopFive(){
    int count = getPlayers().size();
    ArrayList<Player> topFive = new ArrayList<>();
    int index = 0;
    Player  P_highest;
    boolean added = false;
    int indexH = 0;
    ArrayList<Player> copyOfPlayers = new ArrayList<>(getPlayers());

    while (index < count){
    P_highest = copyOfPlayers.get(index);
    added = false;

    for(int i = index; i < count; i++){
    if(copyOfPlayers.get(i).getTotalGained() > P_highest.getTotalGained()){

    P_highest = copyOfPlayers.get(i);
    indexH = i;
    added = true;

    }
    }

    if(added){Collections.swap(copyOfPlayers,index,indexH); index++; continue;}
    index++;

    }
    count = 5;
    if(copyOfPlayers.size() < 5){count = copyOfPlayers.size();}

    for(int i = 0; i < count; i++){
    topFive.add(copyOfPlayers.get(i));
    }



    return topFive;
    }
    public CasinoBank getBank() {
        return bank;
    }
    //private void setPlayersIn(){
    //        getPlayers().add(new Player("Jose23","eltito23",new Bank(),10));
    //     getPlayers().add(new Player("Maria23","eltito23",new Bank(),222));
    //      getPlayers().add(new Player("Fael23","eltito23",new Bank(),540));
    //       getPlayers().add(new Player("Mieiran23","eltito23",new Bank(),60));
    //    }
}





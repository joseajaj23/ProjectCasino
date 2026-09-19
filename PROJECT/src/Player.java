import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    static Scanner input = new Scanner(System.in);
    private static int ID = 1;
    private int playerID;
    private String playerName;
    private String password;
    private Bank bank;
    private double balance;
    private ArrayList<PlayerGameHistory> playerGameHistories;
    private double totalGained;
    private String state;
    private Table currentTable;

    public Player() {

    }
    public Player(String playerName, String password, Bank bank, double balance) {
        this.playerID = ID;
        ID++;
        this.playerName = playerName;
        this.password = password;
        this.bank = bank;
        this.balance = balance;
        this.totalGained = balance * 2;

    }
    protected Player playerPanel(Casino casino){

    IO.println("Welcome to the player Panel Firs you need to create an account");
    Player player = enterCasinoPlayer(casino);
    while(player == null){
    player = enterCasinoPlayer(casino);}

    // If true then enter //

    if(PanelPlayerIn(player) == null){return null;}



    return player;
    }
    private void alterPlayer(String playerName , String password){
        ID++;
        this.playerID = ID;
        this.playerGameHistories = new ArrayList<>();
        this.playerName = playerName;
        this.password = password;
        this.balance = 200;
        this.currentTable =  null;
    }
    private Player registerPLayer(Casino casino){

    int index =0;
    boolean specialCharecter = false;
    String specialCharacters = "@#$*():?><.,";
    IO.println("What is your name and password name");
    String nameIn = input.nextLine();
    IO.println("What is your password to be sir " + nameIn);
    String passwordIn = input.nextLine();
    for(Player players : casino.getPlayers()){
        if(players.getPlayerName().equals(nameIn)){IO.println("Name already taken");return null;}
    }
     if(nameIn.length() < 4){IO.println("Name too short"); return null;}
     if(passwordIn.length() <6){IO.println("Password too short"); return null;}

    while(index < nameIn.length()){
    for(int i = 0; i < specialCharacters.length(); i++){
    if(passwordIn.charAt(index) == specialCharacters.charAt(i)){specialCharecter = true; break;}
    }
    if(specialCharecter){break;}
    index++;
    }

    alterPlayer(nameIn,passwordIn);


bank = new Bank();
boolean createdBankAcount = bank.bankInterface();
while (!createdBankAcount){
    createdBankAcount = bank.bankInterface();
}
return this;
}
    private Player accessIn(Player player){
    if(player ==  null){IO.println("Access denied");return  null;}
    return player;
    }
    private Player logIN(Casino casino){
    boolean nameIs = false;
    IO.println("What is your name");
    String name = input.nextLine();
    IO.println("What is the password");
    String password = input.nextLine();

    for(Player players : casino.getPlayers()){
    if(players.getPlayerName().equals(name)){nameIs = true;}

    if(nameIs){
        if(players.getPassword().equals(password)){return players;}
    }
    }
    return null;
    }
    // Player Actions in //
    public Player enterCasinoPlayer(Casino casino){
        boolean run = true;
        while(run) {

            IO.println("**********");
            IO.println("#1 Register player at casino");
            IO.println("#2 Log in Player");
            IO.println("#3 Exit");
            String answer = input.nextLine();
            switch (answer){

                case "1" ->{
                    Player player = registerPLayer(casino);
                    if(player == null){IO.println("Try again"); continue;}
                    casino.setPlayers(player);
                    return player;
                }

                case "2" ->{
                    Player playerIn= accessIn(logIN(casino));
                    if(playerIn!= null){return playerIn;};
                }
                case "3" -> {run = false;}

            }

        }
        return null;
    }
    private Player PanelPlayerIn(Player player){
    Player playerIn = null;
    boolean run = true;

    while (run) {

        IO.println("****".repeat(9));
        IO.println("1# Log out");
        IO.println("2# View Information");
        IO.println("3# Deposit Credit");
        IO.println("4# WithDraw Credit");
        IO.println("5# View Transaction history");
        IO.println("6# View Game History");
        IO.println("7# Play Game");


        String answer = input.nextLine();

        switch (answer){

         case "1"->{return playerIn;}
         case "2"->{viewInformation(player);}
         case "3"->{deposit(player);}
         case "4"->{withdraw(player);}
         case "5"->{viewTransactionHistory(player);}
         case "6"->{viewGameHistory(player);}
         case "7"->{run = false;}
         default ->{IO.println("Invalid input");}
        }

    }


    return player;
    }
    private void viewInformation(Player player){
    if(player.getCurrentTable() == null){IO.println("Name " + player.getPlayerName() + " Balance:$"+player.getBalance() + " CurretTable: " +"None");return; }
    IO.println("Name " + player.getPlayerName() + " Balance:$" +player.getBalance() + " CurretTable: " +player.getCurrentTable().getTableID());

    }
    private boolean deposit(Player player){
    boolean transaction = false;
    IO.println("Deposit balance to your casino account actual balance Casino :$"+player.getBalance());
    IO.println("What amount you wanna deposit");
    double amount = input.nextDouble();
    input.nextLine();

    if(amount <=0){IO.println("too low amount"); return transaction;}
    BankAccount account = player.getBank().bankInterface(amount);
    if(account == null){return  transaction;}
    account.withdrawPay(amount);
    player.setBalance(amount);
    transaction = true;
    IO.println("Deposit successful!");
    return transaction;
    }
    private boolean withdraw(Player player){

    boolean transaction = false;
    IO.println("Withdraw balance to your Bank account actual balance Casino :$"+player.getBalance());
    IO.println("How much do you wanna withdraw");
    double withdrawAmount = input.nextDouble();
    input.nextLine();
    if(withdrawAmount > player.getBalance()){IO.println("You cannot withdraw above the balance!"); return false;}

    BankAccount account = player.getBank().bankInterface(withdrawAmount);

    if(account == null){return  transaction;}

    transaction = true;
    account.deposit(withdrawAmount);
    if(!account.checkCode()){return false;
    }
    player.setBalanceMinus(withdrawAmount);
    IO.println("Withdraw successful!");
    return transaction;
    }
    private void viewTransactionHistory(Player player){
    player.getBank().seeHistory();
    }
    private void viewGameHistory(Player player){
    if(player.getPlayerGameHistories().isEmpty()){IO.println("Nothing to print History empty");}
    for(PlayerGameHistory history : player.getPlayerGameHistories()){
    history.printPlayerHistory();
    }
    }
    public void setState(String state) {
        this.state = state;
    }
    // ****************** //
    public String getPlayerName() {
        return playerName;
    }
    public String getPassword() {
        return password;
    }
    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }
    protected void take(double value){
    balance -= value;
    }
    protected void give(double value){
    balance += value;
    }
    public double getBalance() {
        return balance;
    }
    public int getPlayerID() {
        return playerID;
    }
    @Override
    public String toString() {
        return "Player{" +
                "Name='" + getPlayerName() + '\'' +
                "ID =:"+ getPlayerID();
    }
    protected double getTotalGained() {
        return totalGained;
    }
    public ArrayList<PlayerGameHistory> getPlayerGameHistories() {
        return playerGameHistories;
    }

    public Bank getBank() {
        return bank;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }
    public void setBalanceMinus(double balance) {
        this.balance -= balance;
    }
}

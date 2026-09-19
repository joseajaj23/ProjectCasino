import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack extends Game{
    static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsOnTableToPlay;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;

    public BlackJack(String name) {
    cards = new ArrayList<>();
    playerCards = new ArrayList<>();
    dealerCards = new ArrayList<>();
    setName(name);
    generateFullDeck();
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
    cardsOnTableToPlay = new ArrayList<>(cards);
    boolean run = true;
    double bet = 0;
    double value = 0;
    int dealerHandValue = 0;
    int playerHandValue = 0;
    boolean winOrLose = true;


    while(run){

    while(bet > player.getBalance() || bet <= 0) {
        IO.println("How much do you wanna bet");
        bet = scanner.nextDouble();
        scanner.nextLine();
    }

    if(cardsOnTableToPlay.isEmpty()){cardsOnTableToPlay = new ArrayList<>(cards);}

    giveCard(2);
    readCard("player");
    boolean runInner = true;
    double currentBet = bet;
    while(runInner){
        setTotalBets();
    double betIn = askWhatToDoNext(bet);
        if(betIn >= currentBet){bet = betIn; runInner = false;}
    readCard("player");
    }

    while(getDeckValue(dealerCards) < 17){
    seeIfDealer();
    }

    dealerHandValue = getDeckValue(dealerCards);
    playerHandValue = getDeckValue(playerCards);

    if(dealerHandValue > 21 && playerHandValue > 21){IO.println("Nothing lost Nothing won, tie"); dealerCards.clear(); playerCards.clear(); continue;}
    else if(dealerHandValue > 21){winOrLose = true;}
    else if(dealerHandValue < 21 && playerHandValue > 21){winOrLose = false;}
    else if(dealerHandValue > playerHandValue){winOrLose = false;}
    else if (dealerHandValue == playerHandValue){winOrLose = false;}
    IO.println("Dealer Hand Value " +dealerHandValue);
    IO.println("Player Hand Value " +playerHandValue);
    if(winOrLose){
        value = calculateWin(bet);
        IO.println("You have won $:"+value);
        player.give((value));
        for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
            if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("win");}
        }
        casino.getBank().subsBalance(value);
    }

    else{IO.println("You have lost $-:"+bet);
        player.take(bet);
        for(PlayerGameHistory gameH : player.getPlayerGameHistories()){
            if(gameH.getGame().getName().equals(this.getName())){gameH.UpdateHistory("lose");}
        }
        this.setTotalPayouts(bet);
        casino.getBank().setBalance(bet);
    }

    IO.println("Do you wanna keep Playing");
    String A = scanner.nextLine();

    if(A.equals("not")){dealerCards.clear(); playerCards.clear(); cardsOnTableToPlay.clear(); run = false;}
    else if(A.equals("yes")){dealerCards.clear(); playerCards.clear();}
    }

    }

    private double calculateWin(double bet){
    double valueIn = 0;
    if(getDeckValue(dealerCards) > 21){valueIn = (bet *2); return valueIn;}
    if(hasAce(playerCards)){
        if(howManyAces(playerCards) == 2){valueIn = bet * 2.3;return valueIn;}
    }
    valueIn = bet*1.7;

    return valueIn;
    }
    private void seeIfDealer(){
    if(getDeckValue(dealerCards) < 17){giveCardToDealer();}
    }
    private double askWhatToDoNext(double bet){
    boolean run = true;
    double newbet = bet;
    while (run){

    IO.println("#1 Stand");
    IO.println("#2 take");
    IO.println("#3 doubleDown (Take one more card and multiply your bet)");

    String answer = scanner.nextLine();
    switch (answer){
    case "1"->{run = stand(); }
    case "2"->{run = takeMore();}
    case "3"->{newbet = doubleDown(bet); run = false;}
    default ->{IO.println("Please take one option");}
    }
    }
    return newbet;
    }
    private double doubleDown(double bet){
    giveCardToPlayer();
    return bet * 2;
    }
    private boolean stand(){
    return false;
    }
    private boolean takeMore(){
    giveCardToPlayer();
    return false;
    }
    private void generateFullDeck() {

        String[] suits = {"❤️", "♦️", "♠️", "♣️"};
        String[] suitNames = {"Hearts", "Diamonds", "Spades", "Clubs"};

        String[] ranks = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] rankNames = {"Ace", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        int[] values = {11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2};


        for (int s = 0; s < suits.length; s++) {
            for (int r = 0; r < ranks.length; r++) {
                String fullName = rankNames[r] + " of " + suitNames[s];
                this.cards.add(new Card(fullName, values[r], ranks[r], suits[s]));
            }
        }
    }
    private void readCard(String whoseDeck){
    if(whoseDeck.equals("player")){
     for(Card cardIn : playerCards){
        cardIn.printCard();
     }
     IO.println("Value of your hand :"+getDeckValue(playerCards)+"/21");
     IO.println();
     dealerCards.getFirst().printCard();
     IO.println("Value of first dealer card");
     IO.println(dealerCards.getFirst().getCardValue());
    }
    else if(whoseDeck.equals("dealer/player")){
    for(Card cardIn : playerCards){
            cardIn.printCard();
        }
    IO.println("Value of your hand :"+getDeckValue(playerCards)+"/21");
    for(Card cardIn : dealerCards){
        cardIn.printCard();
    }
    IO.println("Value of dealer hand :"+getDeckValue(dealerCards)+"/21");
    }}
    private int getDeckValue(ArrayList<Card> deck){

    int value= 0;

    for(Card cardIn : deck){
     if(cardIn.getType().equals("A")){continue;}
     value += cardIn.getCardValue();
    }
    if(hasAce(deck)){
    switch (howManyAces(deck)){
        case 1->{if(addOneOrTen(value)){value = addOneOrTeen3(value);}}
        case 2->{if(addOneOrTen(value)){value = addOneOrTen2(value);}}
        case 3->{if(addOneOrTen(value)){value += 21; return value;} value += 3; }
    }
    }

    return value;
    }
    private int addOneOrTeen3(int value){
    int Mvalue =0;
    if(value+10 > 21){Mvalue = value+1;}
    else{Mvalue = value+10;}
    return Mvalue;
    }
    private boolean addOneOrTen(int value){
    if(value < 12){return true;}
    return false;
    }
    private int addOneOrTen2(int value){
        int Mvalue = 0;
        if(value > 11){Mvalue += value + 2;}
        else if (value < 10){Mvalue += value + 11;}
        return Mvalue;
    }
    private int howManyAces(ArrayList<Card> deck){
    int timesAce = 0;
    for(Card cardIn : deck){
    if(cardIn.getRank().equals("A")){timesAce++;}
    }
    return timesAce;
    }
    private boolean hasAce(ArrayList<Card> deck){
    boolean has = false;
    for(Card cardIn : deck){
    if(cardIn.getType().equals("A")){has = true; break;}
    }
    return has;
    }
    private void giveCard(int times){
    for(int i = 0; i < times; i++){
    giveCardToPlayer();
    giveCardToDealer();
    }
    }
    private void giveCardToPlayer(){
    int randomIn = random.nextInt(0,cardsOnTableToPlay.size());
    playerCards.add(cardsOnTableToPlay.get(randomIn));
    cardsOnTableToPlay.remove(randomIn);

    }
    private void giveCardToDealer(){
    int randomIn = random.nextInt(0,cardsOnTableToPlay.size());
    dealerCards.add(cardsOnTableToPlay.get(randomIn));
    cardsOnTableToPlay.remove(randomIn);
    }

}

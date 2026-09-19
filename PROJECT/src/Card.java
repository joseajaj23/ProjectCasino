public class Card {
private String cardName;
private int cardValue;
private String type;
private String rank;
    public Card(String cardName, int cardValue,String type,String rank) {
        this.type = type;
        this.rank = rank;
        this.cardName = cardName;
        this.cardValue = cardValue;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String getType() {
        return type;
    }
    protected void printCard() {
        // Pad the rank string so the borders stay perfectly aligned
        String leftRank = String.format("%-2s", rank);  // Left-aligned
        String rightRank = String.format("%2s", rank);  // Right-aligned

        System.out.println("*******");
        System.out.println("*  "+leftRank+"  *");
        System.out.println("*  "+type    +"  *"); // Displays your working suit emoji
        System.out.println("*  "+rightRank+"  *");
        System.out.println("*******");
    }

    public String getRank() {
        return rank;
    }
}

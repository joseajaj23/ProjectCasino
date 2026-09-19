//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.ArrayList;

public class Game {
   private static double ID = 1;
   private String gameId;
   private ArrayList<Player> playerPlaying;
   private String name;
   private int totalBets;
   private double totalPayouts;

    public void setPlayerPlaying(Player player) {
    this.playerPlaying.add(player);
    }
    public ArrayList<Player> getPlayerPlaying() {
        return playerPlaying;
    }
    public Game() {
    ID++;
    gameId = "Game0"+ID;
    playerPlaying = new ArrayList<>();
    }
    public void playGame(Player player,Casino casino) {
        playerPlaying.add(player);

    }
    public int getTotalBets() {
        return totalBets;
    }
    public double getTotalPayouts() {
        return totalPayouts;
    }
    public String getGameId() {
        return this.gameId;
    }
    public String getName() {
        return this.name;
    }
    public void setTotalBets() {
        this.totalBets++;
    }
    public void setTotalPayouts(double totalPayouts) {
        this.totalPayouts += totalPayouts;
    }
    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", playerPlaying=" + playerPlaying +
                ", name='" + name + '\'' +
                ", totalBets=" + totalBets +
                ", totalPayouts=" + totalPayouts +
                '}';
    }
    public void setName(String name) {
        this.name = name;
    }

}

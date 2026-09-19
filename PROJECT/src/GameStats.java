public class GameStats {
private String GameName;
private double totalPayOut;
private int totalBets;


    public GameStats(String gameName) {
        GameName = gameName;
    }

    public void setTotalPayOut(double totalPayOut) {
        this.totalPayOut += totalPayOut;
    }

    public void setTotalBets(int totalBets) {
        this.totalBets += totalBets;
    }



    public double getTotalPayOut() {
        return totalPayOut;
    }


    @Override
    public String toString() {
        return "GameName='" + GameName + '\'' +
                ", totalPayOut=" + totalPayOut +
                ", totalBets=" + totalBets;
    }
}

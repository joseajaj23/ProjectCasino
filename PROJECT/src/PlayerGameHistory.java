public class PlayerGameHistory {
private Game game;
private double wins;
private double losses;
private String lastPlay;

    public PlayerGameHistory(Game game) {
        this.game = game;

    }
    protected void UpdateHistory(String type){

        if(type.equals("win")){
        this.wins++;
        lastPlay = "WIN!";
        }
        else if (type.equals("lose")){
        losses++;
        lastPlay = "LOST!";
        }

    }
    public void printPlayerHistory(){
    IO.println(game.getGameId() +" GameNAME "+ game.getName()+ " WINS: "+ wins +"| Losses:" +losses +" / "+" LastGame"+ lastPlay);
    }

    public Game getGame() {
        return game;
    }
}

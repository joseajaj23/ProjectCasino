public class CasinoBank {
private String name;
private double balance;


    public CasinoBank() {
        this.name = "CasinoBank";
        this.balance = 100000;
    }

    public void getBalance() {
    IO.println("Balance from " + name + " :$"+balance);
    }

    protected void setBalance(double balance) {
        this.balance += balance;
    }
    protected void subsBalance(double balance){
    this.balance -= balance;
    }

}

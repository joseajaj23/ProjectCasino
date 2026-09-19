//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private String password;
    private double balance;
    private String accountTransferenceCode;
    private ArrayList<Register> register_list;

    public BankAccount(String name, String password, double balance,String accountTransferenceCode) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.accountTransferenceCode = accountTransferenceCode;
        this.register_list = new ArrayList();
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public boolean withdrawPay(double amount) {
        boolean withdraw = false;
        if (amount > balance) {
            IO.println("Too high amount");
            return false;
        } else if (this.balance <= amount) {
            IO.println("Not enough balance");
            return false;
        } else {
            String register_Input = this.balance + "-" + amount + " :" + (this.balance - amount);
            this.register_list.add(new Register(register_Input));
            this.balance -= amount;
            return true;
        }
    }
    public void deposit(double amount){
        this.balance += amount;
        String register_Input = this.balance + "+" + amount + " :" + (this.balance + amount);
        this.register_list.add(new Register(register_Input));
    }
    protected boolean checkCode(){
    IO.println("Enter the transference code to finish deposit");
    String code = scanner.nextLine();
    if(code.equals(getAccountTransferenceCode())){return true;}
    IO.println("Transference code wrong please enter the real code");
    return false;
    }
    private String getAccountTransferenceCode() {
        return accountTransferenceCode;
    }
    public ArrayList<Register> getRegister_list() {
        return register_list;
    }
}

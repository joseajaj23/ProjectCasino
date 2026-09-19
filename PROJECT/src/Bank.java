

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    static Scanner input = new Scanner(System.in);
    static int accountID;
    private int ID;
    private String name;
    private String password;
    private double balance;
    private ArrayList<BankAccount> bankAccounts = new ArrayList();

    public Bank() {
    }
    private BankAccount interSystemCheckAccount(){
    BankAccount accountB = null;
    boolean pass = false;
    IO.println("What is your username In Bank");
    String userName = input.nextLine();
    IO.println("What is the password of " + userName);
    String passwordUser = input.nextLine();
    // ******* //
    boolean namePass = false;
    for(BankAccount account : bankAccounts){
    if(userName.equals(account.getName())){namePass = true;}

    if(!namePass){IO.println("User name not register in bank sorry try again");}

    for(BankAccount account2 : bankAccounts){
        if(passwordUser.equals(account2.getPassword())){pass = true;}
    }}
    if(!pass){IO.println("Password incorrect try again");}

    for(int i = 0; i<bankAccounts.size();i++){
    if(bankAccounts.get(i).getName().equals(userName)){accountB = bankAccounts.get(i); break;}
    }

    return accountB;
    }
    public BankAccount bankInterface(double amount) {
        boolean run = true;
        BankAccount account = null;
        while(run) {
            IO.println("WELCOME TO THE BANK");
            IO.println("1. Login");
            IO.println("2. Exit");

            try {
                switch (input.nextLine()) {
                    case "1"->{
                        account=this.interSystemCheckAccount();
                        if(account == null)return null;
                        run = false;
                    }

                    case "3"->{run = false;}
                }
            } catch (Exception var7) {
                IO.println("ERROR IN INPUT");
            }
        }
    return account;
    }
    public boolean bankInterface() {
        boolean run = true;

        while(run) {
            IO.println("WELCOME YOU MUST CRAETE A BANK ACCOUNT");

                if(this.createAccount()) {
                    IO.println("Account created successfully!");
                    return true;}

        }
    return false;
    }
    private boolean createAccount() {
        boolean checkList = true;
        IO.println("Welcome to the bank creating account system may i know your nameUser");
        String nameBank;
        boolean namePass;
        do {
            nameBank = input.nextLine();
            namePass = this.checkUserInput(nameBank);
            if (!namePass) {
                IO.println("Name too short try again");
                IO.println("What is your name to be...");
            }

        }while (!namePass);

        IO.println("What is the password to be " + nameBank + "?");
           String passwordBank;
           boolean bankPass;
           do {
                passwordBank = input.nextLine();
                bankPass = this.checkPassword(passwordBank);
                if (!bankPass){
                    IO.println("Password too short try again");
                    IO.println("What is your password to be...");
                   }
            }while (!bankPass);

            if (this.bankAccounts.isEmpty()){checkList = false;}

            if (checkList) {
                    for(BankAccount bankAccount : this.bankAccounts) {
                        if (bankAccount.getName().equals(nameBank)) {
                            IO.println("Name already taken try another or log in");
                            return false;
                        }
                    }
                }

                boolean code = false;
                String codeIn ="";
                do {
                    IO.println("Set a transferece code like 567/233");
                    codeIn = input.nextLine();
                    if(!codeIn.contains("/")){IO.println("Please set a real code");continue;}
                    if(codeIn.contains(" ")){IO.println("Please follow the format not spaces");continue;}
                    code = true;
                }while (!code);


                this.password = passwordBank;
                this.name = nameBank;
                ++accountID;
                this.ID = accountID;
                this.balance = 2002;
                this.bankAccounts.add(new BankAccount(this.name, this.password, this.balance,codeIn));
                IO.println("Your account balance has been opened with 200$ in it if you want more you can deposit any time");
                return true;


    }
    private BankAccount getAccess() {
        BankAccount accountIn = this.logIn();
        return accountIn == null ? null : accountIn;
    }
    private BankAccount logIn() {
        IO.println("Tell me your name yo log in");
        String name = input.nextLine();
        IO.println("What is the password to be " + name + "?");
        String passwordIn = input.nextLine();

        for(int i = 0; i < this.bankAccounts.size(); ++i) {
            if (((BankAccount)this.bankAccounts.get(i)).getName().equals(name) && ((BankAccount)this.bankAccounts.get(i)).getPassword().equals(passwordIn)) {
                return (BankAccount)this.bankAccounts.get(i);
            }
        }

        IO.println("Password or user name invalid");
        return null;
    }
    private boolean checkUserInput(String input) {
        String character = "1234567890@#$%¨&*():>.,";
        if (input.length() < 6) {
            return false;
        } else {
            for(int i = 0; i < input.length(); ++i) {
                for(int j = 0; j < character.length(); ++j) {
                    if (input.charAt(i) == character.charAt(j)) {
                        return true;
                    }
                }
            }

            return true;
        }
    }
    private boolean checkPassword(String password) {
        boolean pass = false;
        String character = "1234567890@#$%¨&*():>.,";
        String characterUpper = "QWERTYUIOPASDFGHJKLZXCVBNM";
        if (password.length() < 6) {
            return false;
        } else {
            for(int i = 0; i < password.length(); ++i) {
                for(int j = 0; j < character.length(); ++j) {
                    if (password.charAt(i) == character.charAt(j)) {
                        pass = true;
                    }
                }
            }

            if (pass) {
                for(int i = 0; i < password.length(); ++i) {
                    for(int j = 0; j < character.length(); ++j) {
                        if (password.charAt(i) == characterUpper.charAt(j)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }
    public void seeHistory(){
    BankAccount account = logIn();
    if(account == null){IO.println("Unable to see login invalid");return;}
    for(Register regis : account.getRegister_list()){
    IO.println(regis.getRegister_Input());
    }
    }
    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}

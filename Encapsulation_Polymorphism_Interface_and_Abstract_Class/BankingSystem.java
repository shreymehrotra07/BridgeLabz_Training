import java.util.*;

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

abstract class BankAccount implements Loanable {
    private String accountNumber, holderName;
    private double balance;
    
    public BankAccount(String acc,String name,double bal){
        this.accountNumber=acc; this.holderName=name; this.balance=bal;
    }
    public String getAccountNumber(){ return accountNumber; }
    public String getHolderName(){ return holderName; }
    public double getBalance(){ return balance; }
    protected void setBalance(double bal){ this.balance=bal; } // encapsulated

    public void deposit(double amount){ balance+=amount; }
    public void withdraw(double amount){ if(balance>=amount) balance-=amount; else System.out.println("Insufficient balance"); }
    
    public abstract double calculateInterest();
    
    public void displayDetails(){
        System.out.println(holderName+" ("+accountNumber+") | Balance:"+balance+" | Interest:"+calculateInterest());
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(String acc,String name,double bal){ super(acc,name,bal); }
    public double calculateInterest(){ return getBalance()*0.04; } // 4% interest
    public void applyForLoan(double amt){ System.out.println("Savings loan applied: "+amt); }
    public boolean calculateLoanEligibility(){ return getBalance()>=5000; }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(String acc,String name,double bal){ super(acc,name,bal); }
    public double calculateInterest(){ return getBalance()*0.02; } // 2% interest
    public void applyForLoan(double amt){ System.out.println("Current loan applied: "+amt); }
    public boolean calculateLoanEligibility(){ return getBalance()>=10000; }
}

public class BankingSystem {
    public static void main(String[] args){
        List<BankAccount> accounts=Arrays.asList(
            new SavingsAccount("SA101","Alice",20000),
            new CurrentAccount("CA202","Bob",50000)
        );

        for(BankAccount acc:accounts){
            acc.displayDetails();
            acc.deposit(2000);
            acc.withdraw(3000);
            acc.applyForLoan(10000);
            System.out.println("Loan Eligible: "+acc.calculateLoanEligibility());
            System.out.println("---------------------------");
        }
    }
}

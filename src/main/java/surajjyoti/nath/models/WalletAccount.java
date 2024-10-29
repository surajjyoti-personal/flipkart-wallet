package surajjyoti.nath.models;

public class WalletAccount {
    private final String id;
    private double balance;
    public WalletAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public String toString() {
        return "WalletAccount{" +
                "id=" + id +
                ", balance=" + balance +
                "}";
    }
}

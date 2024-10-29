package surajjyoti.nath.models;

public class User {
    private String name;
    private WalletAccount account;
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WalletAccount getAccount() {
        return account;
    }

    public void setAccount(WalletAccount account) {
        this.account = account;
    }

    public String toString() {
        return "User{" +
                "name=" + name +
                ", account=" + account +
                "}";
    }

}

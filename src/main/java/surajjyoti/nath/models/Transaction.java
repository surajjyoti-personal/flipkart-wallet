package surajjyoti.nath.models;

import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final String payerName;
    private final String payeeName;
    private final double amount;
    private final LocalDateTime timestamp;
    public Transaction(String id, String payerName, String payeeName, double amount) {
        this.id = id;
        this.payerName = payerName;
        this.payeeName = payeeName;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getPayerName() {
        return payerName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", payerName=" + payerName +
                ", payeeName=" + payeeName +
                ", amount=" + amount +
                ", timestamp=" + timestamp.toString() +
                "}";
    }
}

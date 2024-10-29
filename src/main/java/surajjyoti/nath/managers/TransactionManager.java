package surajjyoti.nath.managers;

import surajjyoti.nath.exceptions.EntityAlreadyExistsException;
import surajjyoti.nath.models.Transaction;

import java.time.LocalDateTime;
import java.util.*;

public class TransactionManager {
    private static TransactionManager instance;
    private final Map<String, Transaction> transactions;
    private TransactionManager() {
        transactions = new HashMap<>();
    }
    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public Transaction createTransaction(Transaction transaction) throws EntityAlreadyExistsException {
        if (transactions.containsKey(transaction.getId())) {
            throw new EntityAlreadyExistsException("transactionId: " + transaction.getId() + " already exists");
        }
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    public List<Transaction> getTransactions(String userName) {
        return transactions.values()
                .stream()
                .filter(transaction -> userName.equals(transaction.getPayeeName()) || userName.equals(transaction.getPayerName()))
                .toList();
    }

    public List<Transaction> getTransactionsFilterByAmount(String userName) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getPayeeName().equals(userName) || transaction.getPayerName().equals(userName)) {
                transactionList.add(transaction);
            }
        }
        transactionList.sort((Transaction a, Transaction b) -> Double.compare(a.getAmount(), b.getAmount()));
        return transactionList;
    }

    public List<Transaction> getTransactionsFilterByDate(String userName, LocalDateTime startDate, LocalDateTime endDate) {
        // without sorting on date
//        return transactions.values()
//                .stream()
//                .filter(transaction -> (transaction.getPayeeName().equals(userName) || transaction.getPayerName().equals(userName))
//                    && transaction.getTimestamp().isAfter(startDate) && transaction.getTimestamp().isBefore(endDate))
//                .toList();

        // with sorting on date as filtering returns a immutable list
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if ((transaction.getPayeeName().equals(userName) || transaction.getPayerName().equals(userName))
                    && transaction.getTimestamp().isAfter(startDate) && transaction.getTimestamp().isBefore(endDate)) {
                transactionList.add(transaction);
            }
        }
        transactionList.sort((Transaction a, Transaction b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        return transactionList;
    }

}

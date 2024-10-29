package surajjyoti.nath.services;

import surajjyoti.nath.exceptions.EntityAlreadyExistsException;
import surajjyoti.nath.exceptions.EntityDoesNotExistException;
import surajjyoti.nath.exceptions.InsufficientBalanceException;
import surajjyoti.nath.exceptions.InvalidAmountException;
import surajjyoti.nath.managers.TransactionManager;
import surajjyoti.nath.managers.UserManager;
import surajjyoti.nath.models.Transaction;
import surajjyoti.nath.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private final TransactionManager transactionManager;
    private final UserManager userManager;
    public TransactionService(TransactionManager transactionManager, UserManager userManager) {
        this.transactionManager = transactionManager;
        this.userManager = userManager;
    }

    public Transaction createTransaction(String payerName, String payeeName, double amount) throws EntityDoesNotExistException, InvalidAmountException, InsufficientBalanceException, EntityAlreadyExistsException {
        if (amount <= 0)
            throw new InvalidAmountException("Amount should be greater than 0: " + amount);
        User payer = userManager.getUserByName(payerName);
        User payee = userManager.getUserByName(payeeName);
        if (payer.getAccount().getBalance() < amount)
            throw new InsufficientBalanceException("Not enough balance in payer account");
        Transaction transaction = new Transaction(UUID.randomUUID().toString().substring(0,8).toUpperCase(), payerName, payeeName, amount);
        transaction = transactionManager.createTransaction(transaction);
        payer.getAccount().withdraw(amount);
        payee.getAccount().deposit(amount);
        return transaction;
    }

    public List<Transaction> getAllTransactionByUserName(String username) {
        return transactionManager.getTransactions(username);
    }

    public List<Transaction> getAllTransactionByUserNameFilterByAmount(String username) {
        return transactionManager.getTransactionsFilterByAmount(username);
    }

    public List<Transaction> getAllTransactionByUserNameAndDateRange(String username, LocalDateTime from, LocalDateTime to) {
        return transactionManager.getTransactionsFilterByDate(username, from, to);
    }
}

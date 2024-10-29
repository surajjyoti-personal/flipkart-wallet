package surajjyoti.nath;

import surajjyoti.nath.exceptions.*;
import surajjyoti.nath.managers.TransactionManager;
import surajjyoti.nath.managers.UserManager;
import surajjyoti.nath.models.LoadMoneyMode;
import surajjyoti.nath.models.Transaction;
import surajjyoti.nath.models.User;
import surajjyoti.nath.services.TransactionService;
import surajjyoti.nath.services.UserService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws EntityAlreadyExistsException, InvalidLoadMoneyModeException, EntityDoesNotExistException, InvalidAmountException, InsufficientBalanceException {
        UserManager userManager = UserManager.getInstance();
        UserService userService = new UserService(userManager);

        TransactionManager transactionManager = TransactionManager.getInstance();
        TransactionService transactionService = new TransactionService(transactionManager, userManager);

        User userBob = userService.createUser("Bob");
        User userAlice = userService.createUser("Alice");
        userService.loadMoney("Bob", 1000, LoadMoneyMode.UPI);
        System.out.println(userBob);

        System.out.println("--------");
        Transaction transaction = transactionService.createTransaction("Bob", "Alice", 250);
        Transaction transaction2 = transactionService.createTransaction("Bob", "Alice", 350);
        Transaction transaction3 = transactionService.createTransaction("Bob", "Alice", 100);
        System.out.println(transaction);
        System.out.println(userBob);
        System.out.println(userAlice);

        System.out.println("--------");
        System.out.println(transactionService.getAllTransactionByUserName("Bob"));

        System.out.println("--------");
        System.out.println(transactionService.getAllTransactionByUserNameFilterByAmount("Bob"));

        System.out.println("--------");
        System.out.println(transactionService.getAllTransactionByUserNameAndDateRange("Bob", LocalDateTime.of(2024, 9, 10, 10, 30), LocalDateTime.of(2024, 10, 30, 10, 30)));

    }
}
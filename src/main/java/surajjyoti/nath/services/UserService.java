package surajjyoti.nath.services;

import surajjyoti.nath.exceptions.EntityAlreadyExistsException;
import surajjyoti.nath.exceptions.EntityDoesNotExistException;
import surajjyoti.nath.exceptions.InvalidAmountException;
import surajjyoti.nath.exceptions.InvalidLoadMoneyModeException;
import surajjyoti.nath.managers.UserManager;
import surajjyoti.nath.models.LoadMoneyMode;
import surajjyoti.nath.models.User;
import surajjyoti.nath.models.WalletAccount;
import surajjyoti.nath.strategies.LoadMoneyStrategy;
import surajjyoti.nath.strategies.StrategyManager;

import java.util.UUID;

public class UserService {
    private final UserManager userManager;
    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public User createUser(String name) throws EntityAlreadyExistsException {
        User user = new User(name);
        WalletAccount account = new WalletAccount(UUID.randomUUID().toString().substring(0, 8).toUpperCase(), 0);
        user.setAccount(account);
        return userManager.creteUser(user);
    }

    public void loadMoney(String name, double amount, LoadMoneyMode mode) throws EntityDoesNotExistException, InvalidLoadMoneyModeException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Amount should be greater than 0: " + amount);
        User user = userManager.getUserByName(name);
        LoadMoneyStrategy loadMoneyStrategy = StrategyManager.getLoadMoneyStrategy(mode);
        loadMoneyStrategy.processPayment(user, amount);
    }
}

package surajjyoti.nath.strategies;

import surajjyoti.nath.models.User;

public interface LoadMoneyStrategy {
    public void processPayment(User user, double amount);
}

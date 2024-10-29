package surajjyoti.nath.strategies;

import surajjyoti.nath.models.User;

public class CreditCardStrategy implements LoadMoneyStrategy{
    @Override
    public void processPayment(User user, double amount) {
        user.getAccount().deposit(amount);
        System.out.println("Loaded amount: " + amount + " through credit card");
    }
}

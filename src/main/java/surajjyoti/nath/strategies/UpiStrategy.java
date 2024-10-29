package surajjyoti.nath.strategies;

import surajjyoti.nath.models.User;

public class UpiStrategy implements LoadMoneyStrategy{
    @Override
    public void processPayment(User user,double amount) {
        user.getAccount().deposit(amount);
        System.out.println("Loaded amount: " + amount + " through UPI");
    }
}

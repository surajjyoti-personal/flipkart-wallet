package surajjyoti.nath.strategies;

import surajjyoti.nath.exceptions.InvalidLoadMoneyModeException;
import surajjyoti.nath.models.LoadMoneyMode;

public class StrategyManager {
    public static LoadMoneyStrategy getLoadMoneyStrategy(LoadMoneyMode mode) throws InvalidLoadMoneyModeException {
        switch (mode) {
            case UPI -> {
                return new UpiStrategy();
            }
            case CREDIT_CARD -> {
                return new CreditCardStrategy();
            }
            default -> {
               throw  new InvalidLoadMoneyModeException("Invalid load money mode: " + mode);
            }
        }

    }
}

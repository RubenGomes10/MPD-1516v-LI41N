package collectors;

import transactions.Transaction;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lfalcao on 16/05/16.
 */
public class CollectorsTests {
    public static List<Transaction> transactions = Arrays.asList(
            new Transaction(2009, 1500, Transaction.Currency.EUR),
            new Transaction(2010, 2300, Transaction.Currency.USD),
            new Transaction(2011, 9900, Transaction.Currency.GBP),
            new Transaction(2014, 1100, Transaction.Currency.EUR),
            new Transaction(2011, 7800, Transaction.Currency.JPY),
            new Transaction(2010, 6700, Transaction.Currency.CHF),
            new Transaction(2012, 5600, Transaction.Currency.EUR),
            new Transaction(2010, 4500, Transaction.Currency.USD),
            new Transaction(2011, 3400, Transaction.Currency.CHF),
            new Transaction(2010, 3200, Transaction.Currency.GBP),
            new Transaction(2009, 4600, Transaction.Currency.USD),
            new Transaction(2011, 5700, Transaction.Currency.JPY),
            new Transaction(2012, 6800, Transaction.Currency.EUR) );
}

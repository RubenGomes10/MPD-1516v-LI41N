package collectors;

import org.junit.Test;
import transactions.Transaction;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Created by lfalcao on 13/05/16.
 */
public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Transaction.Currency.EUR, 1500),
            new Transaction(Transaction.Currency.USD, 2300),
            new Transaction(Transaction.Currency.GBP, 9900),
            new Transaction(Transaction.Currency.EUR, 1100),
            new Transaction(Transaction.Currency.JPY, 7800),
            new Transaction(Transaction.Currency.CHF, 6700),
            new Transaction(Transaction.Currency.EUR, 5600),
            new Transaction(Transaction.Currency.USD, 4500),
            new Transaction(Transaction.Currency.CHF, 3400),
            new Transaction(Transaction.Currency.GBP, 3200),
            new Transaction(Transaction.Currency.USD, 4600),
            new Transaction(Transaction.Currency.JPY, 5700),
            new Transaction(Transaction.Currency.EUR, 6800) );


    @Test
    public void groupingTotalTransactionsByCurrency() {
        HashMap<Transaction.Currency, Integer> sumByCurrency = new HashMap<>();

        for (Transaction t: transactions) {
            Integer value = sumByCurrency.get(t.getCurrency());
            if(value == null) {
                value = 0;
            }
            sumByCurrency.put(t.getCurrency(), t.getValue()+value.intValue());
        }
    }

    @Test
    public void groupingTransactionsByCurrency() {
        HashMap<Transaction.Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();

        for (Transaction t: transactions) {
            List<Transaction> currencyTransactions = transactionsByCurrency.get(t.getCurrency());
            if(currencyTransactions == null) {
                currencyTransactions = new ArrayList<>();
                transactionsByCurrency.put(t.getCurrency(), currencyTransactions);

            }
            currencyTransactions.add(t);
        }

        transactionsByCurrency.forEach((k, v) -> System.out.printf("%s: \n %s\n\n", k, v.toString()));
    }

    @Test
    public void groupingTransactionsByCurrencyUsingCollectors() {
        transactions
                .stream()
                .collect(groupingBy(Transaction::getCurrency))
                .forEach((k, v) -> System.out.printf("%s: \n %s\n\n", k, v.toString()));

    }

    @Test
    public void usingSeveralCollectorsFromCollectrosClass() {

        // Obtain the maximumm value transaction
        Optional<Transaction> maxTransaction = transactions
                .stream().collect(maxBy(comparing(Transaction::getValue)));
        maxTransaction
                .ifPresent(t -> System.out.println(t.getValue()));

        ;


        // Sum all transaction values
        System.out.println(transactions.stream().collect(summingInt(Transaction::getValue)));

        // Summarizing all transactions
        System.out.println(transactions.stream().collect(summarizingInt(Transaction::getValue)));

        // Joining all transaction currencies in a string
        System.out.println(transactions.stream()
                .map(t -> t.getCurrency().toString())
                //.filter(s -> s.length() > 10000)
                .collect(joining(",", "[", "]")));

        // Joining all transaction currencies in a string, using reducing Collector
        System.out.println(transactions.stream()
                .filter(t -> t.getValue() > 9000)
                .map(t -> t.getCurrency().toString())
                .collect(reducing("",(prev, curr) -> prev + " " + curr)));

        // Sum all transaction values using reducing returned Collector
        System.out.println(transactions.stream()
                .filter(t -> t.getValue() > 5000)
                .collect(reducing(0, this::getTransactionValue, this::sumInts)));

        System.out.println("---------------------------------");
        System.out.println(transactions.stream()
                .filter(t -> t.getValue() > 5000)
                .reduce(0, this::accumulateTransactionValue, this::sumInts));


//        // Stream.reduce
//        <U> U reduce(U identity,
//                BiFunction<U, ? super T, U> accumulator,
//                BinaryOperator<U> combiner)
//
//        // Collectors.reducing
//        Collector<T, ?, U> reducing(U identity,
//                Function<? super T, ? extends U> mapper,
//                BinaryOperator<U> op)

    }

    @Test
    public void multiLevelGrouping() {
        System.out.println(transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                        groupingBy(t -> t.getValue() > 5000 ? TransactionValue.HIGH : TransactionValue.LOW)
        )).toString());

        System.out.println(transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                        summingInt(Transaction::getValue)
                )));
    }

    private Integer sumInts(Integer i1, Integer i2) {
        System.out.printf("sumInts: %d+%d\n", i1, i2);
        return Integer.sum(i1, i2);
    }

    private Integer getTransactionValue(Transaction t) {
        System.out.println("getTransactionValue: " + t);
        return t.getValue();
    }

    private Integer accumulateTransactionValue(Integer prev, Transaction t) {
        System.out.printf("accumulateTransactionValue: prev: %d - TransactionValue: %d\n", prev, t.getValue());
        return Integer.sum(prev, t.getValue());
    }

    enum TransactionValue {
        LOW, HIGH
    }
}

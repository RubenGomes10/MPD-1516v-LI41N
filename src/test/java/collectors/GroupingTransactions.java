package collectors;

import org.junit.Test;
import transactions.Transaction;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Created by lfalcao on 13/05/16.
 */
public class GroupingTransactions extends CollectorsTests {

    public static final int LOWER_TRANSACTIONS_LIMIT = 5000;


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
        System.out.println(transactions.parallelStream()
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

        // Transactions by currency type and TransactionClassifier.
        Map<Transaction.Currency, Map<TransactionClassifier, List<Transaction>>> map = transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                        groupingBy(t -> t.getValue() > 5000 ? TransactionClassifier.HIGH : TransactionClassifier.LOW)
                ));
        System.out.println(map.toString());


        // Sum of transaction values by currency type
        Map<Transaction.Currency, Integer> sumByCurrency = transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                        summingInt(Transaction::getValue)
                ));
        System.out.println();

        // Sum of transaction values by currency type, TransactionClassifier and by year
        Map<Transaction.Currency, Map<TransactionClassifier, Map<Integer, Integer>>> transactionsByCurrencyClassifierAndYear =
                transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                        groupingBy(
                                this::getTransactionValueClassifier,
                                groupingBy(
                                        Transaction::getYear,
                                        summingInt(Transaction::getValue)
                                ))

                )

                );
        System.out.println(transactionsByCurrencyClassifierAndYear.toString());
    }

    @Test
    public void multiLevelGroupingCollectAndThen() {

        // Transactions by currency type and TransactionClassifier.
        Map<Transaction.Currency, Integer> collect = transactions.stream().collect(
                groupingBy(
                        Transaction::getCurrency,
                            collectingAndThen(
                                collectingAndThen(
                                    maxBy(comparing(Transaction::getValue)),
                                    Optional::get
                                ), Transaction::getValue)
                ));
        System.out.println();
    }

    @Test
    public void partitioningByUsage() {

        // Transactions by currency type and TransactionClassifier.
        System.out.println(
            transactions.stream().collect(
                partitioningBy(
                        t -> t.getValue() > LOWER_TRANSACTIONS_LIMIT
                        ,mapping(Transaction::getValue, toList())
                )
            )
        );

    }

    private TransactionClassifier getTransactionValueClassifier(Transaction t) {
        return t.getValue() > LOWER_TRANSACTIONS_LIMIT ? TransactionClassifier.HIGH : TransactionClassifier.LOW;
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
        System.out.printf("accumulateTransactionValue: prev: %d - TransactionClassifier: %d\n", prev, t.getValue());
        return Integer.sum(prev, t.getValue());
    }

    enum TransactionClassifier {
        LOW, HIGH
    }
}

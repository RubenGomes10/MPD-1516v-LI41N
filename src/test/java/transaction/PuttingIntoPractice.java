package transaction;

import transactions.Trader;
import transactions.Transaction;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );	
        
        
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        transactions.stream()
                .filter(t -> t.getYear() > 2011)
                .sorted(comparing(t -> t.getValue()))
                .forEach(System.out::println);

        // Query 2: What are all the unique cities where the traders work?
        transactions.stream()
                .map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);

        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(t -> t.getTrader())
                .filter(trader ->  trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(tr -> tr.getName()))
                .forEach(System.out::println);




        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String names = transactions.stream()
            .map(t -> t.getTrader())
            .distinct()
            .sorted(comparing(tr -> tr.getName()))
            .reduce("", (str, trader) -> str + (str.equals("") ? "" : ", ") + trader.getName(), (s1, s2) -> s1 + s2);

        System.out.println(names);

//        // Query 5: Are there any trader based in Milan?

        boolean trandersInMilan = transactions.stream()
                .map(t -> t.getTrader()
                .getCity())
                .anyMatch(city -> city.equals("Lisboa"));
        System.out.println(trandersInMilan);





//
//        boolean milanBased =
//            transactions.stream()
//                        .anyMatch(transaction -> transaction.getTrader()
//                                                            .getCity()
//                                                            .equals("Milan")
//                                 );
//        System.out.println(milanBased);
//
//
//        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
//        transactions.stream()
//                    .map(Transaction::getTrader)
//                    .filter(trader -> trader.getCity().equals("Milan"))
//                    .forEach(trader -> trader.setCity("Cambridge"));
//        System.out.println(transactions);
//
//
//        // Query 7: What's the highest value in all the transactions?
//        int highestValue =
//            transactions.stream()
//                        .map(Transaction::getValue)
//                        .reduce(0, Integer::max);
//        System.out.println(highestValue);


        // Bonus queries from class!!!

        // get the sum of the values for all transactions

        System.out.println(transactions.stream().mapToInt(t -> t.getValue()).sum());
        System.out.println(transactions.stream()
                .map(Transaction::getValue).reduce(0, Integer::sum));


        // get the average value for all transactions
        System.out.println(transactions.stream().mapToInt(t -> t.getValue()).average().getAsDouble());
        int[] reduceArray = transactions.stream()
                .map(Transaction::getValue).reduce(new int[]{0, 0}, (a, value) -> new int[]{a[0] + value, a[1] + 1}, (a1, a2) -> new int[]{a1[0] + a2[0], a1[1] + a2[1]});
        System.out.println(Arrays.toString(reduceArray));

        OptionalDouble average = reduceArray[1] == 0 ? OptionalDouble.empty() : OptionalDouble.of((double)reduceArray[0] / reduceArray[1]);
        System.out.println(average.getAsDouble());
    }
}
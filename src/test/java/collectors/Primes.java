package collectors;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by lfalcao on 20/05/16.
 */
public class Primes {

    static boolean isPrime(int candidate) {
        //int limit = (int)Math.sqrt(candidate);
        return IntStream
                .range(2, candidate)
                .noneMatch(n -> candidate % n == 0);

    }

    static boolean isPrimeOpt1(int candidate) {
        int limit = (int) Math.sqrt(candidate);
        return IntStream
                .rangeClosed(2, limit)
                .noneMatch(n -> candidate % n == 0);

    }

    static boolean isPrimeOpt2(List<Integer> knownPrimes, int candidate) {
        int limit = (int) Math.sqrt(candidate);
        boolean isPrime =
                //takeWhile(knownPrimes, limit)
                takeWhile(knownPrimes, (i) -> i < limit)
                .stream()
                .noneMatch(p -> candidate % p == 0);

        if(isPrime) {
            knownPrimes.add(candidate);
        }
        return isPrime;
    }

//    private static List<Integer> takeWhile(List<Integer> knownPrimes, int limit) {
//        int i;
//        for (i = 0; i < knownPrimes.size() && knownPrimes.get(i) < limit; ++i);
//        return knownPrimes.subList(0, i);
//    }

    private static <T> List<T> takeWhile(List<T> elements, Predicate<T> condition) {
        int i;
        for (i = 0; i < elements.size() && condition.test(elements.get(i)); ++i);
        return elements.subList(0, i);
    }

    List<Integer> primes(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(Primes::isPrime)
                .boxed()
                .collect(toList());
    }

    List<Integer> primesOpt1(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(Primes::isPrimeOpt1)
                .boxed()
                .collect(toList());
    }



    List<Integer> primesOpt2(int n) {
        List<Integer> primes = new ArrayList<>();
        return IntStream.rangeClosed(2, n)
                .filter(num -> isPrimeOpt2(primes ,num))
                .collect(ArrayList::new, List::add, List::addAll);
    }


    private static <T> T measurePerformance(Supplier<T> action) {
        long fastest = Long.MAX_VALUE;
        T res = null;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            res = action.get();
            long duration = (System.nanoTime() - start) / 1_000_000; // mili seconds
            //System.out.println( "> " + duration + " ms");
            if (duration < fastest) fastest = duration;
        }
        System.out.println("DONE in: " + fastest + " ms");
        return res;
    }

    @Test
    public void testPrimesPerformance() {
        //final int LIMIT = 1_000_000;
        final int LIMIT = 1_000_000;

        //measurePerformance(() -> primes(LIMIT));
        for (int i = 0; i < 5; i++) {
            measurePerformance(() -> primesOpt1(LIMIT));
            measurePerformance(() -> primesOpt2(LIMIT));
        }
    }
}

/**
    DONE in: 273 ms
        DONE in: 708 ms
        DONE in: 254 ms
        DONE in: 695 ms
        DONE in: 254 ms
        DONE in: 668 ms
        DONE in: 260 ms
        DONE in: 697 ms
        DONE in: 256 ms
        DONE in: 673 ms

 */
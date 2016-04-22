package composablecomparators;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by lfalcao on 15/04/16.
 */
public class CmpComposableUtils {
    public <T> Comparator<T> reverse(Comparator<T> comparator) {
        return (t1, t2) -> comparator.compare(t2,t1);
    }

    public static <T, U extends Comparable<U>> Comparator<T> createComparator(Function<? super T, U> keyExtractor) {
        return (T t1, T t2) -> keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2));
    }


    public static <T, U extends Comparable<U>> Comparator<T> inverted(Function<T, U> keyExtractor) {
        return (T t1, T t2) -> keyExtractor.apply(t2).compareTo(keyExtractor.apply(t1));
    }

    public static <T> Comparator<T> inverted(Comparator<T> cmp) {
        return (T t1, T t2) -> cmp.compare(t2, t1);
    }

    public static <T> Comparator<T> andThen(Comparator<T> comparator1, Comparator<T> comparator2) {
        return (t1, t2) -> {
            int res = comparator1.compare(t1, t2);
            return res != 0 ? res : comparator2.compare(t1, t2);
        };
    }

    public static <T, U extends Comparable<U>> Comparator<T> andThen(Comparator<T> cmp, Function<T, U> keyExtractor) {
        return andThen(cmp, createComparator(keyExtractor));
    }

}

package composablecomparators;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by lfalcao on 15/04/16.
 */
public class CmpComposableC<T> implements Comparator<T> {


    public static <T, U extends Comparable<U>> CmpComposableC<T> createComparatorV2(Function<T, U> keyExtractor) {
        return new CmpComposableC<>(
                (T t1, T t2) -> keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2))
        );
    }

    final Comparator<T> c;

    public CmpComposableC(Comparator<T> c) {
        this.c = c;
    }


    @Override
    public int compare(T o1, T o2) {
        return c.compare(o1, o2);
    }


    public <R extends Comparable<R>> CmpComposableC<T> andThen(Function<T, R> keyExtractor) {
        return new CmpComposableC<>(
                (item1, item2) -> {
                    int res = this.c.compare(item1, item2);
                    final Comparator<T> thenComparator = createComparatorV2(keyExtractor);
                    return res != 0 ? res : thenComparator.compare(item1, item2);
                }
        );
    }

    public <R extends Comparable<R>> CmpComposableC<T>  inverted() {
            return new CmpComposableC<>(
                    (item1, item2) -> this.compare(item2, item1)
            );

        // DONT'T EVER DO THE FOLLOWING!!! Never change the already returned objects returned from previous methods!
//        final Comparator<T> prev = c;
//
//        this.c = (item1, item2) -> prev.compare(item2, item1);
//        return this;

    }
}

package composablecomparators;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by lfalcao on 15/04/16.
 */
public interface CmpComposableI<T> extends Comparator<T> {


    static <T, U extends Comparable<U>> CmpComposableI<T> createComparatorV3(Function<T, U> keyExtractor) {
        return  (T t1, T t2) -> keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2));

    }


    default CmpComposableI<T> inverted() {
        return (item1, item2) -> this.compare(item2, item1);
    }

    default  <R extends Comparable<R>> CmpComposableI<T> andThen(Function<T, R> keyExtractor) {
        return (item1, item2) -> {
            int res = this.compare(item1, item2);
            final Comparator<T> thenComparator = createComparatorV3(keyExtractor);
            return res != 0 ? res : thenComparator.compare(item1, item2);
        };
    }
}

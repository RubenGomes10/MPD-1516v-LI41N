package query.functional;


import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;


@FunctionalInterface
public interface Queryable<T> extends Nonspliterator<T> {

    static <T> Queryable<T> of(Collection<T> data) {
        Iterator<T> iter = data.iterator();
        return consumer -> {
            boolean hasNext = iter.hasNext();
            if(hasNext) {
                consumer.accept(iter.next());
            }
            return hasNext;
        };
    }

    default void forEach(Consumer<? super T> consumer) {
        while (tryAdvance(consumer));
    }

    default <R> Queryable<R> map(Function<T, R> mapper) {
        return consumer -> tryAdvance(t -> consumer.accept(mapper.apply(t)));
    }

    default Queryable<T> limit(long maxSize) {
        long[] count = {maxSize};
        return consumer -> count[0] > 0 && --count[0] >=0  ? tryAdvance(consumer) : false;
    }

    default Queryable<T> distinct() {
        final Set<? super T> distincts = new HashSet<>();
        return consumer -> tryAdvanceWithPredicate(consumer, t -> distincts.add(t));
    }

    default Queryable<T> filter(Predicate<T> pred) {
        return consumer -> tryAdvanceWithPredicate(consumer, pred);
    }

    default T reduce(T initial, BinaryOperator<T> accumulator) {
        T []res = (T[])Array.newInstance(initial.getClass(), 1);
        res[0] = initial;
        forEach(t -> res[0] = accumulator.apply(res[0], t));
        return res[0];
    }

    default Queryable<T> peek(final Consumer<? super T> action) {
        return consumer ->
                tryAdvance(t -> {
                    action.accept(t);
                    consumer.accept(t);
                });
    }

    default T[] toArray(IntFunction<T[]> generator) {
        List<T> resList = new ArrayList<>();
        forEach(t -> resList.add(t));
        return resList.toArray(generator.apply(resList.size()));
    }

    default boolean tryAdvanceWithPredicate(Consumer<? super T> consumer, Predicate<T> condition) {
        boolean []found = {false};
        while(!found[0] && tryAdvance(t -> {

            if(condition.test(t)) {
                consumer.accept(t);
                found[0] = true;
            }
        }));
        return found[0];
    }
}

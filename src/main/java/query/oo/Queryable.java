package query.oo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.*;


//@FunctionalInterface
public abstract class Queryable<T> implements Spliterable<T> {

    public static <T> Queryable<T> of(Collection<T> data) {
        return new CollectionQueryable(data);
    }

    public void forEach(Consumer<? super T> consumer) {
        while (tryAdvance(consumer));
    }

    public <R> Queryable<R> map(Function<T, R> mapper) {
        return new MapQueryable(mapper, this);
    }

    public <R> Queryable<R> flatMap(Function<T, Queryable<R>> mapper) {
        return new FlatMapQueryable(mapper, this);
    }



    public Queryable<T> limit(long maxSize) {
        return new LimitQueryable(maxSize, this);
    }

    public Queryable<T> distinct() {
        return new DistinctQueryable(this);
    }

    public Queryable<T> filter(Predicate<T> p) {
        return new FilterQueryable(p, this);

    }

    public T reduce(T initial, BinaryOperator<T> accumulator) {
        T []res = (T[])Array.newInstance(initial.getClass(), 1);
        res[0] = initial;
        forEach(t -> res[0] = accumulator.apply(res[0], t));
        return res[0];
    }

    public Queryable<T> peek(final Consumer<? super T> action) {
        //return new PeekQueryable(action, this);
        final Queryable<T> nextQueryable = this;
        return new Queryable<T>() {
            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                return nextQueryable.tryAdvance(t -> {
                    action.accept(t);
                    consumer.accept(t);
                });
            }
        };
    }

    public T[] toArray(IntFunction<T[]> generator) {
        List<T> resList = new ArrayList<>();
        forEach(t -> resList.add(t));

        return resList.toArray(generator.apply(resList.size()));
    }

    protected static <T> boolean tryAdvanceWithPredicate(Consumer<? super T> consumer, Predicate<T> condition, Queryable<T> nextQueryable) {
        boolean []found = {false};
        while(!found[0] && nextQueryable.tryAdvance(t -> {

            if(condition.test(t)) {
                consumer.accept(t);
                found[0] = true;
            }
        }));
        return found[0];
    }


    public static <R> Queryable<R> empty() {
        return new EmptyQueryable();
    }


}

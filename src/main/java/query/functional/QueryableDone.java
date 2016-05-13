package query.functional;

/**
 * Created by lfalcao on 30/04/16.
 */

/*
 * Copyright (c) 2016, Miguel Gamboa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * <p>
 * {@code QueryableDone<T>} is a concise and functional implementation
 * of an equivalent API to the {@link java.util.stream.Stream}, which
 * preserves the internal iteration approach, the laziness behavior
 * and the fluent idiom.
 * This solution answers the question: How can I implement a lazy iterator
 * in Java 8?
 * </p>
 *
 * <p>
 * To achieve a short implementation, the {@code QueryableDone<T>} suppressed
 * the partitioning feature, which means that {@code QueryableDone<T>} does NOT
 * support parallel processing.
 * </p>
 *
 * <p>
 * The following example illustrates a use case of {@code QueryableDone<T>}
 * that is equivalent to the use of {@link java.util.stream.Stream}.
 * You can replace the {@code QueryableDone.of(dataSrc)} call with {@code
 * dataSrc.stream()} and you will get the same result.
 * </p>
 *
 * <pre>{@code
 *     Collection<String> dataSrc = ...  // something
 *     QueryableDone.of(dataSrc)             // <=> dataSrc.stream()
 *         .filter(w -> !w.startsWith("-"))
 *         .distinct()
 *         .map(String::length)
 *         .limit(5)
 *         .forEach(System.out::println);
 * }</pre>
 *
 * @author Miguel Gamboa
 *         created on 21-04-2016
 */
@FunctionalInterface
public interface QueryableDone<T> extends Nonspliterator<T> {

    static <T> boolean truth(Consumer<T> c, T item){
        c.accept(item);
        return true;
    }

    static <T> boolean next(QueryableDone<T> query, Predicate<T> pred, Consumer<? super T> action) {
        final boolean[] found = {false};
        while (!found[0] &&
                query.tryAdvance(e -> {
                    if(pred.test(e)) {
                        action.accept(e);
                        found[0] = true;
                    }
                }));
        return found[0];
    }

    public static <T> QueryableDone<T> of(Collection<T> data) {
        Iterator<T> iterator = data.iterator();
        return action -> iterator.hasNext() ? truth(action, iterator.next()) : false;
    }

    public default void forEach(Consumer<? super T> action) {
        while (tryAdvance(action));
    }

    public default <R> QueryableDone<R> map(Function<T, R> mapper) {
        return action -> tryAdvance(e -> action.accept(mapper.apply(e)));
    }

    public default QueryableDone<T> limit(long maxSize) {
        int[] count = {0};
        return action -> count[0]++ < maxSize ? tryAdvance(action) : false;
    }

    public default QueryableDone<T> distinct() {
        final Set<T> selected = new HashSet<>();
        return action -> next(this, selected::add, action);
    }

    public default QueryableDone<T> filter(Predicate<T> p) {
        return action -> next(this, p, action);
    }

    public default T reduce(T initial, BinaryOperator<T> accumulator) {
        T []acc = (T[]) Array.newInstance(initial.getClass(), 1);
        acc[0] = initial;
        forEach(e -> acc[0] = accumulator.apply(acc[0], e));
        return acc[0];
    }

    public default <A> A[] toArray(IntFunction<A[]> generator) {
        final List<T> coll = new ArrayList<>();
        forEach(e -> coll.add(e));
        return coll.toArray(generator.apply(coll.size()));
    }
}

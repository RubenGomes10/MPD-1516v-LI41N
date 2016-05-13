package query.functional;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by lfalcao on 30/04/16.
 */
@FunctionalInterface
public interface Nonspliterator<T> extends Spliterator<T> {

    @Override
    public abstract boolean tryAdvance(Consumer<? super T> action);

    @Override
    public default Spliterator<T> trySplit() {
        return null; // this spliterator cannot be split
    }

    @Override
    public default long estimateSize() {
        return Long.MAX_VALUE; // Long.MAX_VALUE means unknown, or too expensive to compute.
    }

    @Override
    public default int characteristics() {
        return 0; // No characteristics
    }
}

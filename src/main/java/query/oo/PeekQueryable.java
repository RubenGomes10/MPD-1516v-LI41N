package query.oo;

import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public class PeekQueryable<T> extends Queryable<T> {
    private Consumer<? super T> action;
    private Queryable<T> nextQueryable;

    public PeekQueryable(Consumer<? super T> action, Queryable<T> nextQueryable) {
        this.action = action;
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        return nextQueryable.tryAdvance(t -> {
            action.accept(t);
            consumer.accept(t);
        });
    }
}

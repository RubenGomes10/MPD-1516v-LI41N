package query.oo;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by lfalcao on 02/05/16.
 */
public class FilterQueryable<T> extends Queryable<T> {
    private Predicate<T> pred;
    private Queryable<T> nextQueryable;
    public FilterQueryable(Predicate<T> pred, Queryable<T> nextQueryable) {
        this.pred = pred;
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        boolean []found = {false};
        while(!found[0] && nextQueryable.tryAdvance(t -> {
            if(pred.test(t)) {
                consumer.accept(t);
                found[0] = true;
            }
        }));
        return found[0];
    }
}

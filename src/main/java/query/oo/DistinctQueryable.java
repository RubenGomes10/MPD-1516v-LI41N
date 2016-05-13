package query.oo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public class DistinctQueryable<T> extends Queryable<T> {
    private Queryable<T> nextQueryable;


    public DistinctQueryable(Queryable<T> nextQueryable) {
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        final Set<? super T> distincts = new HashSet<>();
        return Queryable.tryAdvanceWithPredicate(consumer, t -> distincts.add(t), nextQueryable);
    }
}

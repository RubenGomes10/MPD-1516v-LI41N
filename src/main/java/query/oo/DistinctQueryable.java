package query.oo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public class DistinctQueryable<T> extends Queryable<T> {
    private Queryable<T> nextQueryable;
    Set<? super T> distincts = new HashSet<>();


    public DistinctQueryable(Queryable<T> nextQueryable) {
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        boolean []found = {false};
        while(!found[0] && nextQueryable.tryAdvance(t -> {
           boolean res = distincts.add(t);
            if(res) {
                consumer.accept(t);
                found[0] = true;
            }
        }));

        return found[0];
    }
}

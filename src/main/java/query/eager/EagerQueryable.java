package query.eager;

import query.oo.FlatMapQueryable;
import query.oo.Queryable;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by lfalcao on 09/05/16.
 */
public class EagerQueryable<T> implements Iterable<T>{

    public <R> EagerQueryable<R> flatMap(Function<T, Queryable<R>> mapper) {
        return new FlatMapEagerQueryable(mapper, this);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

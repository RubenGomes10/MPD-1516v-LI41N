package query.eager;

import query.oo.Queryable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by lfalcao on 09/05/16.
 */
public class FlatMapEagerQueryable<T, R> extends EagerQueryable<R> {
    List<R> result = new ArrayList<R>();

    public FlatMapEagerQueryable(Function<T, EagerQueryable<R>> mapper, EagerQueryable<T> nextQueryable) {
        for (T t: nextQueryable) {
            EagerQueryable<R> mapeedRs = mapper.apply(t);
            for (R r : mapeedRs) {
                result.add(r);
            }
        }
    }
}

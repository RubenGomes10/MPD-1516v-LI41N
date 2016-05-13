package query.oo;

import java.util.function.Consumer;

/**
 * Created by lfalcao on 09/05/16.
 */
public class EmptyQueryable<T> extends Queryable<T> {
    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        return false;
    }
}

package query.oo;

import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public class LimitQueryable<T> extends Queryable<T> {
    private long maxSize;
    private Queryable<T> nextQueryable;

    public LimitQueryable(long maxSize, Queryable<T> nextQueryable) {

        this.maxSize = maxSize;
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        if(maxSize == 0) {
            return false;
        }
        --maxSize;
        return nextQueryable.tryAdvance(consumer);
    }
}

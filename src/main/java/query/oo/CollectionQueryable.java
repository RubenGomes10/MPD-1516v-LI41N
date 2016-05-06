package query.oo;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public class CollectionQueryable<T> extends Queryable<T> {


    private final Iterator<T> iterator;

    public CollectionQueryable(Collection<T> data) {
        iterator = data.iterator();

    }

    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {

        if(iterator.hasNext()) {
            consumer.accept(iterator.next());
            return true;
        }
        return false;
    }
}

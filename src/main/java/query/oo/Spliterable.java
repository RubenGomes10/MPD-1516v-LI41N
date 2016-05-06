package query.oo;

import java.util.function.Consumer;

/**
 * Created by lfalcao on 02/05/16.
 */
public interface Spliterable<T> {
    boolean tryAdvance(Consumer<? super T> consumer);

}

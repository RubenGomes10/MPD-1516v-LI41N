package query.oo;

import java.util.function.Consumer;
import java.util.function.Function;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lfalcao on 02/05/16.
 */
public class MapQueryable<T,R> extends Queryable<R> {
    private Function<T, R> mapper;
    private Queryable<T> nextQueryable;

    public MapQueryable(Function<T, R> mapper, Queryable<T> nextQueryable) {

        this.mapper = mapper;
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super R> consumer) {
        return nextQueryable.tryAdvance(t -> consumer.accept(mapper.apply(t)));
    }
}

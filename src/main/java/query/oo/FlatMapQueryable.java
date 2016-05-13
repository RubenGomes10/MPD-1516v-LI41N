package query.oo;

import java.util.function.Consumer;
import java.util.function.Function;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by lfalcao on 02/05/16.
 */
public class FlatMapQueryable<T,R> extends Queryable<R> {
    private Function<T, Queryable<R>> mapper;
    private Queryable<T> nextQueryable;
    private  Queryable<R> currentRQueryable = Queryable.empty();

    public FlatMapQueryable(Function<T, Queryable<R>> mapper, Queryable<T> nextQueryable) {

        this.mapper = mapper;
        this.nextQueryable = nextQueryable;
    }

    @Override
    public boolean tryAdvance(Consumer<? super R> consumer) {
        while(true) {
            boolean curentQueryableResult = currentRQueryable.tryAdvance(consumer);
            if(curentQueryableResult == true) {
                return true;
            }

            if(nextQueryable.tryAdvance(t -> currentRQueryable = mapper.apply(t)) == false) {
                return false;
            }
        }
    }
}

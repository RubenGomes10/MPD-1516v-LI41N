package weathergw.domain;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 18/04/16.
 */
public interface DecoratorCountSupplier<T> extends Supplier<T> {

    public static <T> DecoratorCountSupplier<T> decorateSupplier(Supplier<T> decoratedSup, IntConsumer countConsumer) {
        int []count = {0};
        return () -> {
            ++count[0];
            countConsumer.accept(count[0]);
            return decoratedSup.get();
        };
    }

    default public T get() {
        return getDecorated();
    }



    public T getDecorated();


}

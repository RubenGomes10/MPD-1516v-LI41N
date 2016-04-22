package weathergw;

import java.util.function.Supplier;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by lfalcao on 18/04/16.
 */
public class CountSupplier<T> implements Supplier<T> {
    private Supplier<T> sup;
    private int count = 0;



    public CountSupplier(Supplier<T> sup) {
        this.sup = sup;
    }


    @Override
    public T get() {
        ++count;
        return sup.get();
    }

    public int getCount() {
        return count;
    }
}

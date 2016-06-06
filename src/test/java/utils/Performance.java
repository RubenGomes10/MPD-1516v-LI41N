package utils;

import java.util.function.Supplier;

/**
 * Created by lfalcao on 03/06/16.
 */
public class Performance {
    public static <T> T measure(Supplier<T> action) {
        long fastest = Long.MAX_VALUE;
        T res = null;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            res = action.get();
            long duration = (System.nanoTime() - start) / 1_000_000; // mili seconds
            //System.out.println( "> " + duration + " ms");
            if (duration < fastest) fastest = duration;
        }
        System.out.println("DONE in: " + fastest + " ms");
        return res;
    }
}

package series.utils;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by lfalcao on 20/06/16.
 */
public class StreamUtils {


    public static <T> Stream<T> toStream(CompletableFuture<Stream<T>> cf) {
        return StreamSupport.stream(() -> cf.join().spliterator(), 0, false);
    }
}

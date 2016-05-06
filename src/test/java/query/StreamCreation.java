package query;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by lfalcao on 02/05/16.
 */
public class StreamCreation {
    @Test
    public void shouldCreateAStreamFromASetOfStrings() {
        Stream<String> mpd = Stream.of("MPD", "ISEL", "Modelação", "Padrões", "-ISEL", "-MPD", "MPD");

        mpd
            .filter(s -> !s.startsWith("-"))
                .peek(s -> System.out.println("filter"))
                .distinct()
                .peek(s -> System.out.println("distinct"))
                .forEach(System.out::println);
    }

    public void shouldCreateAnInfiniteStreamFromASupplier() {
        int []count = {0};
        Stream<Integer> naturalNumbers = Stream.generate(() ->  count[0]++);

        naturalNumbers.filter(n -> n % 2 == 0)
                .limit(200)
                .forEach(System.out::println);
    }

    @Test
    public void shouldCreateAnInfiniteStreamFromASeedAndAnUnaryOperator() {
        Stream<Integer> naturalNumbers = Stream.iterate(Integer.valueOf(0), (v) -> increment(v));


        Stream<Integer> sorted = naturalNumbers.filter(n -> n % 2 == 0)
                .limit(200)
                .sorted();
        //.forEach(System.out::println);

        while(sorted.spliterator().tryAdvance(System.out::println));
    }

    private Integer increment(Integer v) {
        int i = v.intValue();
        return ++i;
    }
}

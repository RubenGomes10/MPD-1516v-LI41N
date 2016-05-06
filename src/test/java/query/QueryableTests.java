package query;

import org.junit.Test;
import query.oo.Queryable;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 02/05/16.
 */
public class QueryableTests {

    @Test
    public void shouldUseSeveralOperatorsInQuery() {

        Queryable<String> queryable = Queryable.of(Stream.of("MPD", "ISEL", "Modelação", "Padrões", "-ISEL", "-MPD", "MPD", "PDM").collect(Collectors.toList()));
        queryable
                .peek(s -> System.out.println("source"))
                .filter(s -> !s.startsWith("-"))
                .peek(s -> System.out.println("filter"))
                .map(s-> s.length())
                .peek(s -> System.out.println("map"))
                .distinct()
                .peek(s -> System.out.println("distinct"))
                .forEach(System.out::println);


    }
}

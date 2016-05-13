package query;

import org.junit.Test;
import query.functional.Queryable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 02/05/16.
 */
public class QueryableTests {

    @Test
    public void shouldUseSeveralOperatorsInQueryFunctional() {

        query.functional.Queryable<String> queryable = query.functional.Queryable.of(Stream.of("MPD", "ISEL", "Modelação", "Padrões", "-ISEL", "-MPD", "MPD", "PDM").collect(Collectors.toList()));
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

    @Test
    public void shouldUseSeveralOperatorsInQueryObjectOriented() {

        query.oo.Queryable<String> queryable = query.oo.Queryable.of(Stream.of("MPD", "ISEL", "Modelação", "Padrões", "-ISEL", "-MPD", "MPD", "PDM").collect(Collectors.toList()));
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

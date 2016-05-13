package streams;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 09/05/16.
 */
public class StreamsTests {

    public static final int MAX = 50;

    @Test
    public void countDistinctCharsOfWords() {
        String []words = {"Sport", "Lisboa", "e", "Benfica"};


        Arrays.stream(words)
                .map(str -> str.split(""))          // Stream<String[]>
                .flatMap(Arrays::stream)            // Stream<String>
                .distinct()                         // Stream<String>
                .forEach(System.out::println);


        // Another solution using boxing on IntStream
        Arrays.stream(words)
                .flatMap(str -> str.chars().boxed())  // Stream<Integer>
                .distinct()
                .forEach(i -> System.out.println((char)i.intValue()));
    }

    @Test
    public void pitagoreanTriples() {
        IntStream.range(1, 10)
                .limit(5)
                .filter(i -> i%2 == 0)
                .forEach(System.out::println);

        Stream<double[]> pitagoreanNumbers =
                IntStream.range(1, MAX)
                .boxed()
                .flatMap(a ->
                        IntStream.range(a, MAX)
                        .boxed()
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .map(b -> new double[]{(double) a, (double) b, Math.sqrt(a * a + b * b)}));

        pitagoreanNumbers.forEach(a -> System.out.println(Arrays.toString(a)));
    }


    @Test
    public void fibonacciNumbers() {

        Stream
           .iterate(new int[]{0,1}, prev -> new int[]{prev[1], prev[0]+prev[1]})
           .map(a -> a[0])
                .limit(10)
        .forEach(System.out::println);

        // Alternative version

        Stream.concat(Stream.of(0),
                Stream
                        .iterate(new int[]{0,1}, prev -> new int[]{prev[1], prev[0]+prev[1]})
                        .map(a -> a[1]))
                .limit(10)
                .forEach(System.out::println);


    }


    @Test
    public void countCommentLinesOnWeatherInfoFile() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("weather-data.csv").toURI());


        // Count the number of comment lines

        System.out.println(
                Files.lines(path).filter(s -> s.startsWith("#")).count()
        );

        // Show the sunny days lines
        System.out.println(
            Files.lines(path).filter(s -> s.contains("Sunny")).count()
        );


    }

}

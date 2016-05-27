package gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gson.meppingTypes.Movie;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lfalcao on 27/05/16.
 */
public class GsonTests {

    @Test
    public void shouldDeserializeAComplexObject() throws Exception {
        Path p = Paths.get(ClassLoader.getSystemResource("sample.json").toURI());
        String json =  Files.readAllLines(p).stream().collect(joining());

        assertNotNull(json);

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Movie>>() {}.getType();


        List<Movie> movies = gson.fromJson(json, listType);

        movies.stream().forEach(this::assertMovie);






        assertEquals(2, movies.size());
//        assertEquals("Movie1", m.getName());
//        assertEquals(2004, m.getYearReleased());

    }

    private void assertMovie(Movie movie) {
        assertNotNull(movie.getName());
        assertTrue(movie.getYearRelease() > 2000);
        assertNotNull(movie.getActors());
    }
}

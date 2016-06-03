package series.tvmazeapi;

import org.junit.Test;
import series.tvmazeapi.dto.ShowDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * These tests use the {@link TvMazeApiImpl} that uses the real
 * TVMaze service. Therefore these tests need online connectivity
 * to succeed.
 */
public class TvMazeApiTests {
    ShowsApi tvMazeApi = new TvMazeApiImpl();

    @Test
    public void shouldGetAShowDto() throws Exception {
        // Arrange


        // Act
        ShowDto showDto = tvMazeApi.getShow(1);

        // Assert
        assertValidShow(showDto);
        assertEquals("Under the Dome", showDto.name);
        assertEquals(3, showDto.genres.length);
        assertEquals(2, showDto.network.id);
        assertEquals("CBS", showDto.network.name);
        assertEquals("United States", showDto.network.country.name);
        assertEquals("US", showDto.network.country.code);
        assertEquals("America/New_York", showDto.network.country.timezone);
    }


    @Test
    public void shouldGetAPageOfShowDto() throws Exception {
        // Arrange


        // Act
        List<ShowDto> shows = tvMazeApi.getShows();

        // Assert
        assertNotNull(shows);
        int size = shows.size();

        assertTrue(size <= 250);
        assertTrue(size > 0);

        shows.forEach(TvMazeApiTests::assertValidShow);
    }

    private static void assertValidShow(ShowDto showDto) {
        assertNotNull(showDto);

        assertIsValidId(showDto.id);
        assertNotNullOrEmpty(showDto.name);

        assertNotNull(showDto.genres);
        assertTrue(String.format("Genres on show %d is %s", showDto.id, Arrays.toString(showDto.genres)), showDto.genres.length >= 0);

        assertValidNetwork(showDto);
    }

    private static void assertValidNetwork(ShowDto showDto) {
        if(showDto.network != null) {
            assertIsValidId(showDto.network.id);
            assertNotNullOrEmpty(showDto.network.name);
            assertNotNull(showDto.network.country);
            assertNotNullOrEmpty(showDto.network.country.name);
            assertNotNullOrEmpty(showDto.network.country.code);
            assertNotNullOrEmpty(showDto.network.country.timezone);
        }
    }

    private static void assertIsValidId(int id) {
        assertTrue(id > 0);
    }

    private static void assertNotNullOrEmpty(String str) {
        assertNotNull(str);
        assertNotEquals("", str);
    }
}

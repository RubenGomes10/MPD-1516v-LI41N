package series.tvmazeapi;

import org.junit.Test;
import series.tvmazeapi.dto.ShowDto;
import utils.Performance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * These tests use the {@link TvMazeApiImpl} that uses the real
 * TVMaze service. Therefore these tests need online connectivity
 * to succeed.
 */
public class TvMazeApiPerformanceTests {
    ShowsApi tvMazeApi = new TvMazeApiImpl();

    @Test
    public void measureSyncGetShows() throws Exception {
        // Arrange


        // Act
        Performance.measure(this::callGetShowSync);

        // Assert
    }

    @Test
    public void measureAsyncGetShows() throws Exception {
        // Arrange


        // Act
        Performance.measure(this::callGetShowAsync);

        // Assert
    }


    public ShowDto callGetShowSync() {
        tvMazeApi.getShow(1).join();
        return tvMazeApi.getShow(1).join();
    }

    public ShowDto callGetShowAsync() {
        CompletableFuture<ShowDto> showName = tvMazeApi.getShow(1).thenCombine(tvMazeApi.getShow(1), (s1, s2) -> s1);
        return showName.join();
    }
}

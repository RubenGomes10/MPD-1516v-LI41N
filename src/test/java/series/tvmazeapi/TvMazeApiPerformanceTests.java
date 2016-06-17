package series.tvmazeapi;

import org.junit.Test;
import series.tvmazeapi.dto.ShowDto;
import utils.Performance;

import java.util.concurrent.ExecutionException;

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
        tvMazeApi.getShow(1);
        return tvMazeApi.getShow(1);
    }

    public ShowDto callGetShowAsync() {
        tvMazeApi.getShowAsync(1);
        try {
            return tvMazeApi.getShowAsync(1).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

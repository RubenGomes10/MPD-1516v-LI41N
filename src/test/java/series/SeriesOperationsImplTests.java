package series;

import org.junit.Test;
import series.domain.SeriesOprations;
import series.domain.SeriesService;

/**
 * Created by lfalcao on 30/05/16.
 */
public class SeriesOperationsImplTests {
    SeriesOprations seriesOperations = new SeriesService(
            new FakeShowsApi(), null // Mapper missing
    );
    @Test
    public void shouldGetASerie() throws Exception {

    }
}

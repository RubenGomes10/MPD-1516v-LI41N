package series.domain;

import org.junit.Test;
import series.domain.mapping.DtoToDomainMapper;
import series.domain.mapping.DtoToDomainMapperImpl;
import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.TvMazeApiImpl;

/**
 * Created by lfalcao on 20/06/16.
 */
public class SeriesServiceTests {

    @Test
    public void shouldGetASerie() throws Exception {

        ShowsApi tvmazeApi = new TvMazeApiImpl();
        DtoToDomainMapper dtoToDomainMapper = new DtoToDomainMapperImpl(tvmazeApi);
        SeriesService seriesService = new SeriesService(tvmazeApi, dtoToDomainMapper);

    }
}

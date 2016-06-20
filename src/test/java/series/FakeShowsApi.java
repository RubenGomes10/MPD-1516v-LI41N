package series;

import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.dto.ShowDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by lfalcao on 30/05/16.
 */
public class FakeShowsApi implements ShowsApi {

    @Override
    public CompletableFuture<List<ShowDto>> getShows() {
        return null;
    }

    @Override
    public CompletableFuture<ShowDto> getShow(int id) {
        return null;
    }
}

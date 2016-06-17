package series.tvmazeapi;

import series.tvmazeapi.dto.ShowDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by lfalcao on 03/06/16.
 */
public interface ShowsApi {
    CompletableFuture<List<ShowDto>> getShows();
    CompletableFuture<ShowDto> getShow(int id);
}


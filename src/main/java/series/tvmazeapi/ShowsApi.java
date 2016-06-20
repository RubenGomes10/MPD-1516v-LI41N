package series.tvmazeapi;

import series.tvmazeapi.dto.SeasonDto;
import series.tvmazeapi.dto.ShowDto;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 03/06/16.
 */
public interface ShowsApi {
    CompletableFuture<Stream<ShowDto>> getShows();
    CompletableFuture<ShowDto> getShow(int id);

    CompletableFuture<Stream<SeasonDto>> getSeasons(int id);
}


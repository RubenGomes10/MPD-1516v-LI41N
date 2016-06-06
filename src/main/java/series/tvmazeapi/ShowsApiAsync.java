package series.tvmazeapi;

import series.tvmazeapi.dto.ShowDto;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by lfalcao on 03/06/16.
 */
public interface ShowsApiAsync  extends ShowsApi {
    Future<List<ShowDto>> getShowsAsync();
    Future<ShowDto> getShowAsync(int id);
}


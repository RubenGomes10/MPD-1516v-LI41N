package series;

import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.dto.ShowDto;

import java.util.List;

/**
 * Created by lfalcao on 30/05/16.
 */
public class FakeShowsApi implements ShowsApi {
    @Override
    public List<ShowDto> getShows() {
        return null;
    }

    @Override
    public ShowDto getShow(int id) {
        return null;
    }
}

package series.tvmazeapi;

import series.tvmazeapi.dto.ShowDto;

import java.util.List;

/**
 * Created by lfalcao on 30/05/16.
 */
public interface ShowsApi {
    List<ShowDto> getShows();
    ShowDto getShow(int id);
}

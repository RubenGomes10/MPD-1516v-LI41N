package series.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by lfalcao on 23/05/16.
 */
public class Serie extends ShowInterval {

    List<Cast> cast;

    public Serie(List<Cast> cast) {
        this.cast = cast;
    }

    List<Season> getSeasons() {
        return null;
    }
    public List<Cast> getCast() {
        return cast;
    }
}

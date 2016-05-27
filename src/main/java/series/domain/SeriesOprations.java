package series.domain;

import java.util.List;

/**
 * Created by lfalcao on 23/05/16.
 */
public interface SeriesOprations {
    List<Serie> searchSerie(String searchString);
    List<Actor> searchActors(String searchString);
    Serie getSerie(int id);
}

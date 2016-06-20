package series.domain;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by lfalcao on 23/05/16.
 */
public interface SeriesOprations {
    List<Serie> searchSerie(String searchString);
    List<Actor> searchActors(String searchString);
    CompletableFuture<Serie> getSerie(int id);
}

package series.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 23/05/16.
 */
public class Serie extends ShowInterval {
    private int id;
    private String name;
    private Supplier<Stream<Season>> seasonsSupplier;

    List<Cast> cast;

    public Serie(int id, String name, Supplier<Stream<Season>> seasonsSupplier) {
        this.id = id;
        this.name = name;
        this.seasonsSupplier = seasonsSupplier;
    }

    public Serie(List<Cast> cast) {
        this.cast = cast;
    }

    Stream<Season> getSeasons() {
        return seasonsSupplier.get();
    }
    public List<Cast> getCast() {
        return cast;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

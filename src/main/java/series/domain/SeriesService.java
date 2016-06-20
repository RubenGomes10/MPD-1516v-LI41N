package series.domain;

import series.domain.mapping.DtoToDomainMapper;
import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.TvMazeApiImpl;
import series.tvmazeapi.dto.ShowDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by lfalcao on 30/05/16.
 */
public class SeriesService implements SeriesOprations {
    ShowsApi showsApi;
    private DtoToDomainMapper mapper;


    public SeriesService(ShowsApi tvMazeApi, DtoToDomainMapper mapper) {
        this.showsApi = tvMazeApi;
        this.mapper = mapper;
    }



    @Override
    public List<Serie> searchSerie(String searchString) {
        return null;
    }

    @Override
    public List<Actor> searchActors(String searchString) {
        return null;
    }

    @Override
    public CompletableFuture<Serie> getSerie(int id) {
        return showsApi
                .getShow(id)
                .thenApply(mapper::showDtoToSerie);
    }

    public CompletableFuture<Serie> getSeasonsForSerie(int serieId) {
        return null;
    }
}

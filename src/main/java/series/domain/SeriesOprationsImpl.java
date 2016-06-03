package series.domain;

import series.domain.mapping.DtoToDomainMapper;
import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.dto.ShowDto;

import java.util.List;

/**
 * Created by lfalcao on 30/05/16.
 */
public class SeriesOprationsImpl implements SeriesOprations {
    ShowsApi showsApi;
    private DtoToDomainMapper mapper;


    public SeriesOprationsImpl(ShowsApi tvMazeApi, DtoToDomainMapper mapper) {
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
    public Serie getSerie(int id) {

        ShowDto showDto = showsApi.getShow(id);
        return mapper.showDtoToSerie(showDto);
    }
}

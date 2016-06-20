package series.domain.mapping;

import series.domain.Season;
import series.domain.Serie;
import series.tvmazeapi.ShowsApi;
import series.tvmazeapi.dto.SeasonDto;
import series.tvmazeapi.dto.ShowDto;
import series.utils.StreamUtils;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by lfalcao on 20/06/16.
 */
public class DtoToDomainMapperImpl implements DtoToDomainMapper {
    ShowsApi showsApi;

    public DtoToDomainMapperImpl(ShowsApi showsApi) {
        this.showsApi = showsApi;
    }

    @Override
    public Serie showDtoToSerie(ShowDto showDto) {
        return new Serie(showDto.id, showDto.name,
                () -> seasonDtosToSeasons(showDto.id));
    }


    private Stream<Season> seasonDtosToSeasons(int id) {
        CompletableFuture<Stream<SeasonDto>> seasons = showsApi.getSeasons(id);
        return StreamUtils.toStream(seasons)
                .map(this::seasonDtoToSeason);

    }

    private Season seasonDtoToSeason(SeasonDto seasonDto) {
        // TODO: FIll season domain object with dto data
        return new Season();
    }
}

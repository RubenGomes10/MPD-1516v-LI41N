package series.domain.mapping;

import series.domain.Serie;
import series.tvmazeapi.dto.ShowDto;

/**
 * Created by lfalcao on 30/05/16.
 */
public interface DtoToDomainMapper {
    Serie showDtoToSerie(ShowDto showDto);
}

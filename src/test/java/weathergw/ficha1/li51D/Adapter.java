package weathergw.ficha1.li51D;

import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

/**
 * Created by lfalcao on 22/04/16.
 */
public interface Adapter extends Function<HistoryArgs, List<WeatherInfo>> {
    default List<WeatherInfo> apply(HistoryArgs args) {
        return applyAdapted(args.name, args.start, args.end);
    }

    List<WeatherInfo> applyAdapted(String name, LocalDate start, LocalDate end);

}

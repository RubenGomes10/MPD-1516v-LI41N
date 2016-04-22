package weathergw.ficha1.li51D;

import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

/**
 * Created by lfalcao on 22/04/16.
 */
public class WeatherGetter {
    public static List<WeatherRegion> getRegions(String name, Function<HistoryArgs, List<WeatherInfo>> hist) {
        return null;
    }

    public static List<WeatherInfo> getHistory(String name, LocalDate start, LocalDate end) {
        return null;
    }
}

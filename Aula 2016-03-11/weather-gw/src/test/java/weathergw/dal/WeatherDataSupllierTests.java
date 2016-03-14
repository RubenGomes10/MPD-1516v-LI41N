package weathergw.dal;

import org.junit.Test;
import weathergw.domain.WeatherInfo;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 11/03/16.
 */
public class WeatherDataSupllierTests {
    @Test
    void shouldObtainWeatherInfosFromFile() {
        Supplier<Collection<WeatherInfo>> d = new WeatherInfoFileSupplier("weather-data.csv");

        Collection<WeatherInfo> weatherInfos = d.get();

    }
}

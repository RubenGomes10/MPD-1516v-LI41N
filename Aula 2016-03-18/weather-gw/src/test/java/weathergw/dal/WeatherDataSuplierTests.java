package weathergw.dal;

import org.junit.Test;
import weathergw.domain.WeatherInfo;

import java.util.Collection;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lfalcao on 11/03/16.
 */
public class WeatherDataSuplierTests {
    @Test
    public void shouldObtainWeatherInfosFromFile() {
        validateWeaherInfoSupplier(new WeatherInfoFileSupplier("weather-data.csv"));
    }

    @Test
    public void shouldObtainWeatherInfosFromService() {
        validateWeaherInfoSupplier( new WeatherInfoServiceSupplier("https://raw.githubusercontent.com/isel-leic-mpd/mpd-2016-i41d/master/aula02/wthr/src/main/resources/data/lisbon-weather-history.csv"));
    }


    //////// Helper test methods ///////////////////////////
    private void validateWeaherInfoSupplier(Supplier<Collection<WeatherInfo>> d) {
        Collection<WeatherInfo> weatherInfos = d.get();

        assertNotNull(weatherInfos);
        for (WeatherInfo wi: weatherInfos) {
            assertNotNull(wi);
            assertTrue(wi.getMaxTempC() > -150);
            assertTrue(wi.getMaxTempC() < 65);
            assertTrue(wi.getMaxTempC() >= wi.getMinTempC());
        }
    }

}

package weathergw.domain;

import org.junit.Test;
import weathergw.dal.WeatherInfoFileSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/**
 * Created by lfalcao on 14/03/16.
 */
public class QueriesTests {

    @Test
    public void shouldObtainMaxTemp() {
        //WeatherInfoFileSupplier supplier = new WeatherInfoFileSupplier("weather-data.csv");
        Supplier<Collection<WeatherInfo>> sup = new Supplier<Collection<WeatherInfo>>() {

            @Override
            public Collection<WeatherInfo> get() {
                return Arrays.asList(
                        new WeatherInfo(null, 25, 0, null, null, null, null),
                        new WeatherInfo(null, 10, 0, null, null, null, null),
                        new WeatherInfo(null, 15, 0, null, null, null, null)
                );
            }
        };
        WeatherInfoQueries queries = new WeatherInfoQueries(sup);
        int maxTemp = queries.getMaxTemp();

        assertEquals(25, maxTemp);

    }

}

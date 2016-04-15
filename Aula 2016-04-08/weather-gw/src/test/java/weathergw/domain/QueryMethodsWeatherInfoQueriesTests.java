package weathergw.domain;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by lfalcao on 14/03/16.
 */
public class QueryMethodsWeatherInfoQueriesTests extends BaseWeatherInfoQueriesTests {
    private static WeatherInfoQueries wiq;
    private static List<WeatherInfo> weatherInfos;

    @BeforeClass
    public static void beforeClass() {
        Supplier<List<WeatherInfo>> supplier = getSupplier(25, 10, 15);
        weatherInfos = new ArrayList<>(supplier.get());
        wiq = new WeatherInfoQueries(supplier);
    }


    int count = 0;

    @Test
    public void forEachShouldIterateAllSuppliedElements() {

        wiq.forEach(wi -> {
            assertSame(weatherInfos.get(count), wi);
            ++count;
        });

        assertEquals(weatherInfos.size(), count);
    }

    @Test
    public void mapShouldIterateAndTransformAllSuppliedElementsForMaxTemp() {
        int[] maxTemps = wiq.mapToInt(WeatherInfo::getMaxTempC);

        assertEquals(weatherInfos.size(), maxTemps.length);

        for (int i = 0; i < weatherInfos.size(); ++i) {
            assertEquals(weatherInfos.get(i).getMaxTempC(), maxTemps[i]);
        }
    }

    @Test
    public void mapShouldIterateAndTransformAllSuppliedElementsForSunset() {

        Collection<LocalTime> mappedWi = wiq.map((wi) -> wi.getSunset());
        LocalTime[] sunsets = mappedWi.toArray(new LocalTime[]{});

        assertEquals(weatherInfos.size(), sunsets.length);

        for (int i = 0; i < weatherInfos.size(); ++i) {
            assertEquals(weatherInfos.get(i).getSunset(), sunsets[i]);
        }
    }
}

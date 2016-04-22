package weathergw;

import weathergw.domain.DecoratorCountSupplier;
import org.junit.Before;
import org.junit.Test;
import weathergw.dal.WeatherInfoFileSupplier;
import weathergw.dal.WeatherInfoMemorySupplier;
import weathergw.domain.Location;
import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lfalcao on 07/03/16.
 */
public class LocationTests extends BaseWeatherInfoTests {
    private Location location;
    private WeatherInfo weatherInfo1;
    private WeatherInfo weatherInfo2;

    @Before
    public void before() {
        location = new Location("Lisbon", getSupplier());
        weatherInfo1 = new WeatherInfo(LocalDate.now(), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));
        weatherInfo2 = new WeatherInfo(LocalDate.now().minusDays(1), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));
    }

    @Test
    public void shouldObtainWeatherInfoDataFromFile() {
        // Arrange
        location = new Location("Lisbon", new WeatherInfoFileSupplier("weather-data.csv"));


        // Act
        List<WeatherInfo> weatherInfos = location.weatherInfos();


        // Assert
        assertNotNull(weatherInfos);
        assertTrue(weatherInfos.size() != 0);



    }

    @Test
    public void shouldObtainWeatherInfoMemoryAndFileOnlyWithOneCall() {
        createLocationGetWeatherInfosAndAssert(Location::weatherInfos, (l, cs) -> { });
    }



    @Test
    public void shouldObtainWeatherInfoMemoryAndFileOnlyWithTwoCalls() {
        createLocationGetWeatherInfosAndAssert(
                (l) -> { l.weatherInfos(); return l.weatherInfos(); },
                (l, c) -> assertEquals(1, c.intValue()));
    }

//    private void createLocationGetWeatherInfosAndAssert(Function<Location, List<WeatherInfo>> act, BiConsumer<List<WeatherInfo>, Integer> assertConsummer) {
//        // Arrange
//        //CountSupplier countSupplierForFileSupplier = new CountSupplier(new WeatherInfoFileSupplier("weather-data.csv"));
//
//        final WeatherInfoFileSupplier weatherInfoFileSupplier = new WeatherInfoFileSupplier("weather-data.csv");
//
//        int []count = {0};
//
//        Supplier<List<WeatherInfo>> countSupplierForFileSupplier = () ->  {
//            ++count[0]; return weatherInfoFileSupplier.get();
//        };
//
//
//        location = new Location("Lisbon",
//                new WeatherInfoMemorySupplier(countSupplierForFileSupplier));
//
//        // Act
//        List<WeatherInfo> weatherInfos = act.apply(location);
//
//
//        // Assert
//        assertNotNull(weatherInfos);
//        assertTrue(weatherInfos.size() != 0);
//
//
//        assertConsummer.accept(weatherInfos, count[0]);
//    }

    private void createLocationGetWeatherInfosAndAssert(Function<Location, List<WeatherInfo>> act, BiConsumer<List<WeatherInfo>, Integer> assertConsummer) {
        // Arrange
        //CountSupplier countSupplierForFileSupplier = new CountSupplier(new WeatherInfoFileSupplier("weather-data.csv"));

        final WeatherInfoFileSupplier weatherInfoFileSupplier = new WeatherInfoFileSupplier("weather-data.csv");


        Supplier<List<WeatherInfo>> countSupplierForFileSupplier = DecoratorCountSupplier.decorateSupplier(weatherInfoFileSupplier);


        location = new Location("Lisbon",
                new WeatherInfoMemorySupplier(countSupplierForFileSupplier));

        // Act
        List<WeatherInfo> weatherInfos = act.apply(location);


        // Assert
        assertNotNull(weatherInfos);
        assertTrue(weatherInfos.size() != 0);


        assertConsummer.accept(weatherInfos, );
    }



}

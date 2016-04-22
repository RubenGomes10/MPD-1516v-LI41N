package composingfunctions;

import composablecomparators.CmpComposableC;
import composablecomparators.CmpComposableI;
import composablecomparators.CmpComposableUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import weathergw.dal.WeatherInfoFileSupplier;
import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static composablecomparators.CmpComposableC.createComparatorV2;
import static composablecomparators.CmpComposableI.createComparatorV3;
import static composablecomparators.CmpComposableUtils.*;
import static java.util.Comparator.comparing;

/**
 * Created by lfalcao on 11/04/16.
 */
public class ComparatorTests {


    private static Supplier<List<WeatherInfo>> weatherInfos;

    @BeforeClass
    public static void beforeClass() {
        weatherInfos = new WeatherInfoFileSupplier("weather-data.csv");
    }

    @Test
    public void creatingComparatorsUsingComparatorMethods() {
        List<WeatherInfo> weatherInfos = ComparatorTests.weatherInfos.get();


        Comparator<WeatherInfo> composedComparator =
                comparing(WeatherInfo::getDate)
                        .reversed()
                        .thenComparing(WeatherInfo::getMaxTempC)
                        .thenComparing(WeatherInfo::getSunrise);
        weatherInfos.sort(composedComparator);
    }

    @Test
    public void creatingComparatorsUsingCustomStaticMethods() {
        List<WeatherInfo> weatherInfos = ComparatorTests.weatherInfos.get();

        Comparator<WeatherInfo> composedComparator =
                andThen(andThen(
                            inverted(createComparator(WeatherInfo::getDate)),
                            WeatherInfo::getMaxTempC),
                        WeatherInfo::getSunrise)
                ;

        weatherInfos.sort(composedComparator);
    }

    @Test
    public void creatingComparatorsUsingCustomClassInstanceMethods() {
        List<WeatherInfo> weatherInfos = ComparatorTests.weatherInfos.get();

//        Comparator<WeatherInfo> composedComparator =
//                createComparatorV2(WeatherInfo::getDate)
//                        .inverted()
//                        .andThen(WeatherInfo::getMaxTempC)
//                        .andThen(WeatherInfo::getSunrise);

        CmpComposableC<WeatherInfo> dateComparator = createComparatorV2(WeatherInfo::getDate);
        weatherInfos.sort(dateComparator);

        Comparator<WeatherInfo> composedComparator =
                        dateComparator
                        .inverted()
                        .andThen(WeatherInfo::getMaxTempC)
                        .andThen(WeatherInfo::getSunrise);

        weatherInfos.sort(composedComparator);

        weatherInfos.sort(dateComparator);

    }

    @Test
    public void creatingComparatorsUsingCustomInterfaceDefaultMethods() {
        List<WeatherInfo> weatherInfos = ComparatorTests.weatherInfos.get();

        Comparator<WeatherInfo> composedComparator =
                createComparatorV3(WeatherInfo::getDate)
                        .inverted()
                        .andThen(WeatherInfo::getMaxTempC)
                        .andThen(WeatherInfo::getSunrise);

        weatherInfos.sort(composedComparator);

    }


}

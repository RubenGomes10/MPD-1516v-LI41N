package composingfunctions;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.BeforeClass;
import org.junit.Test;
import weathergw.dal.WeatherInfoFileSupplier;
import weathergw.domain.WeatherInfo;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

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
    public void creatingComparatorsV1() {
        List<WeatherInfo> weatherInfos = ComparatorTests.weatherInfos.get();

        weatherInfos.sort((wi1, wi2) -> wi1.getDate().compareTo(wi2.getDate()));

        Comparator<WeatherInfo> comparatorDate = comparing(WeatherInfo::getDate);
        Comparator<WeatherInfo> comparatorMaxTemp = comparing(WeatherInfo::getMaxTempC);


        weatherInfos.sort(comparatorMaxTemp);

        weatherInfos.sort(reverse(comparatorMaxTemp));

        Comparator<WeatherInfo> comparatorDateThenMaxTemp = thenComparing(comparatorDate, comparatorMaxTemp);

        weatherInfos.sort(comparatorDateThenMaxTemp);

        Comparator<WeatherInfo> comparatorDateThenMaxTempThenSunrise = thenComparing(comparatorDateThenMaxTemp, createComparator(WeatherInfo::getSunrise));


        weatherInfos.sort(comparatorDateThenMaxTempThenSunrise);

        weatherInfos.sort(comparing(WeatherInfo::getDate).
                thenComparing(WeatherInfo::getMaxTempC).thenComparing(WeatherInfo::getSunrise));
    }

    public <T> Comparator<T> reverse(Comparator<T> comparator) {
        return (t1, t2) -> comparator.compare(t2,t1);
    }

    public <T, U extends Comparable<U>> Comparator<T> createComparator(Function<? super T, U> keyExtractor) {
        return (T t1, T t2) -> keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2));
    }


    // This version is not possible due to Java generics limitations
//    public <T, U extends Comparable<U>> Comparator<T> createComparator1(Function<T, U> ...keyExtractors) {
//        if(keyExtractors == null || keyExtractors.length == 0) {
//            throw new IllegalArgumentException("keyExtractors must not be null or an empty array");
//        }
//
//        Comparator<T> comparator = createComparator(keyExtractors[0]);
//        for (int i = 1; i < keyExtractors.length; ++i) {
//            comparator = thenComparing(comparator, createComparator(keyExtractors[i]));
//        }
//
//        return comparator;
//    }

    public <T, U extends Comparable<U>> Comparator<T> createReverseComparator(Function<T, U> sup) {
        return (T t1, T t2) -> sup.apply(t2).compareTo(sup.apply(t1));
    }

    public <T> Comparator<T> thenComparing(Comparator<T> comparator1, Comparator<T> comparator2) {
        return (t1, t2) -> {
            int res = comparator1.compare(t1, t2);
            return res != 0 ? res : comparator2.compare(t1, t2);
        };
    }

}

package query;

import org.junit.Test;
import weathergw.dal.WeatherInfoFileSupplier;
import weathergw.domain.WeatherInfo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Created by lfalcao on 29/04/16.
 */
public class QueryTests {
    final List<WeatherInfo> weatherInfos = new WeatherInfoFileSupplier("weather-data.csv").get();

    @Test
    public void severalQueriesBeforeJava8() {

        // Filter all wewtherInfos with temperature less than 20º
        ArrayList<WeatherInfo> lessThenTempreatures = new ArrayList<>();
        for (WeatherInfo wi: weatherInfos) {
            if(filterTemperatures(wi)) {
                lessThenTempreatures.add(wi);
            }
        }

        // Sort by max temperature ascending
        Collections.sort(lessThenTempreatures, comparing(WeatherInfo::getMaxTempC));

        ArrayList<LocalTime> sunriseTime = new ArrayList<>();
        for (WeatherInfo wi: lessThenTempreatures) {
            sunriseTime.add(mapToSunset(wi));
        }

        // Get only the first 5 results
        ArrayList<LocalTime> top5Sunrise = new ArrayList<>();
        for (int i = 5; i < 10 && i < sunriseTime.size(); ++i) {
            top5Sunrise.add(sunriseTime.get(i));
        }

        for (LocalTime sr: top5Sunrise) {
            System.out.println(sr);
        }
    }

    @Test
    public void severalQueriesWithJava8Streams() {

        Stream<LocalTime> sunriseTimes = weatherInfos.stream()
                .filter(QueryTests::filterTemperatures)
                //.sorted(comparing(WeatherInfo::getMaxTempC))
                .skip(5)
                .map(QueryTests::mapToSunset)
                .limit(5)
                ;


        sunriseTimes.forEach(System.out::println);
    }

    @Test
    public void externalIteration() {
        int sum = 0;
        for (WeatherInfo wi: weatherInfos) {
            sum += wi.getMaxTempC();
        }

        System.out.println(sum);
    }

    @Test
    public void internalIteration() {
        final int[] sum = {0};
        weatherInfos.forEach(wi -> sum[0] += wi.getMaxTempC());
        System.out.println(sum[0]);
    }

    private static LocalTime mapToSunset(WeatherInfo weatherInfo) {
        System.out.println("called mapToSunset");
        return weatherInfo.getSunrise();
    }

    private static boolean filterTemperatures(WeatherInfo weatherInfo) {
        System.out.println("called filterTemperatures");
        return weatherInfo.getMaxTempC() < 20;
    }
}

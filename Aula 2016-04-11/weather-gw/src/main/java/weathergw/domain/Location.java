package weathergw.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by lfalcao on 07/03/16.
 */
public class Location implements Iterable<WeatherInfo> {
    private String name;
    private Map<LocalDate, WeatherInfo> weatherInfos;

    public Location(String name) {
        this.name = name;
        weatherInfos = new HashMap<LocalDate, WeatherInfo>();
    }

    public Location() {

    }


    public void add(WeatherInfo weatherInfo) {
        Objects.requireNonNull(weatherInfo, "weatherInfo cannot be null");

        weatherInfos.put(weatherInfo.getDate(), weatherInfo);
    }

    public WeatherInfo get(LocalDate date) {
        return weatherInfos.get(date);
    }


    @Override
    public Iterator<WeatherInfo> iterator() {
        return weatherInfos.values().iterator();
    }

    public Collection<WeatherInfo> weatherInfos() {
        return Collections.unmodifiableCollection(weatherInfos.values());
    }

    public String getName() {
        return name;
    }
}

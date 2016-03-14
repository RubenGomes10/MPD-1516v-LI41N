package weathergw.dal;

import weathergw.domain.WeatherInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 11/03/16.
 */
public class WeatherInfoFileSupplier implements Supplier<Collection<WeatherInfo>> {
    private static final String COMMENT_PREFIX = "#";
    public static final int WEATHER_INFO_COLS = 9;
    private static final java.lang.String WEATHER_INFO_SEPARATOR = ",";
    private String filename;

    public WeatherInfoFileSupplier(String filename) {
        this.filename = filename;
    }

    @Override
    public Collection<WeatherInfo> get() {
        return parseWeatherInfo(readFile());
    }

    private Collection<WeatherInfo> parseWeatherInfo(List<String> lines) {
        ArrayList<WeatherInfo> weatherInfos = new ArrayList<>();
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i);
            if(isWeateherInfo(line)) {
                weatherInfos.add(parseLine(line));
            }
        }
        return weatherInfos;
    }

    private boolean isWeateherInfo(String line) {
        return !isComment(line) && line.split(WEATHER_INFO_SEPARATOR).length == WEATHER_INFO_COLS;
    }

    private boolean isComment(String line) {
        return line.startsWith(COMMENT_PREFIX);
    }

    private WeatherInfo parseLine(String line) {
        // #date(0),maxtempC(1),maxtempF(2),mintempC(3),
        // mintempF(4),sunrise(5),sunset(6),moonrise(7),moonset(8)
        String[] wicols = line.split(WEATHER_INFO_SEPARATOR);
        return new WeatherInfo(
                LocalDate.parse(wicols[0]), // localDate index 0
                Integer.parseInt(wicols[1]), // maxTempC index 1
                Integer.parseInt(wicols[3]), // minTempC index 3
                LocalTime.parse(wicols[5]), // sunrise index 5
                LocalTime.parse(wicols[6]), // sunset index 6
                LocalTime.parse(wicols[7]), // moonrise index 7
                LocalTime.parse(wicols[8]) // moonrise index 8
        );
    }

    private List<String> readFile() {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            // TODO Should log to some log mechanism
            e.printStackTrace();
            return null;
        }
    }
}

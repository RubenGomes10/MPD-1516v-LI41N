package weathergw.dal;

import weathergw.domain.WeatherInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 11/03/16.
 */
public class WeatherInfoServiceSupplier extends WeatherInfoCsvSupplier implements Supplier<Collection<WeatherInfo>> {


    public WeatherInfoServiceSupplier(String filename) {
        super(filename);
    }

    protected List<String> readFile() {
        // TODO
        return null;
    }
}

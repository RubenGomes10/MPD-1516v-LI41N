package weathergw.domain;

import weathergw.dal.WeatherInfoFileSupplier;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 14/03/16.
 */
public class WeatherInfoQueries {
    private static Supplier<Collection<WeatherInfo>> supplier;

    public WeatherInfoQueries(Supplier<Collection<WeatherInfo>> supplier) {

        this.supplier = supplier;
    }

    public int getMaxTemp() {
        Collection<WeatherInfo> weatherInfos = supplier.get();
        WeatherInfo maxWi = null;
        for (WeatherInfo wi: weatherInfos) {
            if(maxWi == null) {
                maxWi = wi;
            } else {
                if(wi.getMaxTempC() > maxWi.getMaxTempC()) {
                    maxWi = wi;
                }
            }
        }

        return maxWi.getMaxTempC();
    }
}

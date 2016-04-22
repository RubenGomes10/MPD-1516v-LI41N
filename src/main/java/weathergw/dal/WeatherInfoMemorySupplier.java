package weathergw.dal;

import weathergw.domain.WeatherInfo;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 18/04/16.
 */
public class WeatherInfoMemorySupplier implements Supplier<List<WeatherInfo>> {
    Supplier<List<WeatherInfo>> sup;

    List<WeatherInfo> cache;

    public WeatherInfoMemorySupplier(Supplier<List<WeatherInfo>> sup) {
        this.sup = sup;
    }





    @Override
    public List<WeatherInfo> get() {
        if(cache == null) {
            cache = sup.get();
        }

        return cache;
    }
}

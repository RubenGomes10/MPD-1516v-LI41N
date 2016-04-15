package weathergw.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 04/04/16.
 */
public class BaseWeatherInfoQueriesTests {

    protected static Supplier<List<WeatherInfo>> getSupplier(final int... maxTemps) {

        return new Supplier<List<WeatherInfo>>() {
            private List<WeatherInfo> wi = createList   ();

            private List<WeatherInfo> createList() {
                ArrayList<WeatherInfo> al = new ArrayList<>();
                for (int i: maxTemps) {
                    al.add(new WeatherInfo(null, i, i, null, null, null, null));
                }
                return al;
            }

            @Override
            public List<WeatherInfo> get() {
                return wi;
            }
        };
    }


}

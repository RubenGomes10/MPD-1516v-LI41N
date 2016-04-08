package weathergw.domain;

import weathergw.common.Predicate;
import weathergw.dal.WeatherInfoFileSupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

/**
 * Supports several queries for WeatherInfo collections.
 */
public class WeatherInfoQueries {
    private static Supplier<List<WeatherInfo>> supplier;

    public WeatherInfoQueries(Supplier<List<WeatherInfo>> supplier) {

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

    public List<WeatherInfo> getWithMaxTemperaturesBetween(int min, int max) {
//        return filter(new Predicate<WeatherInfo>() {
//            @Override
//            public boolean evaluate(WeatherInfo wi) {
//                return wi.getMaxTempC() >= min && wi.getMaxTempC() < max;
//            }
//        });
        return  filter(wi -> wi.getMinTempC() >= min && wi.getMinTempC() < max);
    }

    public List<WeatherInfo> getWithMinTemperaturesBetween(int min, int max) {
        return filter(new Predicate<WeatherInfo>() {
            @Override
            public boolean evaluate(WeatherInfo wi) {
                return wi.getMinTempC() >= min && wi.getMinTempC() < max;
            }
        });
    }

    public List<WeatherInfo> filter(Predicate<WeatherInfo> pred) {
        Collection<WeatherInfo> weatherInfos = supplier.get();
        List<WeatherInfo> filtered = new ArrayList<>();
        for (WeatherInfo wi: weatherInfos) {
            if(pred.evaluate(wi)) {
                filtered.add(wi);
            }
        }
        return filtered;
    }

    public void forEach(Consumer<WeatherInfo> consumer) {
        for (WeatherInfo wi : supplier.get()) {
            consumer.accept(wi);
        }



//        Collection<WeatherInfo> weatherInfos = supplier.get();
//
//        List<WeatherInfo> wiList = new ArrayList<>(weatherInfos);
//        for (int i = 1; i < wiList.size(); ++i) {
//            consumer.accept(wiList.get(i));
//        }
//        consumer.accept(new WeatherInfo(null, 0, 0, null, null, null, null));
    }

    public <R> Collection<R> map(Function<WeatherInfo, R> projection)  {
        final Collection<R> coll = new ArrayList<>();
//        for (WeatherInfo wi: supplier.get()) {
//            coll.add(projection.apply(wi));
//
//        }
//        return coll;

        forEach(wi -> coll.add(projection.apply(wi)));

        return coll;
    }

    public int[] mapToInt(ToIntFunction<WeatherInfo> intProjection)  {
        List<WeatherInfo> wi = supplier.get();

        int[] intValues = new int[wi.size()];
        for (int i = 0; i < intValues.length; i++) {
            intValues[i] = intProjection.applyAsInt(wi.get(i));
        }

        return intValues;
    }
}

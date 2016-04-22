package weathergw.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 07/03/16.
 */
public class Location {
    private String name;
    private Supplier<List<WeatherInfo>> sup;

    public Location(String name, Supplier<List<WeatherInfo>> sup) {
        this.name = name;
        this.sup = sup;
    }

    public Location() {
        this(null, null);
    }

    public Location(String name) {
        this(name, null);
    }


//    @Override
//    public Iterator<WeatherInfo> iterator() {
//        return weatherInfos.values().iterator();
//    }

    public List<WeatherInfo> weatherInfos() {
        return sup.get();
    }

    public String getName() {
        return name;
    }

    public WeatherInfo get(LocalDate localDate) {
        throw new NotImplementedException();
    }
}

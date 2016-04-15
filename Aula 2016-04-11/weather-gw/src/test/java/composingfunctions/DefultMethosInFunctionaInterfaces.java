package composingfunctions;

import org.junit.Test;
import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * Created by lfalcao on 11/04/16.
 */
public class DefultMethosInFunctionaInterfaces {
    @Test
    public void functionDefaultMethodTests() {
        Function<WeatherInfo, LocalDate> dateFunction = WeatherInfo::getDate;
        Function<WeatherInfo, Integer> weatherInfoIntegerFunction =
                dateFunction
                .andThen(LocalDate::toString)
                .andThen(String::length);

        WeatherInfo wi = new WeatherInfo();

        Integer apply = weatherInfoIntegerFunction.apply(wi);





    }

}

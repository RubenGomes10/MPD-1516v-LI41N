package weathergw.ficha1.li51D;

import weathergw.domain.WeatherInfo;

import java.util.List;
import java.util.function.Function;

/**
 * Created by lfalcao on 22/04/16.
 *
 *
 * Considere o método estático getRegions de WeatherGetter e a classe HistoryArgs:
 List<WeatherRegion> getRegions(String query, Function<HistoryArgs, List<WeatherInfo>> historyGetter){…}
 class HistoryArgs { final String name;  final LocalDate start, end; … }

 Considere a seguinte utilização:

 Function<HistoryArgs, List<WeatherInfo>> hist = p -> WeatherGetter.getHistory(p.name, p.start, p.end);
 List<WeatherRegion> data = WeatherGetter.getRegions("Porto", hist)

 Sem alterar o código existente, implemente uma interface Adapter que permita a seguinte utilização de getRegions:

 Adapter hist = WeatherGetter::getHistory;
 List<WeatherRegion> data = WeatherGetter.getRegions("Porto", hist);

 */
public class AdapterTest {

    void initialCode() {
        Function<HistoryArgs, List<WeatherInfo>> hist = p -> WeatherGetter.getHistory(p.name, p.start, p.end);
        List<WeatherRegion> data = WeatherGetter.getRegions("Porto", hist);

    }

    void adaptedCode() {
        Adapter hist = WeatherGetter::getHistory;
        List<WeatherRegion> data = WeatherGetter.getRegions("Porto", hist);


    }

}

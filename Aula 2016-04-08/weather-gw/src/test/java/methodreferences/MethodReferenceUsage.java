package methodreferences;

import weathergw.domain.Location;
import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by lfalcao on 08/04/16.
 */
public class MethodReferenceUsage {

    /**
     * Option 1 from Java in action book (section 3.6.1)
     */
    void classMethod() {

        // Using lambda syntax
        Function<String, Integer> f = (str) -> Integer.parseInt(str);

        // Using method reference syntax
        Function<String, Integer> f1 = Integer::parseInt;
    }

    /**
     * Option 2 from Java in action book (section 3.6.1)
     */
    void instanceMethodForeknowTarget() {
        final Location lisbon = new Location("Lisboa");
        final Location oporto = new Location("Porto");


        // Using lambda syntax
        BiFunction<Location, LocalDate, WeatherInfo> f = (loc, date) -> loc.get(date);

        // Using method reference syntax
        BiFunction<Location, LocalDate, WeatherInfo> f1 = Location::get;


        f1.apply(lisbon, LocalDate.now().minusDays(1));
        f1.apply(oporto, LocalDate.now().minusDays(1));


    }

    /**
     * Option 3 from Java in action book (section 3.6.1)
     */
    void instanceMethodForKnownTarget() {
        final Location lisbon = new Location("Lisboa");

        // Using lambda syntax
        Function<LocalDate, WeatherInfo> f = (date) -> lisbon.get(date);

        // Using method reference syntax
        Function<LocalDate, WeatherInfo> f1 = lisbon::get;

        f1.apply(LocalDate.now().minusDays(1));
        f1.apply(LocalDate.now().minusDays(1));


    }


    void miscSamplesUsingMethodReferences() {
        List<String> listStrs = Arrays.asList("a", "b", "A", "B");
        listStrs.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        listStrs.sort(String::compareToIgnoreCase);

        BiPredicate<List<String>, String> containsLambda =
                (list, element) -> list.contains(element);

        BiPredicate<List<String>, String> containsRefMethod =
                List<String>::contains;


        boolean res = containsLambda.test(listStrs, "a");
        boolean res1 = containsRefMethod.test(listStrs, "a");
    }


    void constructorreferences() {
        Supplier<String> supplierLambda = () -> new String();
        Supplier<String> supplierConstructorReference = String::new;

        Function<String, Location> functionLambda = (name) -> new Location(name);
        Function<String, Location> functionConstructorReference = Location::new;

        Supplier<Location> supplierLocationLambda = () -> new Location();
        Supplier<Location> supplierLocationConstructorReference = Location::new;

        Location lisboa = functionConstructorReference.apply("Lisboa");
        Location location = supplierLocationConstructorReference.get();

    }


}

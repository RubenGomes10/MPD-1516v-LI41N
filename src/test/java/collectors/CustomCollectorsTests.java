package collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by lfalcao on 16/05/16.
 */
public class CustomCollectorsTests extends CollectorsTests{

    @Test
    public void shouldUserMySetCollectorAndObtainASet() {
        List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica", "Campeão", "2015/2016", "Campeão", "2015/2016", "Campeão", "2015/2016", "Campeão", "2015/2016", "Dá-me", "o", "36", "Dá-me", "o", "36", "Dá-me", "o", "36", "Dá-me", "o", "36");

        Set<String> collect =
                strings
                    .parallelStream()
                    //.stream()
                    //.sorted()
                    .collect(new MySetCollector<>());

        System.out.println(collect);
    }


}

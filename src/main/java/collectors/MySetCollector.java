package collectors;

import javax.annotation.processing.Completion;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by lfalcao on 16/05/16.
 */
public class MySetCollector<T>  implements Collector<T, Set<T>, Set<T>> {
//    @Override
//    public Supplier<Set<T>> supplier() {
//        System.out.println("supplier");
//        return HashSet::new;
//    }
//
//    @Override
//    public BiConsumer<Set<T>, T> accumulator() {
//        System.out.println("accumulator");
//        return Set::add;
//    }
//
//    @Override
//    public BinaryOperator<Set<T>> combiner() {
//        System.out.println("combiner");
//        return (s1, s2) -> { s1.addAll(s2); return s1; };
//    }
//
//    @Override
//    public Function<Set<T>, Set<T>> finisher() {
//        System.out.println("finisher");
//        return Function.identity();
//    }


    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier");
        return () -> {
            System.out.println("supplier called");
            return new HashSet();
        };
    }

    @Override
        public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator");
        return (s1, t) -> {
            System.out.println("accumulator called");
            s1.add(t);
        };


    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner");
        return (s1, s2) -> {
            System.out.println("combiner called");
            s1.addAll(s2); return s1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher");
        return (s) -> {
            System.out.println("finisher called");
            return s;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics");
        return new HashSet<>(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}

/*

/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/bin/java -Dvisualvm.id=430499598225541 -ea -Didea.launcher.port=7537 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA.app/Contents/bin" -Didea.junit.sm_runner -Dfile.encoding=UTF-8 -classpath "/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/junit/lib/junit-rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/lib/tools.jar:/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2015-2016/2015-2016-Ver-MPD/Repositories/MPD-1516v-LI41N/build/classes/test:/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2015-2016/2015-2016-Ver-MPD/Repositories/MPD-1516v-LI41N/build/resources/test:/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2015-2016/2015-2016-Ver-MPD/Repositories/MPD-1516v-LI41N/build/classes/main:/Users/lfalcao/.gradle/caches/modules-2/files-2.1/junit/junit/4.11/4e031bb61df09069aeb2bffb4019e7a5034a4ee0/junit-4.11.jar:/Users/lfalcao/.gradle/caches/modules-2/files-2.1/org.hamcrest/hamcrest-core/1.3/42a25dc3219429f0e5d060061f71acb49bf010a0/hamcrest-core-1.3.jar" com.intellij.rt.execution.application.AppMain com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 collectors.CustomCollectorsTests,shouldUserMySetCollectorAndObtainASet
        characteristics
        supplier
        accumulator
        combiner
        characteristics
        supplier called
        supplier called
        accumulator called
        supplier called
        accumulator called
        supplier called
        accumulator called
        accumulator called
        combiner called
        supplier called
        accumulator called
        supplier called
        accumulator called
        accumulator called
        supplier called
        accumulator called
        combiner called
        supplier called
        accumulator called
        accumulator called
        supplier called
        accumulator called
        supplier called
        accumulator called
        accumulator called
        combiner called
        supplier called
        accumulator called
        accumulator called
        supplier called
        accumulator called
        combiner called
        combiner called
        supplier called
        accumulator called
        accumulator called
        supplier called
        accumulator called
        combiner called
        supplier called
        accumulator called
        supplier called
        accumulator called
        accumulator called
        combiner called
        combiner called
        accumulator called
        accumulator called
        combiner called
        combiner called
        combiner called
        combiner called
        combiner called
        combiner called
        combiner called
        characteristics
        finisher
        finisher called
        [Sport, 36, Lisboa, e, 2015/2016, Campeão, Dá-me, Benfica, o]

        Process finished with exit code 0

*/